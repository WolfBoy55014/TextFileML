package net.wolfboy.tfml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
                } catch (IOException e) {
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
                            } catch (IOException e) {
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

            // Clean up input
            String preparedInput = FindAndStoreCharacterCount.PrepareString(input);

            int tolerance = 0;
                for (long i = count - 1; i >= 0; i--) {

                    // Reading the Line in the Log (The turing part!)
                    try (Stream<String> lines = Files.lines(logPath)) {
                        currentLineRead = lines.skip(i).findFirst().get();
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    // Clean up read strings, and save its command type
                    String commandType = currentLineRead.substring(0, 1);
                    currentLineRead = currentLineRead.substring(5);
                    currentLineRead = FindAndStoreCharacterCount.PrepareString(currentLineRead);

                    int similarity = TheAlgorithm.getLevenshteinDistance(currentLineRead, preparedInput);

                    // The remastered "more advanced" if statement
                    if (similarity == tolerance && commandType.equals("B")) {
                        try (Stream<String> lines = Files.lines(logPath)) {
                            response = lines.skip(i + 1).findFirst().get();
                            response = response.substring(5);
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                // Whole block made to Decrease the tolerance, and start again
                } else if (i == 0) {
                        i = count - 1;
                        tolerance++;
                    }
                }


        }
        return response;
    }

    public static int getLevenshteinDistance(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();

        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            T[0][j] = j;
        }

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0: 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }

        return T[m][n];
    }
}
