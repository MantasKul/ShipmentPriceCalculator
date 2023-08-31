/*
    Name format:
    methodName_StateUnderTest_ExpectedResult
 */
import org.mantas.Line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest {

  @Nested
  @DisplayName("Testing isFormatCorrect")
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

  // splitLine will only be called with correct format as it's checked before
  @Nested
  @DisplayName("Testing splitLine")
  public class splitLineTests {
    @Test
    public void splitLine_line_sameLineButSplit() {
      Line line = new Line();
      line.splitLine("2015-12-15 S LP");
      LocalDate expectedDate = LocalDate.of(2015, 12, 15);
      char expectedSize = 'S';
      String expectedProvider = "LP";

      assertEquals(expectedDate, line.getDate());
      assertEquals(expectedSize, line.getSize());
      assertEquals(expectedProvider, line.getProvider());
    }
  }
}
