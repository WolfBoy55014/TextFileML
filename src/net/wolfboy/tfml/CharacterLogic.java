package net.wolfboy.tfml;

import java.io.*;
import java.util.*;


public class CharacterLogic {
    public static void MakeCharacterCount(String input) {
        // Clears var file

        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter("var.txt");

            // Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);

            // Clears the file
            output.write("");

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }

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

        // Count Frequency of Characters
        HashMap<Character, Integer> CharacterCount = new HashMap<Character, Integer>();
        String s = input;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            CharacterCount.merge(c, 1, (a, b) -> a + b);
        }

        // Prints HashMap
        System.out.println(CharacterCount);

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
        try {
            String inputFile = "var.txt";
            String outputFile = "var.txt";

            FileReader reader = new FileReader(inputFile);
            BufferedReader bufferedreader = new BufferedReader(reader);
            String inputLine;
            List<String> lineList = new ArrayList<String>();
            while ((inputLine = bufferedreader.readLine()) != null) {
                lineList.add(inputLine);
            }
            reader.close();

            Collections.sort(lineList);

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
}