package net.wolfboy.tfml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetUpFiles {
    // Makes a temp file for variable saving (silly, right?)
    public static void MakeFiles() {
        try {
            File file = new File("var.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File("var2.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
