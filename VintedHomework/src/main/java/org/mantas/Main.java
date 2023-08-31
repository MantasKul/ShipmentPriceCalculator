package org.mantas;

import java.io.*;

public class Main {
  public static void main(String[] args) {
    readFile();
  }
  public static void readFile() {
    Line line = new Line();
    PriceCalculator priceCalculator = new PriceCalculator();

    try {
      FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\input.txt");
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String nextLine;
      while((nextLine = bufferedReader.readLine()) != null) {
        if (line.isFormatCorrect(nextLine)) {
          // Splitting and setting the line into date, size, provider
          line.splitLine(nextLine);

          // Calculating price/discount
          priceCalculator.checkForDiscount(line);

          System.out.println(line + " " + priceCalculator);
        } else {
          System.out.println(nextLine + " Ignored");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
