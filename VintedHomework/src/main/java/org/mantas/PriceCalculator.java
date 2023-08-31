package org.mantas;

import java.time.LocalDate;

public class PriceCalculator {
  private int price;
  private int discount;
  private int discountLeft;
  private LocalDate previousDate;
  private boolean largeLPDiscountedThisMonth;
  private int largeLPPackageStreak;

  public PriceCalculator() {
    discountLeft = Constants.MONTHLY_DISCOUNT_AMOUNT;
    largeLPDiscountedThisMonth = false;
    largeLPPackageStreak = 0;
  }

  public void checkForDiscount(Line line) {
    discount = 0;

    // Setting price for discount calculation
    setPriceFromLine(line);
    // Resetting variables that refresh each month
    resetVariablesIfNewMonth(line.getDate());

    if(discountLeft > 0) {
      // All S shipments should always match the lowest S package price among the providers.
      if (line.getSize() == 'S') {
        applySPackageDiscount();
      }
      // The third L shipment via LP should be free, but only once a calendar month.
      if (line.getProvider().equals("LP") && line.getSize() == 'L' && !largeLPDiscountedThisMonth) {
        if (largeLPPackageStreak >= 2) {
          applyThirdLargeLPPackageDiscount();
        } else {
          largeLPPackageStreak++;
        }
      }
    }
  }

  // If it's a new month set the discountLeft back to 10.00
  public void resetVariablesIfNewMonth(LocalDate currentDate) {
    // previousDate will be null if it's first line entry
    if(previousDate != null) {
      if (previousDate.getMonth() != currentDate.getMonth() || previousDate.getYear() != currentDate.getYear()) {
        discountLeft = Constants.MONTHLY_DISCOUNT_AMOUNT;
        largeLPDiscountedThisMonth = false;
      }
    }

    previousDate = currentDate;
  }

  // Make sure to discount on S size packages if there's cheaper option
  public void applySPackageDiscount() {
    int cheapestSmallPrice = price;
    // Checking if there's a provider with cheaper price than already chosen one
    for (ShippingInfo sp: Constants.SHIPPING_PRICES) {
      if (sp.getSize() == 'S' && sp.getPrice() < cheapestSmallPrice) {
        cheapestSmallPrice = sp.getPrice();
      }
    }
    // Discount will be 0 if there were no cheaper options
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

  public void applyThirdLargeLPPackageDiscount() {
    largeLPPackageStreak = 0;
    largeLPDiscountedThisMonth = true;

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

  // Data retrieval
  public String getPriceString() {
    String preDot = Integer.toString(price / 100);
    String postDot = "";//Integer.toString(price % 100);
    if(price % 100 == 0) postDot += "00";
    if(price % 100 < 10 && price % 100 > 0) postDot += "0" + price % 100;
    if(price % 100 >= 10) postDot += Integer.toString(price % 100);
    return preDot + "." + postDot;
  }

  public String getDiscountString() {
    if (discount == 0) return "-";

    String preDot = Integer.toString(discount / 100);
    String postDot = "";//Integer.toString(price % 100);
    if(discount % 100 == 0) postDot += "00";
    if(discount % 100 < 10 && discount % 100 > 0) postDot += "0" + discount % 100;
    if(discount % 100 >= 10) postDot += Integer.toString(discount % 100);
    return preDot + "." + postDot;
  }

  @Override
  public String toString() {
    return getPriceString() + " " + getDiscountString();
  }
}
