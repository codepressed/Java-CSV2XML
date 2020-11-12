package com.codepressed.CSVtoXML;

import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        /*/
        Vars Initialization
         */
        String csvFile = args[0];
        String xmlFile = args[1];
        //String csvFile = "C:\\Users\\Dani\\src\\CodepressedConverter\\examples\\grades.csv";
        //String xmlFile = "C:\\Users\\Dani\\src\\CodepressedConverter\\examples\\gradesOutput.xml";

        /*
        ArrayList of ArrayStrings Generation with CSV
         */
        ArrayList<String[]> elements = null;
        try {
            elements = new Reader().CSVtoArrayList(csvFile);
        } catch (IOException e) {
            System.out.println("File wasn't found, error: "+e);;
        }

        /*/
        XML Doc Generation with ArrayList
         */
        Document xmlDoc = null;
        try {
            xmlDoc = new Util().docBuilder(elements);
        } catch (ParserConfigurationException e) {
            System.out.println("Configuration error: "+e);;
        }

        /*/
        Transform xml DOC to a xml FILE
         */
        try {
            Util.transform(xmlDoc,xmlFile);
        } catch (TransformerException e) {
            System.out.println("Transformer error: "+e);;
        }
    }
}
