package net.wolfboy.tfml;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SetUpFiles.MakeFiles();

        Scanner inputScanner = new Scanner(System.in);

        System.out.print("[YOU] ");
        String input = inputScanner.nextLine();
        CharacterLogic.MakeCharacterCount(input);



        //System.out.println("[BOT] " + input);
    }
}
