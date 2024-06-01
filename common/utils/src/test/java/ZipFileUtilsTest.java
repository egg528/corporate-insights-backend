import org.example.utils.ZipFileUtils;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@DisplayName("ZipFileUtils 단위 테스트")
@TestMethodOrder(OrderAnnotation.class)
public class ZipFileUtilsTest {
    private final String targetPath = "src/test/resources";
    private final Path sourceZipFilePath = Path.of("src/test/resources/source.zip");
    private final Path testZipFilePath = Path.of("src/test/resources/zip-file.zip");
    private final Path testXmlFilePath = Path.of("src/test/resources/CORPCODE.xml");

    @Test
    @DisplayName("byte 배열을 읽어 zip 형태의 파일을 저장할 수 있다.")
    @Order(1)
    public void saveByteArrayToZipFile() throws IOException {
        // given
        byte[] bytes = Files.readAllBytes(sourceZipFilePath);

        // when
        ZipFileUtils.save(targetPath, bytes);

        // then
        assertTrue(Files.exists(testZipFilePath), "Expected file should exist after save byte array.");
    }

    @Test
    @DisplayName("zip 형태의 파일을 읽어들여 올바르게 압출을 풀어 저장할 수 있다.")
    @Order(2)
    public void unZipValidZipFile() throws IOException {
        // when
        ZipFileUtils.unZip(targetPath);

        // then
        assertTrue(Files.exists(testXmlFilePath), "Expected file should exist after unzipping");
    }

    @Test
    @DisplayName("테스트용 파일 삭제")
    @Order(3)
    public void deleteTestFiles() throws IOException {
        Files.deleteIfExists(testXmlFilePath);
        Files.deleteIfExists(testZipFilePath);
    }
}
