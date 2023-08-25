package org.example;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        readFile();
    }

    public static void readFile() {
        try {
            URL url = Main.class.getClassLoader().getResource("input.txt");
            File file = new File(url.toURI());//new File(System.getProperty("user.dir") + "\\src\\main\\resources\\input.txt");

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to find a file");
        }
    }
}

/*
    Read file
    Check if line syntax/pattern of line is correct
    keep track of month (for discount rules)
    add price to the line
    subtract price by following the rules
 */
