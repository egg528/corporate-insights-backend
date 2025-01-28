package org.example.clients.corporate;

import org.example.domain.corporate.Corporate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CorporateClientMapper {
    private static final DateTimeFormatter MODIFY_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private CorporateClientMapper() {}

    public static Corporate toDomain(XMLStreamReader reader) throws XMLStreamException {
        Objects.requireNonNull(reader, "XMLStreamReader must not be null");
        String corpCode = null, corpName = null, stockCode = null, modifyDate = null;

        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamReader.START_ELEMENT -> {
                    String elementName = reader.getLocalName();
                    switch (elementName) {
                        case "corp_code" -> corpCode = reader.getElementText();
                        case "corp_name" -> corpName = reader.getElementText();
                        case "stock_code" -> stockCode = reader.getElementText();
                        case "modify_date" -> modifyDate = reader.getElementText();
                    }
                }
                case XMLStreamReader.END_ELEMENT -> {
                    if ("list".equals(reader.getLocalName())) {
                        return new Corporate(
                                corpCode,
                                stockCode.isBlank() ? null : stockCode,
                                corpName,
                                LocalDate.parse(modifyDate, MODIFY_DATE_FORMATTER)
                        );
                    }
                }
            }
        }
        throw new IllegalStateException("Unexpected end of list element");
    }
}
