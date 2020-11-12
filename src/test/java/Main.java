import org.junit.Test;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.ArrayList;



public class Main {
    @Test
    public void FinalCSVReader() throws IOException, ParserConfigurationException, TransformerException {
        //Initialization
        String csvFile = "C:\\Users\\Dani\\testing\\grades.csv";
        String line = "";
        String csvSplit = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        //CSV BufferedReader
        BufferedReader csvReader;
        csvReader = new BufferedReader(new FileReader(csvFile));

        //ArrayList of ArrayStrings
        ArrayList<String[]> elements = new ArrayList<String[]>();

        //CSV to ArrayList of ArrayStrings
        while ((line = csvReader.readLine()) != null) {
            String[] nodes = line.split(csvSplit);
            elements.add(nodes);
        }

        //XML Root elements generation
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
        Document xmlDoc = xmlBuilder.newDocument();

        Element rootElement = xmlDoc.createElement("root");
        xmlDoc.appendChild(rootElement);

        Element mainElement = xmlDoc.createElement("elements");
        rootElement.appendChild(mainElement);


        boolean headerDefined = false; //First while will be to define header
        String[] header = new String[elements.size()]; //Header initialization

        //XML Node elements generation
        for (String[] node : elements) { //FOR every ArrayString
            if (headerDefined) {
                Element nodesElements = xmlDoc.createElement("element");
                mainElement.appendChild(nodesElements);

                for (int j = 0; j < node.length; j++) {
                    node[j] = node[j].replaceAll("\"", "").trim();
                    //System.out.println(node[j]);
                    //System.out.println(header[j] + " owo");
                    Element nodesValues = xmlDoc.createElement(header[j]);
                    nodesElements.appendChild(nodesValues);
                    Text nodeTxt = xmlDoc.createTextNode(node[j]);
                    nodesValues.appendChild(nodeTxt);
                }
            }
            //Header generation
            else{
                header = node; //Header has a independent array
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

        //XML File OutputFormat
        TransformerFactory xmlTransformerFactory = TransformerFactory.newInstance();
        Transformer xmlTransformer = xmlTransformerFactory.newTransformer();
        xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xmlTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xmlTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        xmlTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        FileOutputStream outputStream = new FileOutputStream((new File("C:\\Users\\Dani\\testing\\XMLoutput.xml")));
        xmlTransformer.transform(new DOMSource(xmlDoc), new StreamResult(outputStream));


    }
}
