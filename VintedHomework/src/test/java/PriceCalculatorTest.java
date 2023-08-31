/*
    Name format:
    methodName_StateUnderTest_ExpectedResult
 */

import org.mantas.Line;
import org.mantas.PriceCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class PriceCalculatorTest {
    @Nested
    @DisplayName("Testing isNewMonth method")
    class isNewMonthTests {
        @Test
        public void isNewMonth_newMonth_true() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setPreviousDate(LocalDate.of(2015, 03, 15));
            boolean actual = priceCalculator.isNewMonth(LocalDate.of(2015, 04, 15));
            boolean expected = true;
            assertEquals(expected, actual);
        }
        @Test
        public void isNewMonth_previousMonthIsNull_false() {
            PriceCalculator priceCalculator = new PriceCalculator();
            boolean actual = priceCalculator.isNewMonth(LocalDate.of(2015, 03, 15));
            boolean expected = false;
            assertEquals(expected, actual);
        }
        @Test
        public void isNewMonth_previousAndCurrentDatesSame_false() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setPreviousDate(LocalDate.of(2015, 03, 15));
            boolean actual = priceCalculator.isNewMonth(LocalDate.of(2015, 03, 15));
            boolean expected = false;
            assertEquals(expected, actual);
        }
        @Test
        public void isNewMonth_differentYearSameMonth_true() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setPreviousDate(LocalDate.of(2015, 03, 15));
            boolean actual = priceCalculator.isNewMonth(LocalDate.of(2016, 03, 15));
            boolean expected = true;
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Test if getCheapestSmall being called when package size is small")
    class getCheapestSmallTests {
        @Test
        public void getCheapestSmall_smallSizeNotCheapestOption_called(){
            Line line = new Line();
            line.splitLine("2015-12-15 S MR");

        }
        public void getCheapestSmall_smallSizeCheapestOption_called(){}
        public void getCheapestSmall_largeSize_notCalled(){}
    }

    @Nested
    @DisplayName("Testing getPriceString")
    class getPriceStringTest {
        @Test
        public void getPriceString_000_000withDot() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setPrice(0);
            String actual = priceCalculator.getPriceString();
            String expected = "0.00";
            assertEquals(expected, actual);
        }
        @Test
        public void getPriceString_109_oneDollarOhNineCents() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setPrice(109);
            String actual = priceCalculator.getPriceString();
            String expected = "1.09";
            assertEquals(expected, actual);
        }
        @Test
        public void getPriceString_690_sixDollarsNinetyCents() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setPrice(690);
            String actual = priceCalculator.getPriceString();
            String expected = "6.90";
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Testing getPriceString")
    class getDiscountString {
        @Test
        public void getDiscountString_000_dash() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setDiscount(0);
            String actual = priceCalculator.getDiscountString();
            String expected = "-";
            assertEquals(expected, actual);
        }
        @Test
        public void getDiscountString_109_oneDollarOhNineCents() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setDiscount(109);
            String actual = priceCalculator.getDiscountString();
            String expected = "1.09";
            assertEquals(expected, actual);
        }
        @Test
        public void getDiscountString_690_sixDollarsNinetyCents() {
            PriceCalculator priceCalculator = new PriceCalculator();
            priceCalculator.setDiscount(690);
            String actual = priceCalculator.getDiscountString();
            String expected = "6.90";
            assertEquals(expected, actual);
        }
    }
}
