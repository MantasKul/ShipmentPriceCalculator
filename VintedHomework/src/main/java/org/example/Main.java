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
                String nextLine = sc.nextLine();

                if(isLineValid(nextLine)) {
                    System.out.println(nextLine + " Valid");
                } else {
                    System.out.println(nextLine + " Ignored");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to find a file");
        }
    }

    public static boolean isLineValid(String line) {
        // Regex to check if format is correct WIP: "\d{4}-\d{2}-\d{2} [S,M,L] (LP|MR)"
        if(line.matches("\\d{4}-\\d{2}-\\d{2} [S,M,L] (LP|MR)")) return true;
        return false;
    }

    public static float getMonthDiscount() {
        return 0.00f;
    }

    public static float getPrice() {
        return 0.00f;
    }
}

/*
    Read file
    Check if line syntax/pattern of line is correct
    keep track of month (for discount rules)
    add price to the line
    subtract price by following the rules
 */
