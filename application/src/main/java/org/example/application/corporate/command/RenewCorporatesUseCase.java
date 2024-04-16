package org.example.application.corporate.command;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface RenewCorporatesUseCase {

    void renew() throws XMLStreamException, IOException;
}
