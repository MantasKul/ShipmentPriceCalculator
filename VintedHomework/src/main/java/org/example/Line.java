package org.example;

import java.time.LocalDate;

public class Line {
    private LocalDate date;
    private char size;
    private String provider;

    public boolean isFormatCorrect(String line) {
        if (line.matches("\\d{4}-\\d{2}-\\d{2} [S,M,L] (LP|MR)")) return true;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return date + " " +
                size + " " +
                provider;
    }
}
