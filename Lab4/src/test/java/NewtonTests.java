import NewtonMethods.ClassicNewtonMethod;
import functions.QuadraticFunction;
import interfaces.Function;
import interfaces.Method;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NewtonTests {

    @BeforeClass
    public static void prepare() {

        System.out.println("prepare");
    }

    @Before
    public void preTest() {

        System.out.println("run test");
    }

    @Test
    public void classicNewtoneTest() throws IOException {

        System.out.println("run classicNewtoneTest");

        double[] x0 = {10, 10};
        Method classicNewtone = new ClassicNewtonMethod();

        Function f1 = new QuadraticFunction(new double[][]{{2, 0}, {0, 2}}, new double[]{0, 0}, 0);
        double[] f1Real = {0, 0};
        double[] f1Answer = classicNewtone.findMinimum(f1, x0);

        assertArrayEquals("Incorrect minimum in f1", f1Real, f1Answer, 0.0);
        System.out.println("f1 test passed");

        Function f2 = new QuadraticFunction(new double[][]{{20, 20}, {20, 40}}, new double[]{10, 10}, 10);
        double[] f2Real = {0.5, 0};
        double[] f2Answer = classicNewtone.findMinimum(f2, x0);

        assertArrayEquals("Incorrect minimum in f2", f2Real, f2Answer, 0.0);
        System.out.println("f2 test passed");

        System.out.println("classicNewtone test passed");

    }
}
