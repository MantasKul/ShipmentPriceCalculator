package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        readFile();
    }

    public static void readFile() {
        Line line = new Line();
        PriceCalculator priceCalculator = new PriceCalculator();

        try {
            URL url = Main.class.getClassLoader().getResource("input.txt");
            File file = new File(url.toURI());

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String nextLine = sc.nextLine();

                if(line.isFormatCorrect(nextLine)) {
                    // Splitting and setting the line into date, size, provider
                    line.splitLine(nextLine);

                    // Calculating price/discount
                    priceCalculator.checkForDiscount(line);

                    // Setting the date to previous date for next calculations
                    //lineManager.setPreviousDate(lineManager.getCurrentDate());

                    if(priceCalculator.getDiscount() != 0) {
                        writeFile(line + " " + priceCalculator);
                    } else {
                        writeFile(line + " " + priceCalculator);
                    }
                } else {
                    nextLine += " Ignored";
                    writeFile(nextLine);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile(String s) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\resources\\output.txt", true);
            BufferedWriter write = new BufferedWriter(fileWriter);
            write.append(s + "\n");
            write.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
