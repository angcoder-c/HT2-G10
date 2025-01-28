package com.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

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

    public static ArrayList<String> read(String path) {
        ArrayList<String> content = new ArrayList<>();    

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (Exception e) {
            return null;
        }
        return content;
    }
    

    public static ArrayList<Character> getLineParse(String line) {
        ArrayList<Character> ops = new ArrayList<Character>();
        
        String[] tokens = line.trim().split("\\s+");
        
        for (String token : tokens) {
            if (token.length() == 1) {
                ops.add(token.charAt(0));
            }
        }
        
        return ops;
    }

    public static List<Character[]> getContent (String[] args) {
        List<Character[]> content = read(FilePath(args))
        .stream()
        .map(line -> getLineParse(line).toArray(new Character[0]))
        .collect(Collectors.toList());

        return content;
    }
    public static void main(String[] args) {
        List<Character[]> content = getContent(args);
    }
}