package org.example;

import java.time.LocalDate;

public class LineManager {
    private LocalDate previousDate;
    private LocalDate currentDate;

    private String currentProvider;
    private char currentSize;

    private float discountLeft = Constants.MONTHLY_DISCOUNT_AMMOUNT;
    private float discount;
    private float price = 0;

    private int largePackageStreak = 0;
    private boolean largePackageDiscountedThisMonth = false;


    public boolean isLineValid(String line) {
        // Regex to check if format is correct WIP: "\d{4}-\d{2}-\d{2} [S,M,L] (LP|MR)"
        if(line.matches("\\d{4}-\\d{2}-\\d{2} [S,M,L] (LP|MR)")) return true;
        return false;
    }

    public void calculateDiscount() {
        discount = 0;
        if(isNewMonth()) {
            discountLeft = Constants.MONTHLY_DISCOUNT_AMMOUNT;
            largePackageDiscountedThisMonth = false;
        }

        for(ShippingPrices sp : Constants.SHIPPING_PRICES) {
            //System.out.println(sp.getProvider() + " --- " + currentProvider);
            //System.out.println(sp.getSize() + " --- " + currentSize);
            if(currentProvider.equals(sp.getProvider()) && sp.getSize() == currentSize) {
                price = sp.getPrice();
            }
        }

        if(discountLeft > 0) {
            //System.out.println("CURRENT PRICE: " + price);
            if (currentSize == 'S') {
                //System.out.print("ITEM WAS S PRICE ADJUSTED. ");
                getCheapestSmall();
                //System.out.println("NEW PRICE" + price + " DISCOUNTED: " + discount);
            }

            if(currentProvider.equals("LP") && currentSize == 'L'){
                if(largePackageStreak == 2) {
                    largePackageStreak = 0;
                    largePackageDiscount();
                }
                else largePackageStreak++;
            }
        }
    }

    // If it's a new month set the discountLeft back to 10.00
    public boolean isNewMonth() {
        // previousDate will be null if it's first line entry
        if(previousDate == null) return false;
        if(previousDate.getMonth() != currentDate.getMonth() || previousDate.getYear() != currentDate.getYear()) {
            return true;
        }
        return false;
    }

    // Make sure to discount on S size packages if there's cheaper option
    public void getCheapestSmall() {
        float cheapestSmallPrice = price;
        for(ShippingPrices sp : Constants.SHIPPING_PRICES) {
            if(sp.getSize() == 'S' && sp.getPrice() < cheapestSmallPrice) {
                cheapestSmallPrice = sp.getPrice();
            }
        }

        discount = price - cheapestSmallPrice;
        if(discountLeft >= discount) {
            price -= discount;
            discountLeft -= discount;
        } else {
            discount = discountLeft;
            price -= discount;
            discountLeft = 0;
        }
    }

    public void largePackageDiscount() {
        if(discountLeft >= price) {
            discountLeft -= price;
            discount = price;
            price = 0;
        } else {
            price -= discountLeft;
            discount = discountLeft;
            discountLeft = 0;
        }
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

    // Getters & Setters
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

    public float getPrice() {
        return price;
    }
    public float getDiscount() {
        return discount;
    }
}
