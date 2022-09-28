package net.wolfboy.tfml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class TheAlgorithm {
    static public String ChoosingResponse(Boolean debug, Boolean useSimpleAlgorithm, String input) throws IOException {
        File log = new File(("log.txt"));
        String character;
        String firstChar = "";
        String secondChar = "";
        String whatYOUSaid = "";
        String firstCharOfPendingResponse = "";
        String secondCharOfPendingResponse = "";
        String response = "";
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
            System.out.println("The Value of The second most Common Character (From Class: TheAlgorithm): " + secondChar);
        }

        // Saves whole Log file to ArrayList
        BufferedReader bufReader = new BufferedReader(new FileReader(log));
        ArrayList<String> wholeLog = new ArrayList<>();

        String line = bufReader.readLine();
        while (line != null) {
            wholeLog.add(line);
            line = bufReader.readLine();
        }

        bufReader.close();

        // Loops through ArrayList, looking for a Match
        if (useSimpleAlgorithm) {
            for (int i = wholeLog.size() - 1; i >= 0; i--) {
                whatYOUSaid = wholeLog.get(i);

                if (debug) {
                    System.out.println("Line Reading: " + i + ", Contents: " + wholeLog.get(i));
                }

                firstCharOfPendingResponse = String.valueOf(whatYOUSaid.charAt(2));
                secondCharOfPendingResponse = String.valueOf(whatYOUSaid.charAt(3));

                firstCharOfPendingResponse = firstCharOfPendingResponse.toLowerCase();
                secondCharOfPendingResponse = secondCharOfPendingResponse.toLowerCase();

                if (Objects.equals(firstChar, firstCharOfPendingResponse)) {
                    if (Objects.equals(secondChar, secondCharOfPendingResponse)) {
                        if (Objects.equals(whatYOUSaid.substring(0, 1), "B") || Objects.equals(whatYOUSaid.substring(0, 1), "U")) {
                            if (debug) {
                                System.out.println("BEEEP");
                                System.out.println("What we determined you said: " + whatYOUSaid.substring(5));
                            }

                            plausibleResponse = wholeLog.get(i + 1);

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
