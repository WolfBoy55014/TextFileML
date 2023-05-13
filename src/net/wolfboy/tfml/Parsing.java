package net.wolfboy.tfml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {
    public static List<String> mostCommonCharacters = new ArrayList<>();

    public static void MakeCharacterCount(String input, Boolean debug) {

        /*
        Here we will--

        1. Delete previous characters
        2. Clean prompt
        3. Count the frequency of every character in the prompt
        4. Identify the most common characters
        5. Sort the characters alphabetically
        6. Add a second-most-frequent-character if there are too few most-frequent-characters
         */

        // 1. Delete old info
        mostCommonCharacters.clear();

        // 2. Removing all unwanted characters, spaces, and capitalization
        input = Parsing.PrepareString(input);

        // 3. Here we are counting the characters in the prompt
        HashMap<Character, Integer> CharacterCount = new HashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            CharacterCount.merge(c, 1, Integer::sum);
        }

        // #DEBUG# // Print Character Count
        if (debug) {
            System.out.println("The Character Count of Prompt: " + CharacterCount);
        }

        // 4. Identifying most frequent Character
        int max = (Collections.max(CharacterCount.values())); // The Max Count of a Character in The Map

        // For every entry in the map, check if it has the same frequency as the max
        for (Map.Entry<Character, Integer> entry :
                CharacterCount.entrySet()) {

            String data = Character.toString(entry.getKey()); // Get the current character's frequency

            // If the character is, or has the same amount as the most frequent character, store it to the list
            if (entry.getValue() == max) {
                mostCommonCharacters.add(data);
            }
        }

        // 5. Sort the list of characters alphabetically
        Collections.sort(mostCommonCharacters);

        // 6. Adding a second character if only one character
        if (mostCommonCharacters.size() < 2) {

            // Similar to earlier, just grabbing a second most common character
            for (Map.Entry<Character, Integer> entry :
                    CharacterCount.entrySet()) {

                String data = Character.toString(entry.getKey());// Get the current character's frequency

                // If the character is, or has the same amount as the second most frequent character, add it to the list
                if (entry.getValue() == max - 1) {
                    mostCommonCharacters.add(data);
                    break; // Leave; we only need one
                }
            }
            mostCommonCharacters.add("_"); // When there is not an exact second place, add a placeholder
        }

        // #DEBUG# // Print Character Count
        if (debug) {
            System.out.println("Using Characters: " + mostCommonCharacters);
        }

    }

    public static String PrepareString(String input) {
        // Make all characters lowercase
        input = input.toLowerCase();

        // Removes all unwanted characters
        input = input.replace(" ", "");
        input = input.replace(",", "");
        input = input.replace("'", "");
        input = input.replace(".", "");
        input = input.replace("!", "");
        input = input.replace("?", "");
        input = input.replace("|", "");
        input = input.replace("’", "");

        return input;
    }
}
