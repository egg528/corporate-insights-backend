import org.example.utils.XmlFileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("XMLFileUtils 단위 테스트")
public class XmlFileUtilsTest {

    @Test
    @DisplayName("정상적인 XML 파일을 오류 없이 읽어드릴 수 있다.")
    public void parseValidXmlFile() {
        // given
        String filePath = "src/test/resources/CORPCODE.xml";

        // when
        XMLStreamReader reader = null;

        try {
            reader = XmlFileUtils.parse(filePath);
         } catch (FileNotFoundException | XMLStreamException e) {
            fail("Exception should not be thrown for a valid XML file: " + e.getMessage());
        }

        // then
        assertNotNull(reader);
    }

    @Test
    @DisplayName("존재하지 않는 파일을 읽을 경우 오류를 반환한다.")
    public void parseNonExistFile() {
        // given
        String nonExistFilePath = "src/test/resources/non-exist.xml";

        // when & then
        assertThrows(FileNotFoundException.class, () -> {
            XMLStreamReader reader = XmlFileUtils.parse(nonExistFilePath);
            if (reader != null) {
                reader.close();
            }
        }, "FileNotFoundException should be thrown for a non-existent file.");
    }

    @Test
    @DisplayName("XML 형식이 아닌 파일을 읽을 경우 오류를 반환한다.")
    public void parseInvalidFile() {
        // given
        String invalidFilePath = "src/test/resources/zip-file.zip";

        // when & then
        assertThrows(XMLStreamException.class, () -> {
            XMLStreamReader reader = XmlFileUtils.parse(invalidFilePath);
            if (reader != null) {
                reader.close();
            }
        }, "Invalid file format. Expected XML.");
    }
}