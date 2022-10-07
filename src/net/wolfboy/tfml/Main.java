package net.wolfboy.tfml;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        SetUpFiles.MakeFiles();

        while (true) {
            Scanner inputScanner = new Scanner(System.in);

            System.out.print("[YOU] ");
            String input = inputScanner.nextLine();

            String output = TurAIAPI.IOHandler(input, false, true);

            System.out.println("[BOT] " + output);
        }
    }
}
