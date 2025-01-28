from airflow import DAG
from airflow.operators.bash import BashOperator
from datetime import datetime, timedelta

# DAG 기본 설정
default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

# DAG 정의
with DAG(
    dag_id='git_sync_test_dag',
    default_args=default_args,
    description='Git Sync 테스트를 위한 간단한 DAG',
    schedule_interval=None,  # 수동 실행 전용
    start_date=datetime(2025, 1, 1),  # 과거 실행 방지
    catchup=False,  # 이전 날짜 실행 방지
    tags=['git_sync', 'test'],
) as dag:

    # BashOperator 작업 정의
    task_1 = BashOperator(
        task_id='print_hello',
        bash_command='echo "Git Sync 테스트: Hello, Airflow!"',
    )

    task_2 = BashOperator(
        task_id='print_success',
        bash_command='echo "Git Sync 테스트 완료!"',
    )

    # Task 의존성 정의
    task_1 >> task_2
