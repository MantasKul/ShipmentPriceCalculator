package org.example;

import java.util.Date;

public class LineManager {
    private Date currentDate;
    private float discountLeft;

    public boolean isLineValid(String line) {
        // Regex to check if format is correct WIP: "\d{4}-\d{2}-\d{2} [S,M,L] (LP|MR)"
        if(line.matches("\\d{4}-\\d{2}-\\d{2} [S,M,L] (LP|MR)")) return true;
        return false;
    }

    public float getMonthDiscount() {
        return 0.00f;
    }

    public float getPrice() {
        return 0.00f;
    }
}
