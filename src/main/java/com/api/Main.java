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

    public static void addNumber(Postfix<String> postfix, String number) {
        postfix.push(number);
    }

    public static void addOperator(Postfix<String> postfix, String operator) {
        postfix.push(operator);
    }

    public static double evaluatePostfix(Postfix<String> postfix) {
        Postfix<Double> tempStack = new Postfix<>();
        while (!postfix.empty()) {
            String token = postfix.pop();
            if (isNumeric(token)) {
                tempStack.push(Double.parseDouble(token));
            } else {
                double b = tempStack.pop();
                double a = tempStack.pop();
                switch (token) {
                    case "+":
                        tempStack.push(a + b);
                        break;
                    case "-":
                        tempStack.push(a - b);
                        break;
                    case "*":
                        tempStack.push(a * b);
                        break;
                    case "/":
                        tempStack.push(a / b);
                        break;
                    case "%":
                        tempStack.push(a % b);
                        break;
                }
            }
        }
        return tempStack.pop();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String filePath = FilePath(args);
        if (filePath == null) {
            System.out.println("File path not provided.");
            return;
        }

        String content = readline(filePath);
        if (content == null) {
            System.out.println("Error reading file.");
            return;
        }

        Postfix<String> postfix = new Postfix<>();
        String[] tokens = content.split("\\s+");

        for (String token : tokens) {
            if (isNumeric(token)) {
                addNumber(postfix, token);
            } else {
                addOperator(postfix, token);
            }
        }

        double result = evaluatePostfix(postfix);
        System.out.println("Result: " + result);
    }

}