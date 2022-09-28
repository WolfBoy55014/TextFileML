package net.wolfboy.tfml;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Boolean debug = false;
        Boolean useSimpleAlgorithm = true;
        SetUpFiles.MakeFiles();

        while (true) {
            Scanner inputScanner = new Scanner(System.in);

            System.out.print("[YOU] ");
            String input = inputScanner.nextLine();
            FindAndStoreCharacterCount.MakeCharacterCount(input, debug);
            System.out.println("[BOT] " + TheAlgorithm.ChoosingResponse(debug, useSimpleAlgorithm, input));
        }
    }
}
