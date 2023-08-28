package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineManager {
    private LocalDate previousDate;
    private LocalDate currentDate;
    private float discountLeft = Constants.MONTHLY_DISCOUNT_AMMOUNT;
    private String currentProvider;
    private char currentSize;

    public boolean isLineValid(String line) {
        // Regex to check if format is correct WIP: "\d{4}-\d{2}-\d{2} [S,M,L] (LP|MR)"
        if(line.matches("\\d{4}-\\d{2}-\\d{2} [S,M,L] (LP|MR)")) return true;
        return false;
    }

    public void calculateDiscount() {
        if(isNewMonth()) {
            discountLeft = Constants.MONTHLY_DISCOUNT_AMMOUNT;
        }
    }

    public boolean isNewMonth() {
        // previousDate will be null if it's first line entry
        if(previousDate == null) return false;
        if(previousDate.getMonth() != currentDate.getMonth() || previousDate.getYear() != currentDate.getYear()) {
            return true;
        }
        return false;
    }

/*    public void setCurrentDate(String s) throws ParseException {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(s);

        if(matcher.find()) {
            currentDate = LocalDate.parse(matcher.group());
        }
    }

    public void setCurrentProvider(String s) {
        Pattern pattern = Pattern.compile("( LP| MR)");
        Matcher matcher = pattern.matcher(s);

        if(matcher.find()) {
            currentProvider = matcher.group();
        }
    }

    public void setCurrentSize(String s) {
        Pattern pattern = Pattern.compile("( S | M | L )");
        Matcher matcher = pattern.matcher(s);

        if(matcher.find()) {
            currentSize = matcher.group().charAt(0);
        }
    }*/

    public void setCurrentDate(String s) {
        currentDate = LocalDate.parse(s);
    }
    public void setPreviousDate(LocalDate d) {
        previousDate = d;
    }
    public void setCurrentSize(char c) {
        currentSize = c;
    }
    public void setCurrentProvider(String s) {
        currentProvider = s;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public String getCurrentProvider() {
        return currentProvider;
    }

    public char getCurrentSize() {
        return currentSize;
    }

    public LocalDate getPreviousDate() {
        return previousDate;
    }
}
