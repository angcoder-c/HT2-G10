package com.api;
import java.io.FileReader;

public class Main {
    public static String FilePath (String[] args) {
        String filePath = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f") && i + 1 < args.length) {
                filePath = args[i + 1];
                break;
            }
        }
        return filePath;
    }

    public static String readline(String path) {
        String line = null;
        int ch;

        try (FileReader fr = new FileReader(path)) {
            while ((ch=fr.read())!=-1) {
                line += (char) ch;
            }
        } catch (Exception e) {
            return null;
        }
        return line;
    }
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}