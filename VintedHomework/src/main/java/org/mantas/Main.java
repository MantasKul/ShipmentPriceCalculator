package org.mantas;

import java.io.*;
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
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("input.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String nextLine;
            while((nextLine = bufferedReader.readLine()) != null) {

                if(line.isFormatCorrect(nextLine)) {
                    // Splitting and setting the line into date, size, provider
                    line.splitLine(nextLine);

                    // Calculating price/discount
                    priceCalculator.checkForDiscount(line);

                    //writeFile(line + " " + priceCalculator);
                    System.out.println(line + " " + priceCalculator);
                } else {
                    nextLine += " Ignored";
                    //writeFile(nextLine);
                    System.out.println(nextLine);
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
