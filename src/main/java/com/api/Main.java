package com.api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static String FilePath(String[] args) {
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
        StringBuilder line = new StringBuilder();
        int ch;

        try (FileReader fr = new FileReader(path)) {
            while ((ch = fr.read()) != -1) {
                line.append((char) ch);
            }
        } catch (Exception e) {
            return null;
        }
        return line.toString();
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
            System.out.println("Evaluating token: " + token); // Debugging line
            if (isNumeric(token)) {
                tempStack.push(Double.parseDouble(token));
            } else {
                Double b = tempStack.pop();
                Double a = tempStack.pop();
                if (a == null || b == null) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }
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
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
            }
        }
        if (tempStack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
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

    public static double evaluatePostfix(String[] tokens) {
        Postfix<Double> tempStack = new Postfix<>();
        for (String token : tokens) {
            System.out.println("Evaluating token: " + token); // Debugging line
            if (isNumeric(token)) {
                tempStack.push(Double.parseDouble(token));
            } else {
                Double b = tempStack.pop();
                Double a = tempStack.pop();
                if (a == null || b == null) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }
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
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
            }
        }
        if (tempStack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        return tempStack.pop();
    }

    public static void main(String[] args) {
        String filePath = FilePath(args);
        if (filePath == null) {
            System.out.println("File path not provided.");
            return;
        }

        System.out.println("Reading file: " + filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int operationNumber = 1;

            while ((line = br.readLine()) != null) {
                System.out.println("Processing line: " + line);
                String[] tokens = line.split("\\s+");

                try {
                    double result = evaluatePostfix(tokens);
                    System.out.println("Operation " + operationNumber + ": " + result);
                } catch (IllegalArgumentException e) {
                    System.out.println("Operation " + operationNumber + " is invalid: " + e.getMessage());
                }
                operationNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}