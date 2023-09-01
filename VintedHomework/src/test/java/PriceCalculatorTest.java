/*
    Name format:
    methodName_StateUnderTest_ExpectedResult
 */

import org.junit.jupiter.api.Test;
import org.mantas.PriceCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {
  @Nested
  @DisplayName("Testing small package discount calculation")
  class applySPackageDiscountTests {
    @Test
    public void applySPackageDiscount_givenNotCheapestS_discounted() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 200);

      Field discountLeftField = priceCalculator.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(priceCalculator, 10000);

      Method method = priceCalculator.getClass().getDeclaredMethod("applySPackageDiscount");
      method.setAccessible(true);

      int expectedPrice = 150;
      int expectedDiscountLeft = 9950;

      method.invoke(priceCalculator);
      int actualPrice = priceField.getInt(priceCalculator);
      int actualDiscountLeft = discountLeftField.getInt(priceCalculator);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }

    @Test
    public void applySPackageDiscount_givenCheapestSPackage_notDiscounted() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 150);

      Field discountLeftField = priceCalculator.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(priceCalculator, 10000);

      Method method = priceCalculator.getClass().getDeclaredMethod("applySPackageDiscount");
      method.setAccessible(true);

      int expectedPrice = 150;
      int expectedDiscountLeft = 10000;

      method.invoke(priceCalculator);
      int actualPrice = priceField.getInt(priceCalculator);
      int actualDiscountLeft = discountLeftField.getInt(priceCalculator);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }

    @Test
    public void applySPackageDiscount_partialDiscountLeft_partiallyDiscounted() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 200);

      Field discountLeftField = priceCalculator.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(priceCalculator, 30);

      Method method = priceCalculator.getClass().getDeclaredMethod("applySPackageDiscount");
      method.setAccessible(true);

      int expectedPrice = 170;
      int expectedDiscountLeft = 0;

      method.invoke(priceCalculator);
      int actualPrice = priceField.getInt(priceCalculator);
      int actualDiscountLeft = discountLeftField.getInt(priceCalculator);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }
  }

  @Nested
  @DisplayName("Testing third large LP package discount")
  class applyThirdLargeLPPackageDiscountTests {
    @Test
    public void applyThirdLargeLPPackageDiscount_fullDiscountLeft_priceIsZero() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 690);

      Field discountLeftField = priceCalculator.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(priceCalculator, 10000);

      int expectedPrice = 0;
      int expectedDiscountLeft = 9310;

      Method method = priceCalculator.getClass().getDeclaredMethod("applyThirdLargeLPPackageDiscount");
      method.setAccessible(true);

      method.invoke(priceCalculator);
      int actualPrice = priceField.getInt(priceCalculator);
      int actualDiscountLeft = discountLeftField.getInt(priceCalculator);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }

    @Test
    public void applyThirdLargeLPPackageDiscount_partialDiscountLeft_partiallyDiscounted() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 690);

      Field discountLeftField = priceCalculator.getClass().getDeclaredField("discountLeft");
      discountLeftField.setAccessible(true);
      discountLeftField.set(priceCalculator, 300);

      int expectedPrice = 390;
      int expectedDiscountLeft = 0;

      Method method = priceCalculator.getClass().getDeclaredMethod("applyThirdLargeLPPackageDiscount");
      method.setAccessible(true);

      method.invoke(priceCalculator);
      int actualPrice = priceField.getInt(priceCalculator);
      int actualDiscountLeft = discountLeftField.getInt(priceCalculator);

      assertEquals(expectedPrice, actualPrice);
      assertEquals(expectedDiscountLeft, actualDiscountLeft);
    }
  }

  @Nested
  @DisplayName("Testing if correct string of price is returned")
  class getPriceStringTests {
    @Test
    public void getPriceString_690_sixPointNinety() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 690);

      Method method = priceCalculator.getClass().getDeclaredMethod("getPriceString");
      method.setAccessible(true);

      String expected = "6.90";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_109_onePointOhNine() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 109);

      Method method = priceCalculator.getClass().getDeclaredMethod("getPriceString");
      method.setAccessible(true);

      String expected = "1.09";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_000_zeroPointZeroZero() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 0);

      Method method = priceCalculator.getClass().getDeclaredMethod("getPriceString");
      method.setAccessible(true);

      String expected = "0.00";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_010_zeroPointTen() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 10);

      Method method = priceCalculator.getClass().getDeclaredMethod("getPriceString");
      method.setAccessible(true);

      String expected = "0.10";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getPriceString_001_zeroPointOhTen() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field priceField = priceCalculator.getClass().getDeclaredField("price");
      priceField.setAccessible(true);
      priceField.setInt(priceCalculator, 1);

      Method method = priceCalculator.getClass().getDeclaredMethod("getPriceString");
      method.setAccessible(true);


      String expected = "0.01";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }
  }

  @Nested
  @DisplayName("Testing if correct string of discount is returned")
  class getDiscountStringTests {
    @Test
    public void getDiscountString_690_sixPointNinety() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field discountField = priceCalculator.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(priceCalculator, 690);

      Method method = priceCalculator.getClass().getDeclaredMethod("getDiscountString");
      method.setAccessible(true);

      String expected = "6.90";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_000_dash() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field discountField = priceCalculator.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(priceCalculator, 0);

      Method method = priceCalculator.getClass().getDeclaredMethod("getDiscountString");
      method.setAccessible(true);

      String expected = "-";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_010_zeroPointTen() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field discountField = priceCalculator.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(priceCalculator, 10);

      Method method = priceCalculator.getClass().getDeclaredMethod("getDiscountString");
      method.setAccessible(true);

      String expected = "0.10";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_001_zeroPointOhOne() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field discountField = priceCalculator.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(priceCalculator, 1);

      Method method = priceCalculator.getClass().getDeclaredMethod("getDiscountString");
      method.setAccessible(true);

      String expected = "0.01";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }

    @Test
    public void getDiscountString_109_onePointOhNine() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      PriceCalculator priceCalculator = new PriceCalculator();

      Field discountField = priceCalculator.getClass().getDeclaredField("discount");
      discountField.setAccessible(true);
      discountField.setInt(priceCalculator, 109);

      Method method = priceCalculator.getClass().getDeclaredMethod("getDiscountString");
      method.setAccessible(true);

      String expected = "1.09";
      String actual = (String) method.invoke(priceCalculator);

      assertEquals(expected, actual);
    }
  }
}
