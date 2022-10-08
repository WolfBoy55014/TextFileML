package net.wolfboy.tfml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogWriter {
    public static void WriteLineInLog(String prefix, String input) {
        FindAndStoreCharacterCount.MakeCharacterCount(input, false);
        String character;
        String firstChar = "";
        String secondChar = "";
        String outputText = "";
        Path var = Path.of("var.txt");
        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter("var.txt", true);

            // Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);

            // Writes the letter to the file
            output.write("_");

            // Writes new line before the next letter
            output.newLine();

            // Closes the writer
            output.close();

            character = Files.readAllLines(var).get(0);
            firstChar = character;
            firstChar = firstChar.toUpperCase();
            character = Files.readAllLines(var).get(1);
            secondChar = character;
            secondChar = secondChar.toUpperCase();
        } catch (IOException e) {
            System.out.println(e);
        }

        outputText = (prefix + "-" + firstChar + secondChar + ";" + input);

        LogWriter.WriteToLog(outputText);
    }

    public static void WriteToLog (String input) {
        try(FileWriter fw = new FileWriter("log.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(input);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
