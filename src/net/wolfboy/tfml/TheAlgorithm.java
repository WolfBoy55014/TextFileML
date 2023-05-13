package net.wolfboy.tfml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class TheAlgorithm {
    static public String ChoosingResponse(Boolean debug, Boolean useSimpleAlgorithm, String input, int initialTolerance) throws IOException {
        String currentLineRead = "";
        String firstCharOfPendingResponse = "";
        String secondCharOfPendingResponse = "";
        String response = "*silence*";
        String plausibleResponse = "";
        String commandType = "";

        String firstChar = "";
        String secondChar = "";

        if (useSimpleAlgorithm) {
            firstChar = Parsing.mostCommonCharacters.get(0).toLowerCase();
            secondChar = Parsing.mostCommonCharacters.get(1).toLowerCase();
            if (debug) {
                System.out.println("The Value of The First most Common Character (From Class: TheAlgorithm): " + firstChar);
                System.out.println("The Value of The Second most Common Character (From Class: TheAlgorithm): " + secondChar);
            }
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
                                System.out.println("We determined you said: " + currentLineRead.substring(5));
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

            long startingPoint = getRandomNumber(1, count);

            // Clean up input
            String preparedInput = Parsing.PrepareString(input);

            int tolerance = initialTolerance;
                for (long i = startingPoint - 1; i >= 0; i--) {

                    // Reading the Line in the Log (The turing part!)
                    try (Stream<String> lines = Files.lines(logPath)) {
                        currentLineRead = lines.skip(i).findFirst().get();
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    // Clean up read strings, and save its command type
                    commandType = currentLineRead.substring(0, 1);
                    currentLineRead = currentLineRead.substring(5);
                    if (debug) {
                        System.out.println("Contents of Line: " + currentLineRead);
                    }
                    currentLineRead = Parsing.PrepareString(currentLineRead);

                    int similarity = TheAlgorithm.getLevenshteinDistance(currentLineRead, preparedInput);
                    if (debug) {
                        System.out.println("Similarity: " + similarity);
                    }

                    // The remastered "more advanced" if statement
                    if (similarity <= tolerance && !commandType.equals("H")) {
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
        if (!commandType.equals("U")) {
            LogWriter.WriteLineInLog("H", input);
            LogWriter.WriteLineInLog("B", response);
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

    public static long getRandomNumber(long min, long max) {
        return Math.round ((int) ((Math.random() * (max - min)) + min));
    }
}
