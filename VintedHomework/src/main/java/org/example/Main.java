package org.example;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

import static org.example.Constants.SHIPPING_PRICES;

public class Main {
    public static void main(String[] args) {
        readFile();
    }

    public static void readFile() {
        LineManager lineManager = new LineManager();

        try {
            URL url = Main.class.getClassLoader().getResource("input.txt");
            File file = new File(url.toURI());//new File(System.getProperty("user.dir") + "\\src\\main\\resources\\input.txt");

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String nextLine = sc.nextLine();

                if(lineManager.isLineValid(nextLine)) {
                    // Splitting and setting the line into date, size, provider
                    String[] splitLine = nextLine.split(" ", 3);
                    lineManager.setCurrentDate(splitLine[0]);
                    lineManager.setCurrentSize(splitLine[1].charAt(0));
                    lineManager.setCurrentProvider(splitLine[2]);

                    // Calculating discount
                    lineManager.calculateDiscount();

                    // Setting the date to previous date for next calculations
                    lineManager.setPreviousDate(lineManager.getCurrentDate());

                    System.out.println(nextLine + " " + lineManager.getPrice() + " " + lineManager.getDiscount());
                } else {
                    nextLine = nextLine + " Ignored";
                    // writeFile(nextLine);
                    //System.out.println(nextLine);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile() {
        // TODO: Implement writing a line to a file
    }


}

/*
    Read file
    Check if line syntax/pattern of line is correct
    keep track of month (for discount rules)
    add price to the line
    subtract price by following the rules
 */
