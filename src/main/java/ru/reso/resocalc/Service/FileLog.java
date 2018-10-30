package ru.reso.resocalc.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class FileLog {





    public void appendLogEntry(String text) {

        Path filePath = Paths.get("D:/resocalc_CHECK.txt");
        text=text+"\n";
        try {
        if (!Files.exists(filePath)) {
                Files.createFile(filePath);
        }

            Files.write(filePath, text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void addLogEntry(String text) {

        try {
            //String str = "SomeMoreTextIsHere";
            File newTextFile = new File("D:/log.txt");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(text + "\n");
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }


    }

    public void addLogEntry2(String text) {

        try {

            File newTextFile = new File("D:/logKate.txt");
            FileWriter fw = new FileWriter(newTextFile);
            fw.write(text + "\n");
            fw.close();

        } catch (IOException iox) {

            iox.printStackTrace();
        }


    }

    public void addLogHashMap(String panelName, HashMap<String, String> map) {
        String text = "";
        Path filePath = Paths.get("D:/logHash.txt");
        try {

            //File newTextFile = new File("D:/logHash.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            //FileWriter fw = new FileWriter(newTextFile);

            for (String key : map.keySet()) {
                text = "Panel: " + panelName + " .Key: " + key + ", Value: " + map.get(key) + "\n" ;
                Files.write(filePath, text.getBytes(), StandardOpenOption.APPEND);
            }


            //fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }


    }

    public void saveAllReportPanels(String reportName, ArrayList<String> panels) throws IOException {


        String file = "D:/reportPanels.txt";
        ObjectOutput out = null;


        FileWriter writer = new FileWriter(file);
        int size = panels.size();
        for (int i=0;i<size;i++) {
            String str = panels.get(i).toString();
            writer.write(str+ "\n");

            if(i < size-1)
            writer.write("\n");
        }

        writer.write("CURRENT REPORT = " + reportName  + "\n");
        writer.close();


    }
    public void saveAllReportPanels2(String reportName, ArrayList<String> panels) throws IOException {


        String file = "D:/reportPanels2.txt";
        ObjectOutput out = null;


        FileWriter writer = new FileWriter(file);
        int size = panels.size();
        for (int i=0;i<size;i++) {
            String str = panels.get(i).toString();
            writer.write(str+ "\n");

            if(i < size-1)
                writer.write("\n");
        }

        writer.write("CURRENT REPORT = " + reportName  + "\n");
        writer.close();


    }


}
