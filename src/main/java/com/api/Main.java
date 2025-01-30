/**
 * Hoja de trabajo 2
 * Algoritmos y estructuras de datos
 * Fecha: 27 / 01 / 2025
 * Grupo 10
 */

package com.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    /**
     * Obtiene la ruta del archivo desde los argumentos del programa.
     *
     * @param args Los argumentos del programa.
     * @return La ruta del archivo si se encuentra, de lo contrario, null.
     */
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

    /**
     * Lee el contenido de un archivo y devuelve las líneas como una lista de strings.
     *
     * @param path La ruta del archivo.
     * @return una lista con las líneas del archivo o null si ocurre un error.
     */
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

    /**
     * Parsea una línea en busca de operadores postfix.
     *
     * @param line La línea a procesar.
     * @return una lista de caracteres que representan los operadores en la línea.
     */
    public static ArrayList<Character> getLineParse(String line) {
        ArrayList<Character> ops = new ArrayList<>();
        String[] tokens = line.trim().split("\\s+");
        for (String token : tokens) {
            if (token.length() == 1) {
                ops.add(token.charAt(0));
            }
        }
        return ops;
    }

    /**
     * Convierte el contenido del archivo en una lista de tokens.
     *
     * @param content Una lista de líneas del archivo.
     * @return Una lista de arrays de strings, donde cada array representa una línea tokenizada.
     */
    public static List<String[]> getContent(List<String> content) {
        return content
            .stream()
            .map(line -> line.trim().split("\\s+"))
            .collect(Collectors.toList());
    }

    /**
     * Evalúa una expresión en notación postfix y calcula su resultado.
     *
     * @param tokens Un array de strings que representan la expresión postfix.
     * @return El resultado de la evaluación.
     * @throws IllegalArgumentException Si la expresión es inválida.
     */
    public static double evaluatePostfix(String[] tokens) {
        Stack<Double> stack = new PostfixVector<>();
        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                Double b = stack.pop();
                Double a = stack.pop();

                if (a == null || b == null) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero is not allowed");
                        }
                        stack.push(a / b);
                        break;
                    case "%":
                        stack.push(a % b);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
                
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        return stack.pop();
    }

    /**
     * Comprueba si un string es un número válido.
     *
     * @param str El string a verificar.
     * @return True si el string es un número, de lo contrario false.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método principal para ejecutar el programa.
     *
     * @param args Los argumentos del programa. Se espera el argumento "-f <ruta>" para especificar un archivo.
     */
    public static void main(String[] args) {
        String filePath = FilePath(args);
        if (filePath == null) {
            System.out.println("ERROR: archivo no proporcionado");
            return;
        }

        ArrayList<String> lines = read(filePath);
        if (lines == null || lines.isEmpty()) {
            System.out.println("ERROR: el archivo está vacío.");
            return;
        }

        List<String[]> parsedLines = getContent(lines);

        int operationNumber = 1;
        for (String[] tokens : parsedLines) {
            try {
                double result = evaluatePostfix(tokens);
                System.out.println("Linea " + operationNumber + ": " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: linea " + operationNumber + " es invalida: " + e.getMessage());
            }
            operationNumber++;
        }
    }
}