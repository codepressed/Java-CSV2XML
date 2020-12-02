package com.codepressed.CSVtoXML;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public class XMLDoc {

    /**
     *
     * @param XMLelements ArrayList of CSV Values
     * @param elementName Name fixed for node tree
     * @return Final doc
     * @throws ParserConfigurationException
     */
    public Document docBuilder(ArrayList<String[]> XMLelements, String elementName) throws ParserConfigurationException {
        if (elementName == null){
            elementName = "element";
        }
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
        Document xmlDoc = xmlBuilder.newDocument();

        Element rootElement = xmlDoc.createElement("root");
        xmlDoc.appendChild(rootElement);
        Element mainElement = xmlDoc.createElement(elementName+"s");
        rootElement.appendChild(mainElement);

        boolean headerDefined = false;
        String[] header = new String[XMLelements.size()];

        //DOC Generation -> XML Generation of every ELEMENT
        for (String[] node : XMLelements) {
            if (headerDefined) {
                Element nodesElements = xmlDoc.createElement(elementName);
                mainElement.appendChild(nodesElements);

                for (int j = 0; j < node.length; j++) {
                    node[j] = node[j].replaceAll("\"", StringUtils.EMPTY).trim();
                    Element nodesValues = xmlDoc.createElement(header[j]);
                    nodesElements.appendChild(nodesValues);
                    Text nodeTxt = xmlDoc.createTextNode(node[j]);
                    nodesValues.appendChild(nodeTxt);
                }
            }
            //DOC Generation -> Array Generation of every COL Name for NODES
            else {
                header = node;
                for (int j = 0; j < node.length; j++) {
                    header[j] = header[j].replaceAll("[^a-zA-Z0-9]", StringUtils.EMPTY);
                    //Avoid a fullint
                    try {
                        Integer.parseInt(header[j]);
                        header[j] = "node" + header[j];
                    } catch (NumberFormatException e) {
                    }
                }
                headerDefined = true;
            }
        }
        return (xmlDoc);
    }

}

