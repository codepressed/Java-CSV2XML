package com.codepressed.CSVtoXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Util {

    /*/
    DOC Generation -> XML with ArrayList String elements
     */
    public Document docBuilder(ArrayList<String[]> elements) throws ParserConfigurationException {
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
        Document xmlDoc = xmlBuilder.newDocument();

        Element rootElement = xmlDoc.createElement("root");
        xmlDoc.appendChild(rootElement);

        Element mainElement = xmlDoc.createElement("elements");
        rootElement.appendChild(mainElement);

        boolean headerDefined = false; //First while will be to define header
        String[] header = new String[elements.size()]; //Header initialization

        /*/
        DOC Generation -> XML Generation of every ELEMENT
        */
        for (String[] node : elements) { //FOR every ArrayString
            if (headerDefined) {
                Element nodesElements = xmlDoc.createElement("element");
                mainElement.appendChild(nodesElements);

                for (int j = 0; j < node.length; j++) {
                    node[j] = node[j].replaceAll("\"", "").trim();
                    Element nodesValues = xmlDoc.createElement(header[j]);
                    nodesElements.appendChild(nodesValues);
                    Text nodeTxt = xmlDoc.createTextNode(node[j]);
                    nodesValues.appendChild(nodeTxt);
                }
            }
            /*/
            DOC Generation -> Array Generation of every COL Name for NODES
            */
            else {
                header = node;
                for (int k = 0; k < node.length; k++) {
                    header[k] = header[k].replaceAll("[^a-zA-Z0-9]", "");
                    //We want to make sure NODE isn't NUMERIC. If it is, we make a patch
                    try {
                        Integer.parseInt(header[k]);
                        header[k] = "node" + header[k];
                    } catch (NumberFormatException e) {
                    }
                }
                headerDefined = true;
            }
        }
        return (xmlDoc);
    }

    /*/
    XML Generation -> Transform DOC Data to XML Format
     */
    public static void transform(Document xmlDoc, String xmlFile) throws TransformerException {
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

