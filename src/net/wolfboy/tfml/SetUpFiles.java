package net.wolfboy.tfml;

import java.io.File;
import java.io.IOException;

public class SetUpFiles {
    // Makes a temp file for variable saving (silly, right?)
    public static void MakeFiles() {
        try {
            File file = new File("log.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
