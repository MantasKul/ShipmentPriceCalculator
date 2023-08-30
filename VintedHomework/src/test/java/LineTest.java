/*
    Name format:
    methodName_StateUnderTest_ExpectedResult
 */
import org.example.Line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest {

    @Nested
    @DisplayName("Checking isFormatCorrect")
    public class isFormatCorrectTests {
        @Test
        public void isFormatCorrect_correctFormat_true() {
            boolean actual = new Line().isFormatCorrect("2015-02-01 S MR");
            boolean expected = true;
            assertEquals(expected, actual);
        }

        @Test
        public void isFormatCorrect_incorrectFormat_false() {
            boolean actual = new Line().isFormatCorrect("2015-02-29 CUSPS");
            boolean expected = false;
            assertEquals(expected, actual);
        }

        @Test
        public void isFormatCorrect_emptyString_false() {
            boolean actual = new Line().isFormatCorrect("");
            boolean expected = false;
            assertEquals(expected, actual);
        }
    }
}
