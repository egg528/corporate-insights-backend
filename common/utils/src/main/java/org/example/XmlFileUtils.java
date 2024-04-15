package org.example;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XmlFileUtils {
    public static void read(String path) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(path));

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    // 시작 태그 처리
                    System.out.println("Start Element: " + reader.getLocalName());
                    break;
                case XMLStreamConstants.CHARACTERS:
                    // 태그 사이의 문자 데이터 처리
                    String text = reader.getText().trim();
                    if (!text.isEmpty()) {
                        System.out.println("Text: " + text);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    // 종료 태그 처리
                    System.out.println("End Element: " + reader.getLocalName());
                    break;
            }
        }

        reader.close();
    }
}
