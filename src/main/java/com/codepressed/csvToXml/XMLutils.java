package com.codepressed.csvToXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLutils {

    private static final Logger logger = Logger.getLogger(XMLutils.class.getName());

    private XMLutils(){
        throw new IllegalStateException("This is a utility class.");
    }

    public static boolean writeXmlDocumentToFile(Document xmlDoc, String xmlFilePath) {
        try {
            TransformerFactory xmlTransformerFactory = TransformerFactory.newInstance();
            //To protect from XXE attacks
            xmlTransformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            xmlTransformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

            Transformer xmlTransformer = xmlTransformerFactory.newTransformer();
            xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xmlTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
            xmlTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            xmlTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            try (FileOutputStream outputStream = new FileOutputStream(xmlFilePath)) {
                xmlTransformer.transform(new DOMSource(xmlDoc), new StreamResult(outputStream));
            }
            return true;

        } catch (TransformerException | IOException e) {
            logger.log(Level.SEVERE, "Error writing xml document to file", e);
            return false;
        }
    }

    public static Document createXmlDocument(List<String[]> xmlElements, String elementName) {
        try {
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = xmlBuilder.newDocument();

            Element rootElement = xmlDoc.createElement("root");
            xmlDoc.appendChild(rootElement);
            Element mainElement = xmlDoc.createElement(elementName + "s");
            rootElement.appendChild(mainElement);

            boolean headerDefined = false;
            String[] header = new String[xmlElements.size()];

            for (String[] node : xmlElements) {
                if (headerDefined) {
                    Element nodesElements = xmlDoc.createElement(elementName);
                    mainElement.appendChild(nodesElements);

                    for (int j = 0; j < node.length; j++) {
                        node[j] = node[j].replace("\"", "").trim();
                        Element nodesValues = xmlDoc.createElement(header[j]);
                        nodesElements.appendChild(nodesValues);
                        Text nodeTxt = xmlDoc.createTextNode(node[j]);
                        nodesValues.appendChild(nodeTxt);
                    }
                } else {
                    header = node;
                    for (int j = 0; j < node.length; j++) {
                        header[j] = header[j].replaceAll("[^a-zA-Z0-9]", "");
                        if(isParsableToInt(header[j])) {
                            header[j] = "node" + header[j];
                        }
                    }
                    headerDefined = true;
                }
            }
            return xmlDoc;
        } catch (ParserConfigurationException e) {
            logger.log(Level.SEVERE, "Error creating the xml document", e);
            return null;
        }
    }


    public static List<String[]> readCsvFile(String csvFilePath, String separator)  {
        List<String[]> elements = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] nodes = line.split(separator);
                elements.add(nodes);
            }
        } catch(IOException e){
            logger.log(Level.SEVERE, "Error reading the csv file", e);
        }
        return elements;
    }

    public static boolean isParsableToInt(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException e){
            logger.log(Level.INFO, "One of the columns is a int. We will add to it a 'node' prefix.");
            return false;
        }
    }

}
