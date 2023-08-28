import junit.framework.Assert;
import org.example.LineManager;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;

public class LineManagerTest {
    @Test
    public void checkIfLineHasCorrectFormat() {
        LineManager lineManager = new LineManager();
        System.out.println("Check correct format");
        assertEquals(true, lineManager.isLineValid("2015-02-01 S MR"));
        assertEquals(true, lineManager.isLineValid("2015-02-24 L LP"));
        System.out.println("Check incorrect format");
        assertEquals(false, lineManager.isLineValid("2015-02-29 CUSPS"));
        System.out.println("Check empty line");
        assertEquals(false, lineManager.isLineValid(""));
        System.out.println("Check gibber");
        assertEquals(false, lineManager.isLineValid("asfdsf453542q5"));
    }

    @Test
    public void checkIfNewMonth() {
        LineManager lineManager = new LineManager();

        System.out.println("Check when previousDate is empty");
        lineManager.setCurrentDate("2015-05-15");
        assertEquals(false, lineManager.isNewMonth());

        System.out.println("Check when dates are same");
        lineManager.setCurrentDate("2015-05-15");
        lineManager.setPreviousDate(lineManager.getCurrentDate());
        assertEquals(false, lineManager.isNewMonth());

        System.out.println("Check when same year, different month");
        lineManager.setCurrentDate("2015-05-15");
        lineManager.setPreviousDate(LocalDate.of(2015, 06, 15));
        assertEquals(true, lineManager.isNewMonth());

        System.out.println("Check when year is different but month is same");
        lineManager.setCurrentDate("2015-05-15");
        lineManager.setPreviousDate(LocalDate.of(2016, 05, 15));
        assertEquals(true, lineManager.isNewMonth());
    }
}

// TODO: user parametrized unit tests
