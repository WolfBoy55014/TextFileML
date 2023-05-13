package net.wolfboy.tfml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {
    public static void WriteLineInLog(String prefix, String input) {
        Parsing.MakeCharacterCount(input, false);
        String firstChar = Parsing.mostCommonCharacters.get(0).toUpperCase();
        String secondChar = Parsing.mostCommonCharacters.get(1).toUpperCase();

        String outputText = (prefix + "-" + firstChar + secondChar + ";" + input);

        LogWriter.WriteToLog(outputText);
    }

    public static void WriteToLog (String input) {
        try(FileWriter fw = new FileWriter("log.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(input);
        } catch (IOException ignored) {
        }
    }
}
