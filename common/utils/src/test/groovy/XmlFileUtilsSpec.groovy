import org.example.utils.XmlFileUtils
import spock.lang.Specification
import spock.lang.Title

import javax.xml.stream.XMLStreamException
import javax.xml.stream.XMLStreamReader

@Title("XMLFileUtils 단위 테스트")
class XmlFileUtilsSpec extends Specification {

    def "정상적인 XML 파일을 오류 없이 읽어드릴 수 있다."() {
        given:
        String filePath = "src/test/resources/CORPCODE.xml"

        when:
        XMLStreamReader reader = XmlFileUtils.parse(filePath)

        then:
        reader != null

        cleanup:
        reader?.close()
    }

    def "존재하지 않는 파일을 읽을 경우 오류를 반환한다."() {
        given:
        String nonExistFilePath = "src/test/resources/non-exist.xml"

        when:
        XmlFileUtils.parse(nonExistFilePath)

        then:
        thrown(FileNotFoundException)
    }

    def "XML 형식이 아닌 파일을 읽을 경우 오류를 반환한다."() {
        given:
        String invalidFilePath = "src/test/resources/zip-file.zip"

        when:
        XmlFileUtils.parse(invalidFilePath)

        then:
        def e = thrown(XMLStreamException)
        e.message == "Invalid file format. Expected XML."
    }
}