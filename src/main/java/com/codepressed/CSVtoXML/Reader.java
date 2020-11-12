package com.codepressed.CSVtoXML;

import java.io.*;
import java.util.ArrayList;


public class Reader {

    /*/
    ArrayList Generation ArrayStrings  with the CSV Data
     */
    public ArrayList<String[]> CSVtoArrayList(String csvFile) throws IOException {
        ArrayList<String[]> elements = new ArrayList<String[]>();
        String csvSplit = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        String line = "";
        BufferedReader csvReader = null;

        try {
            csvReader = new BufferedReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while ((line = csvReader.readLine()) != null) {
            String[] nodes = line.split(csvSplit);
            elements.add(nodes);
        }

        return elements;
    }


}