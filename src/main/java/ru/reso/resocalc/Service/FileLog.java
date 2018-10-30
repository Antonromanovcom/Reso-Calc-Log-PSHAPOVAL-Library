package ru.reso.resocalc.Service;

import ru.reso.resocalc.Entity.MyStmtParamList;

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
        text = text + "\n";
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
            File newTextFile = new File("D:/log777.txt");

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
                text = "Panel: " + panelName + " .Key: " + key + ", Value: " + map.get(key) + "\n";
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
        for (int i = 0; i < size; i++) {
            String str = panels.get(i).toString();
            writer.write(str + "\n");

            if (i < size - 1)
                writer.write("\n");
        }

        writer.write("CURRENT REPORT = " + reportName + "\n");
        writer.close();


    }

    public void saveAllReportPanels2(ArrayList<String> panels) throws IOException {


        String file = "D:/reportPanels2.txt";
        ObjectOutput out = null;


        FileWriter writer = new FileWriter(file);
        int size = panels.size();
        for (int i = 0; i < size; i++) {
            String str = panels.get(i).toString();
            writer.write(str + "\n");

            if (i < size - 1)
                writer.write("\n");
        }

        //writer.write("CURRENT REPORT = " + reportName  + "\n");
        writer.close();


    }

    public void saveAllReportPanels3(MyStmtParamList paramList) throws IOException {

        String varName = "";
        String varType = "";
        Object varValue;
        String varValueStr;
        String separator = " - ";
        String str = "";

        String file = "D:/log777_7.txt";
        ObjectOutput out = null;
        deleteFileIfExist(file);


        FileWriter writer = new FileWriter(file);


        int size = paramList.size();
        for (int i = 0; i < size; i++) {
            varName = paramList.get(i).getFieldName();
            varType = paramList.get(i).getFieldType();
            varValue = paramList.get(i).getValue();
            varValueStr = String.valueOf(varValue);
            str = varName + separator + varType + separator + varValue;
            writer.write(str + "\n");

            if (i < size - 1)
                writer.write("\n");
        }

        //writer.write("CURRENT REPORT = " + reportName  + "\n");
        writer.close();

    }


    private static void deleteFileIfExist(String file) throws IOException {

        File f = new File(file);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }


    }


}
