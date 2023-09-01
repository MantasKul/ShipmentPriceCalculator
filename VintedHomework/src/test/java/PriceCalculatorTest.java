/*
    Name format:
    methodName_StateUnderTest_ExpectedResult
 */

import org.junit.jupiter.api.Test;
import org.mantas.PriceCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {
  @Nested
  @DisplayName("Testing small package discount calculation")
  class applySPackageDiscountTests {
    @Test
    public void applySPackageDiscount_givenNotCheapestS_discounted() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 200);

      Field discountLeftField = pc.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(pc, 10000);

      int expectedPrice = 150;
      int expectedDiscountLeft = 9950;

      pc.applySPackageDiscount();
      int actualPrice = priceField.getInt(pc);
      int actualDiscountLeft = discountLeftField.getInt(pc);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }

    @Test
    public void applySPackageDiscount_givenCheapestSPackage_notDiscounted() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 150);

      Field discountLeftField = pc.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(pc, 10000);

      int expectedPrice = 150;
      int expectedDiscountLeft = 10000;

      pc.applySPackageDiscount();
      int actualPrice = priceField.getInt(pc);
      int actualDiscountLeft = discountLeftField.getInt(pc);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }

    @Test
    public void applySPackageDiscount_partialDiscountLeft_partiallyDiscounted() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 200);

      Field discountLeftField = pc.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(pc, 30);

      int expectedPrice = 170;
      int expectedDiscountLeft = 0;

      pc.applySPackageDiscount();
      int actualPrice = priceField.getInt(pc);
      int actualDiscountLeft = discountLeftField.getInt(pc);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }
  }

  @Nested
  @DisplayName("Testing third large LP package discount")
  class applyThirdLargeLPPackageDiscountTests {
    @Test
    public void applyThirdLargeLPPackageDiscount_fullDiscountLeft_priceIsZero() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 690);

      Field discountLeftField = pc.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(pc, 10000);

      int expectedPrice = 0;
      int expectedDiscountLeft = 9310;

      pc.applyThirdLargeLPPackageDiscount();
      int actualPrice = priceField.getInt(pc);
      int actualDiscountLeft = discountLeftField.getInt(pc);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }

    @Test
    public void applyThirdLargeLPPackageDiscount_partialDiscountLeft_partiallyDiscounted() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 690);

      Field discountLeftField = pc.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(pc, 300);

      int expectedPrice = 390;
      int expectedDiscountLeft = 0;

      pc.applyThirdLargeLPPackageDiscount();
      int actualPrice = priceField.getInt(pc);
      int actualDiscountLeft = discountLeftField.getInt(pc);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }
  }

  @Nested
  @DisplayName("Testing if correct string of price is returned")
  class getPriceStringTests {
    @Test
    public void getPriceString_690_sixPointNinety() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 690);

      String expected = "6.90";
      String actual = pc.getPriceString();

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_109_onePointOhNine() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 109);

      String expected = "1.09";
      String actual = pc.getPriceString();

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_000_zeroPointZeroZero() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 0);

      String expected = "0.00";
      String actual = pc.getPriceString();

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_010_zeroPointTen() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 10);

      String expected = "0.10";
      String actual = pc.getPriceString();

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_001_zeroPointOhTen() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field priceField = pc.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(pc, 1);

      String expected = "0.01";
      String actual = pc.getPriceString();

      assertEquals(expected, actual);
    }
  }

  @Nested
  @DisplayName("Testing if correct string of discount is returned")
  class getDiscountStringTests {
    @Test
    public void getDiscountString_690_sixPointNinety() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field discountField = pc.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(pc, 690);

      String expected = "6.90";
      String actual = pc.getDiscountString();

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_000_dash() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field discountField = pc.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(pc, 0);

      String expected = "-";
      String actual = pc.getDiscountString();

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_010_zeroPointTen() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field discountField = pc.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(pc, 10);

      String expected = "0.10";
      String actual = pc.getDiscountString();

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_001_zeroPointOhOne() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field discountField = pc.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(pc, 1);

      String expected = "0.01";
      String actual = pc.getDiscountString();

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_109_onePointOhNine() throws NoSuchFieldException, IllegalAccessException {
      PriceCalculator pc = new PriceCalculator();

      Field discountField = pc.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(pc, 109);

      String expected = "1.09";
      String actual = pc.getDiscountString();

      assertEquals(expected, actual);
    }
  }
}
//https://www.baeldung.com/java-set-private-field-value