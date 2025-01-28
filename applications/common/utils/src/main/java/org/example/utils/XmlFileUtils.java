package org.example.utils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlFileUtils {
    public static XMLStreamReader parse(String path) throws FileNotFoundException, XMLStreamException {
        if (!path.endsWith(".xml")) {
            throw new XMLStreamException("Invalid file format. Expected XML.");
        }

        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLStreamReader(new FileInputStream(path));
    }
}
