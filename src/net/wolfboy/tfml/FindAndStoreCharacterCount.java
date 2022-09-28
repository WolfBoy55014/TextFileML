package net.wolfboy.tfml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FindAndStoreCharacterCount {
    public static void MakeCharacterCount(String input, Boolean debug) {
        // Clears var files

        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter("var.txt");

            // Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);

            // Clears the file
            output.write("");

            // Closes the writer
            output.close();

            // The Second one

            // Creates a FileWriter
            FileWriter file2 = new FileWriter("var2.txt");

            // Creates a BufferedWriter
            BufferedWriter output2 = new BufferedWriter(file2);

            // Clears the file
            output2.write("");

            // Closes the writer
            output2.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }

        input = FindAndStoreCharacterCount.PrepareString(input);

        // Count Frequency of Characters
        HashMap<Character, Integer> CharacterCount = new HashMap<Character, Integer>();
        String s = input;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            CharacterCount.merge(c, 1, Integer::sum);
        }

        // Prints HashMap if debug mode is on
        if (debug) {
            System.out.println("The Character Count of Input: " + CharacterCount);
        }

        // Finds most common Character
        int maxValueInMap = (Collections.max(CharacterCount.values()));
        for (Map.Entry<Character, Integer> entry :
                CharacterCount.entrySet()) {

            String data = Character.toString(entry.getKey());
            if (entry.getValue() == maxValueInMap) {
                try {
                    // Creates a FileWriter
                    FileWriter file = new FileWriter("var.txt", true);

                    // Creates a BufferedWriter
                    BufferedWriter output = new BufferedWriter(file);

                    // Writes the letter to the file
                    output.write(data);

                    // Writes new line before the next letter
                    output.newLine();

                    // Closes the writer
                    output.close();
                }

                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        for (Map.Entry<Character, Integer> entry :
                CharacterCount.entrySet()) {

            String data = Character.toString(entry.getKey());
            if (entry.getValue() == maxValueInMap - 1) {
                try {
                    // Creates a FileWriter
                    FileWriter file = new FileWriter("var2.txt", true);

                    // Creates a BufferedWriter
                    BufferedWriter output = new BufferedWriter(file);

                    // Writes the letter to the file
                    output.write(data);

                    // Writes new line before the next letter
                    output.newLine();

                    // Closes the writer
                    output.close();
                }

                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }

        try {
            String inputFile = "var.txt";
            String outputFile = "var.txt";

            FileReader file = new FileReader(inputFile);
            BufferedReader output = new BufferedReader(file);
            String inputLine;
            List<String> lineList = new ArrayList<String>();
            while ((inputLine = output.readLine()) != null) {
                lineList.add(inputLine);
            }
            file.close();

            // The actual sorting
            Collections.sort(lineList);
            // adding a second character if only one character
            if (lineList.size() < 2) {
                String character = "";
                Path var2 = Path.of("var2.txt");
                try {
                    character = Files.readAllLines(var2).get(0);
                } catch (IOException e) {
                    System.out.println(e);
                }

                lineList.add(character);
            }

            FileWriter writer = new FileWriter(outputFile);
            PrintWriter out = new PrintWriter(writer);
            for (String outputLine : lineList) {
                out.println(outputLine);
            }
            out.flush();
            out.close();
            writer.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static String PrepareString(String input) {
        // Make all characters lowercase
        input = input.toLowerCase();

        // Removes all unwanted characters
        input = input.replace(" ", "");
        input = input.replace(",", "");
        input = input.replace("'", "");
        input = input.replace(".", "");
        input = input.replace("!", "");
        input = input.replace("?", "");
        input = input.replace("|", "");
        input = input.replace("’", "");

        return input;
    }

}