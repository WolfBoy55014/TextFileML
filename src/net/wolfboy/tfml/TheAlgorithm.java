package net.wolfboy.tfml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class TheAlgorithm {
    static public String ChoosingResponse(Boolean debug, Boolean useSimpleAlgorithm, String input) throws IOException {
        File log = new File(("log.txt"));
        String character;
        String firstChar = "";
        String secondChar = "";
        String currentLineRead = "";
        String firstCharOfPendingResponse = "";
        String secondCharOfPendingResponse = "";
        String response = "*silence*";
        String plausibleResponse = "";
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
            character = Files.readAllLines(var).get(1);
            secondChar = character;
        } catch (IOException e) {
            System.out.println(e);
        }
        if (debug) {
            System.out.println("The Value of The First most Common Character (From Class: TheAlgorithm): " + firstChar);
            System.out.println("The Value of The Second most Common Character (From Class: TheAlgorithm): " + secondChar);
        }
        long count = 0;

        Path logPath = Path.of("log.txt");
        try {
            // make a connection to the file

            // read all lines of the file
            count = Files.lines(logPath).count();

        } catch (Exception e) {
            e.getStackTrace();
        }

        // Loops through ArrayList, looking for a Match
        if (useSimpleAlgorithm) {
            for (long i = count - 1; i >= 0; i--) {

                try (Stream<String> lines = Files.lines(logPath)) {
                    currentLineRead = lines.skip(i).findFirst().get();
                }
                catch(IOException e){
                    System.out.println(e);
                }

                if (debug) {
                    System.out.println("Line Reading: " + i + ", Contents: " + currentLineRead);
                }

                firstCharOfPendingResponse = String.valueOf(currentLineRead.charAt(2));
                secondCharOfPendingResponse = String.valueOf(currentLineRead.charAt(3));

                firstCharOfPendingResponse = firstCharOfPendingResponse.toLowerCase();
                secondCharOfPendingResponse = secondCharOfPendingResponse.toLowerCase();

                if (Objects.equals(firstChar, firstCharOfPendingResponse)) {
                    if (Objects.equals(secondChar, secondCharOfPendingResponse)) {
                        if (Objects.equals(currentLineRead.substring(0, 1), "B") || Objects.equals(currentLineRead.substring(0, 1), "U")) {
                            if (debug) {
                                System.out.println("BEEEP");
                                System.out.println("What we determined you said: " + currentLineRead.substring(5));
                            }

                            try (Stream<String> lines = Files.lines(logPath)) {
                                plausibleResponse = lines.skip(i + 1).findFirst().get();
                            }
                            catch(IOException e){
                                System.out.println(e);
                            }

                            response = plausibleResponse.substring(5);
                            break;
                        }
                    }
                }
            }
        } else {
            // ALL RIGHTY THE MEAT!

            String preparedInput = FindAndStoreCharacterCount.PrepareString(input);



        }
        return response;
    }




    static int countPairsOfStrings(String s1, int n1,
                          String s2, int n2)
    {

        // To store the frequencies of characters
        // of string s1 and s2
        int []freq1 = new int[26];
        int []freq2 = new int[26];
        Arrays.fill(freq1, 0);
        Arrays.fill(freq2, 0);

        // To store the count of valid pairs
        int i, count = 0;

        // Update the frequencies of
        // the characters of string s1
        for (i = 0; i < n1; i++)
            freq1[s1.charAt(i) - 'a']++;

        // Update the frequencies of
        // the characters of string s2
        for (i = 0; i < n2; i++)
            freq2[s2.charAt(i) - 'a']++;

        // Find the count of valid pairs
        for (i = 0; i < 26; i++)
            count += (Math.min(freq1[i], freq2[i]));

        return count;
    }


    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
