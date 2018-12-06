package stas.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BufferedReader_BufferedWriter {
    public static List<String> readFile(File source) throws IOException {
        List<String> data = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String s;
            while ((s = reader.readLine()) != null) { /// readLine() method of BufferedReader
                data.add(s);
            }
        }
        return data;
    }

    public static void writeFile(List<String> data, File destination) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            for (String s : data) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File("//home/st/zoo.txt");
        File destination = new File("//home/st/zooCopy.txt");
        Charset utf8Charset = Charset.forName("UTF-8");
        List<String> data = readFile(source);
        for (String record : data) {
            System.out.println(record);
        }
        writeFile(data, destination);
    }
}