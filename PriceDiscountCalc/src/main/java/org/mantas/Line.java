package org.mantas;

import java.time.LocalDate;

public class Line {
  private LocalDate date;
  private char size;
  private String provider;

  public boolean isFormatCorrect(String line) {
    if (line.matches("\\d{4}-\\d{2}-\\d{2} [SML] (LP|MR)")) return true;
    return false;
  }

  public void splitLine(String line) {
    String[] splitLine = line.split(" ", 3);
    date = LocalDate.parse(splitLine[0]);
    size = splitLine[1].charAt(0);
    provider = splitLine[2];
  }

  // Getters & Setters
  public LocalDate getDate() {
    return date;
  }

  public char getSize() {
    return size;
  }

  public String getProvider() {
    return provider;
  }

  @Override
  public String toString() {
    return date + " " +
            size + " " +
            provider;
  }
}
