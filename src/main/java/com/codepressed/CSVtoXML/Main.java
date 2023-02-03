package com.codepressed.CSVtoXML;


import org.w3c.dom.Document;
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
        if (args.length <2) {
            logger.log(Level.SEVERE, "No args were specified.");
            System.exit(1);
        }
        //Vars Initialization
        String csvFile = args[0];
        String xmlFile = args[1];
        String elementName = args.length>=3 && !args[3].equals("-s") ? args[2] : "element";
        String csvSplit = (args.length>3 && args[3].equals("-s") || args.length>4 && args[4].equals("-s")) ? ";(?=([^\"]*\"[^\"]*\")*[^\"]*$)":",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        List <String[]> elements = XMLutils.readCsvFile(csvFile, csvSplit);
        Document xmlDoc = XMLutils.createXmlDocument(elements, elementName);
        XMLutils.writeXmlDocumentToFile(xmlDoc, xmlFile);
    }
    }




