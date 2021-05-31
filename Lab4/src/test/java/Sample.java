import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class Sample {
    @BeforeClass
    public static void prepare() {
        System.out.println("prepare");
    }

    @Before
    public void preTest() {
        System.out.println("run test");
    }

    @Test
    public void sampleTest1() {
        assertTrue("should be true", true);
    }

    @Test
    public void sampleTest2() {
        assertNull("should be null", null);
    }
}
