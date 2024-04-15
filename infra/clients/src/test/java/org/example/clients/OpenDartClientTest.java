package org.example.clients;

import lombok.RequiredArgsConstructor;
import org.example.XmlFileUtils;
import org.example.ZipFileUtils;
import org.example.clients.client.OpenDartClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Path;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class OpenDartClientTest {

    private final OpenDartClient openDartClient;

    @Test
    public void test() throws IOException, XMLStreamException {
        var res = openDartClient.findCorporatesZipFile();
        XmlFileUtils.read("src/main/resources/CORPCODE.xml");

    }
}
