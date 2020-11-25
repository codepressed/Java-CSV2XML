package com.codepressed.CSVtoXML;

import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /**
     * Executes the CSV to XML conversion
     * @author Daniel Apesteguia Timoner
     * @param args Input file, output file and elements names.
     */
    public static void main(String[] args){
        //Arg validator
        if(args.length == 0)
        {
            System.out.println("You didn't type any args.");
            System.exit(0);
        }
        try {

        //Vars Initialization
        String csvFile = args[0];
        String xmlFile = args[1];
        String elementName;
        try{
        elementName = args[2];}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You didn't especify any element so we will fix 'element' as parental node.");
            elementName = "element";
            }


        //ArrayList of ArrayStrings Generation with CSV
        ArrayList<String[]> elements;
            elements = new Reader().CSVtoArrayList(csvFile);

        //XML Doc Generation with ArrayList
        Document xmlDoc;
            xmlDoc = new TabularToXMLConverter().docBuilder(elements,elementName);
            TabularToXMLConverter.transformDocToFile(xmlDoc,xmlFile);

        } catch (IOException e) {
            e.printStackTrace();
        System.out.println("File wasn't found, error: "+e);
        }
        catch (TransformerException e){
            System.out.println("Transformer error: "+e);
        }
        catch (ParserConfigurationException e) {
            System.out.println("Configuration error: "+e);
        }
    }
}
