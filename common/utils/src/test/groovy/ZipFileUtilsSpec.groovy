import org.example.utils.ZipFileUtils
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.nio.file.Files
import java.nio.file.Path

@Stepwise
class ZipFileUtilsSpec extends Specification {
    private final String targetPath = "src/test/resources"
    private final Path sourceZipFilePath = Path.of("src/test/resources/source.zip")
    @Shared private final Path testZipFilePath = Path.of("src/test/resources/zip-file.zip")
    @Shared private final Path testXmlFilePath = Path.of("src/test/resources/CORPCODE.xml")

    def "byte 배열을 읽어 zip 형태의 파일을 저장할 수 있다."() {
        given:
        byte[] bytes = Files.readAllBytes(sourceZipFilePath)

        when:
        ZipFileUtils.save(targetPath, bytes)

        then:
        Files.exists(testZipFilePath)
    }

    def "zip 형태의 파일을 읽어들여 올바르게 압축을 풀어 저장할 수 있다."() {
        when:
        ZipFileUtils.unZip(targetPath)

        then:
        Files.exists(testXmlFilePath)
    }

    def "테스트용 파일 삭제"() {
        when:
        Files.deleteIfExists(testXmlFilePath)
        Files.deleteIfExists(testZipFilePath)

        then:
        !Files.exists(testXmlFilePath)
        !Files.exists(testZipFilePath)
    }
}