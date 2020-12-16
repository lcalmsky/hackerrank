package time_conversion;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {
    public void test() {
        assertEquals("00:40:22", Solution.timeConversion("12:40:22AM"));
    }
}
