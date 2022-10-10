package net.wolfboy.tfml;

import com.sun.source.tree.WhileLoopTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        SetUpFiles.MakeFiles();

        Scanner inputScanner = new Scanner(System.in);
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
