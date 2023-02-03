package com.codepressed.CSVtoXML;


import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Executes the CSV to XML conversion
     *
     * @param args [0] = Input file, [1] = Output file, [2] = Elements names, [3] = csv type.
     * @author Daniel Apesteguia Timoner (Codepressed)
     */
    public static void main(String[] args) {

        //Arg validator
        if (args.length == 0) {
            logger.log(Level.SEVERE, "No args were specified.");
            System.exit(0);
        }
        //Vars Initialization
        String csvFile = args[0];
        String xmlFile = args[1];
        String elementName;
        String csvSplit = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        try {
            elementName = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.INFO, "Since you didn't specify a element name, 'element' will be the parental node.");
            elementName = "element";
        }

        try {
            if (args[3] == "-s")
                csvSplit = ";(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        } catch (ArrayIndexOutOfBoundsException e){
        }

                List<String[]> elements;
                elements = XMLutils.readCsvFile(csvFile, csvSplit);
                Document xmlDoc;
                xmlDoc = XMLutils.createXmlDocument(elements, elementName);
                XMLutils.writeXmlDocumentToFile(xmlDoc, xmlFile);
            }
    }




