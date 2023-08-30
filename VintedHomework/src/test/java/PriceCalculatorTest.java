/*
    Name format:
    methodName_StateUnderTest_ExpectedResult
 */

import org.example.PriceCalculator;

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


}
