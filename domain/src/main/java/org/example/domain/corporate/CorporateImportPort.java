package org.example.domain.corporate;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface CorporateImportPort {

    // TODO: Memory 사용량 확인해보고 너무 크다면 Stream으로 변경
    List<Corporate> loadAllCorporates() throws IOException, XMLStreamException;
}
