package net.wolfboy.tfml;


import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("This Version has been Modified with Py4J");

        SetUpFiles.MakeFiles();

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("  _______                _____ ");
        System.out.println(" |__   __|         /    |_   _|");
        System.out.println("    | |_   _ _ __ /  \\    | |  ");
        System.out.println("    | | | | | '__/ /  \\   | |  ");
        System.out.println("    | | |_| | | / ____ \\ _| |_ ");
        System.out.println("    |_|\\__,_|_|/_/    \\_|_____|");

        System.out.println("Welcome to the included UI. Choose a Mode.");
            System.out.print("(Writer / Conversation): ");
            String input = inputScanner.nextLine();

            if (Objects.equals(input, "Conversation")) {
                while (true) {

                    System.out.print("[YOU] ");
                    input = inputScanner.nextLine();

                    if (Objects.equals(input, "Exit")) {
                        break;
                    }

                    String output = TurAIAPI.IOHandler(input, false, false, 0);

                    System.out.println("[BOT] " + output);
                }
        } else if (Objects.equals(input, "Writer")) {
                while (true) {
                    System.out.print("Prefix Character: ");
                    String prefix = inputScanner.nextLine();
                    System.out.print("Phrase: ");
                    input = inputScanner.nextLine();
                    LogWriter.WriteLineInLog(prefix, input);

                }
            }
    }
}
