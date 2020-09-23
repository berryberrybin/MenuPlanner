package com.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TsvToDml {

    public static void main(String[] arg) throws Exception {

        StringTokenizer st;
        FileInputStream input = new FileInputStream("db/raw_menu.tsv");
        InputStreamReader reader = new InputStreamReader(input, "UTF-8");
        BufferedReader TSVFile = new BufferedReader(reader);
        FileOutputStream output = new FileOutputStream("db/menu_dml.txt", false);
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        BufferedWriter out = new BufferedWriter(writer);
        String dataRow = TSVFile.readLine(); // Read first line.

        while (dataRow != null) {
            String[] array = dataRow.split("\t");
            out.write("INSERT INTO menu " +
                    "(name,country,category,recipe,ingredient,color) " +
                    "VALUES("
                    + "'" + array[0] + "','" + array[1] + "'," + array[2] + "," + array[3] + "," + array[4] + ",'" + array[5] + "');");

            out.write("\n");
            dataRow = TSVFile.readLine(); // Read next line of data.
        }
        // Close the file once all data has been read.
        TSVFile.close();
        out.close();

        // End the printout with a blank line.
        System.out.println();

    } //main()
} // TSVRead