from airflow import DAG
from airflow.operators.python import PythonOperator
from airflow.utils import timezone
from airflow.models import Variable

import mysql.connector
from mysql.connector import Error

import pytz
import logging
from datetime import datetime
import OpenDartReader

# 로깅 설정
logging.basicConfig(level=logging.INFO, format="%(asctime)s - %(levelname)s - %(message)s")


class CorporationSync:
    """상장 기업 정보를 OpenDartReader API에서 가져와 MySQL DB에 동기화하는 클래스."""

    API_KEY = Variable.get("OPENDART_API_KEY")
    DB_CONFIG = {
        "host": Variable.get("MYSQL_HOST"),
        "user": Variable.get("MYSQL_USERNAME"),
        "password": Variable.get("MYSQL_PASSWORD"),
        "database": "corporate-insights"
    }

    def __init__(self):
        self.dart = OpenDartReader(self.API_KEY)
        self.conn = None

    def __enter__(self):
        """MySQL 연결 생성"""
        try:
            self.conn = mysql.connector.connect(**self.DB_CONFIG)
            logging.info("✅ MySQL 데이터베이스 연결 성공")
            return self
        except Error as e:
            logging.error(f"❌ MySQL 연결 실패: {e}")
            raise

    def __exit__(self, exc_type, exc_value, traceback):
        """MySQL 연결 종료"""
        if self.conn and self.conn.is_connected():
            self.conn.close()
            logging.info("🔌 MySQL 연결 종료")

    def fetch_corp_codes(self):
        """DART API에서 상장회사 목록을 가져옴"""
        try:
            corps_df = self.dart.corp_codes
            listed_corps = corps_df
            logging.info(f"📊 전체 상장회사 수: {len(listed_corps)}")
            return listed_corps
        except Exception as e:
            logging.error(f"❌ DART API 호출 중 에러 발생: {e}")
            raise

    def update_mysql(self, new_corps_df):
        """MySQL 데이터베이스 업데이트"""
        try:
            with self.conn.cursor() as cursor:
                current_corps_dict = self._fetch_current_companies(cursor)
                update_result = self._process_corporate_changes(cursor, new_corps_df, current_corps_dict)
                self.conn.commit()
                logging.info("✅ MySQL 업데이트 완료")
                return update_result
        except Error as e:
            self.conn.rollback()
            logging.error(f"❌ MySQL 처리 중 에러 발생: {e}")
            raise

    def _fetch_current_companies(self, cursor):
        """현재 데이터베이스에 저장된 상장 기업 정보 조회"""
        cursor.execute("""
            SELECT code, stock_code, is_active, last_modified 
            FROM corporates 
            WHERE is_active = TRUE
        """)
        current_corps = cursor.fetchall()
        return {row[0]: row[3].strftime('%Y%m%d') if row[3] else None for row in current_corps}

    def _process_corporate_changes(self, cursor, new_corps_df, current_corps_dict):
        """신규 상장, 폐지, 정보 업데이트 처리"""
        now = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        new_corps_dict = dict(zip(new_corps_df["corp_code"], new_corps_df["modify_date"]))

        current_codes = set(current_corps_dict.keys())
        new_codes = set(new_corps_dict.keys())

        codes_to_insert = new_codes - current_codes
        codes_to_deactivate = current_codes - new_codes
        codes_to_update = {code for code in (new_codes & current_codes)
                           if new_corps_dict[code] != current_corps_dict[code]}

        self._insert_new_companies(cursor, new_corps_df, codes_to_insert, now)
        self._deactivate_old_companies(cursor, codes_to_deactivate, now)
        self._update_existing_companies(cursor, new_corps_df, codes_to_update, now)

        return {
            "new_companies": len(codes_to_insert),
            "deactivated_companies": len(codes_to_deactivate),
            "updated_companies": len(codes_to_update),
        }


def sync_corporations():
    """기업 데이터를 동기화하는 메인 실행 함수"""
    try:
        with CorporationSync() as sync:
            new_corp_data = sync.fetch_corp_codes()
            result = sync.update_mysql(new_corp_data)

            logging.info(f"""
            ✅ 처리 결과:
            - 신규 상장 기업: {result['new_companies']}
            - 상장 폐지 기업: {result['deactivated_companies']}
            - 정보 갱신 기업: {result['updated_companies']}
            """)
    except Exception as e:
        logging.error(f"❌ 동기화 중 에러 발생: {e}")


# Airflow DAG 정의
default_args = {
    "owner": "airflow",
    "start_date": timezone.datetime(2024, 1, 1, tzinfo=pytz.timezone("Asia/Seoul")),
    "retries": 1,
}

with DAG(
    dag_id="corporation_sync_dag",
    default_args=default_args,
    schedule_interval="0 0 * * *",  # UTC 기준 자정 → KST 기준 09:00
    catchup=False,
    tags=["opendart", "mysql", "sync"]
) as dag:

    sync_task = PythonOperator(
        task_id="sync_corporations",
        python_callable=sync_corporations,
        provide_context=True,
    )

    sync_task
