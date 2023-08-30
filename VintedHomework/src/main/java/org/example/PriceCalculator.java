package org.example;

import java.time.LocalDate;

public class PriceCalculator {
    private int price;
    private int discount;
    private int discountLeft;
    private LocalDate previousDate;
    private boolean largeLPDiscountedThisMonth;
    private int largePackageStreak;
    private Line line;

    public PriceCalculator() {
        discountLeft = Constants.MONTHLY_DISCOUNT_AMOUNT;
        largeLPDiscountedThisMonth = false;
        largePackageStreak = 0;
    }

    public void checkForDiscount(Line line) {
        discount = 0;

        setPriceFromLine(line);

        if(isNewMonth(line.getDate())) {
            discountLeft = Constants.MONTHLY_DISCOUNT_AMOUNT;
            largeLPDiscountedThisMonth = false;
        }

        if(discountLeft > 0) {
            // All S shipments should always match the lowest S package price among the providers.
            if (line.getSize() == 'S') {
                getCheapestSmall();
            }
            // The third L shipment via LP should be free, but only once a calendar month.
            if (line.getProvider().equals("LP") && line.getSize() == 'L' && !largeLPDiscountedThisMonth) {
                if (largePackageStreak >= 2) {
                    largePackageStreak = 0;
                    largePackageDiscount();
                    largeLPDiscountedThisMonth = true;
                } else {
                    largePackageStreak++;
                }
            }
        }
    }

    // If it's a new month set the discountLeft back to 10.00
    public boolean isNewMonth(LocalDate currentDate) {
        // previousDate will be null if it's first line entry
        if(previousDate != null) {
            if (previousDate.getMonth() != currentDate.getMonth() || previousDate.getYear() != currentDate.getYear()) {
                previousDate = currentDate;
                return true;
            }
        }

        previousDate = currentDate;
        return false;
    }

    // Make sure to discount on S size packages if there's cheaper option
    public void getCheapestSmall() {
        int cheapestSmallPrice = price;

        for (ShippingInfo sp: Constants.SHIPPING_PRICES) {
            if (sp.getSize() == 'S' && sp.getPrice() < cheapestSmallPrice) {
                cheapestSmallPrice = sp.getPrice();
            }
        }

        discount = price - cheapestSmallPrice;
        if (discountLeft >= discount) {
            price -= discount;
            discountLeft -= discount;
        } else {
            discount = discountLeft;
            price -= discount;
            discountLeft = 0;
        }
    }

    public void largePackageDiscount() {
        if (discountLeft >= price) {
            discountLeft -= price;
            discount = price;
            price = 0;
        } else {
            price -= discountLeft;
            discount = discountLeft;
            discountLeft = 0;
        }
    }

    public void setPriceFromLine(Line line) {
        for (ShippingInfo sp: Constants.SHIPPING_PRICES) {
            if (line.getProvider().equals(sp.getProvider()) && sp.getSize() == line.getSize()) {
                price = sp.getPrice();
            }
        }
    }

    // Setters & Getters
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscountLeft() {
        return discountLeft;
    }

    public void setDiscountLeft(int discountLeft) {
        this.discountLeft = discountLeft;
    }

    public LocalDate getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(LocalDate previousDate) {
        this.previousDate = previousDate;
    }

    public boolean isLargeLPDiscountedThisMonth() {
        return largeLPDiscountedThisMonth;
    }

    public void setLargeLPDiscountedThisMonth(boolean largeLPDiscountedThisMonth) {
        this.largeLPDiscountedThisMonth = largeLPDiscountedThisMonth;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getPriceString() {
        String zero = price % 100 == 0 ? "0" : "";
        return price / 100 + "." + price % 100 + zero;
    }
    public String getDiscountString() {
        if (discount == 0) return "-";
        String zero = discount % 100 == 0 ? "0" : "";
        return discount / 100 + "." + discount % 100 + zero;
    }

    @Override
    public String toString() {
        return getPriceString() + " " + getDiscountString();
    }
}
