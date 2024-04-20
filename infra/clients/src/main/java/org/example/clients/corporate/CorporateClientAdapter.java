package org.example.clients.corporate;

import lombok.RequiredArgsConstructor;
import org.example.common.XmlFileUtils;
import org.example.common.ZipFileUtils;
import org.example.clients.client.OpenDartClient;
import org.example.domain.corporate.Corporate;
import org.example.domain.corporate.CorporateImportPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CorporateClientAdapter implements CorporateImportPort {

    private final OpenDartClient openDartClient;

    @Value("${download.path}")
    private String DEFAULT_DOWNLOAD_PATH;

    @Value("${download.file-path}")
    private String DEFAULT_CORPORATES_FILE_PATH;

    @Override
    public List<Corporate> loadAllCorporates() throws IOException, XMLStreamException {
        byte[] res = openDartClient.findCorporatesZipFile();
        ZipFileUtils.save(DEFAULT_DOWNLOAD_PATH, res);
        ZipFileUtils.unZip(DEFAULT_DOWNLOAD_PATH);

        var reader = XmlFileUtils.parse(DEFAULT_CORPORATES_FILE_PATH);
        List<Corporate> corporates = new ArrayList<>();

        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamReader.START_ELEMENT && "list".equals(reader.getLocalName())) {
                corporates.add(CorporateClientMapper.toDomain(reader));
            }
        }

        reader.close();

        return corporates;
    }
}
