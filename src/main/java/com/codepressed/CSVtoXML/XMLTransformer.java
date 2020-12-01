package com.codepressed.CSVtoXML;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XMLTransformer {
    //XML Generation -> Transform DOC Data to XML Format
    public static void transformDocToFile(Document xmlDoc, String xmlFile) throws TransformerException {
        TransformerFactory xmlTransformerFactory = TransformerFactory.newInstance();
        Transformer xmlTransformer = xmlTransformerFactory.newTransformer();
        xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xmlTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xmlTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        xmlTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream((new File(xmlFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        xmlTransformer.transform(new DOMSource(xmlDoc), new StreamResult(outputStream));
    }
}
