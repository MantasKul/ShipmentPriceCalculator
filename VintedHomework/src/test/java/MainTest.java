import org.example.LineManager;
import org.example.Main;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class MainTest {
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
}

// TODO: user parametrized unit tests
