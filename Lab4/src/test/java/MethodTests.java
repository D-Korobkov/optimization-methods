import functions.QuadraticFunction;
import interfaces.Function;
import interfaces.Method;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public abstract class MethodTests {
    protected final static double eps = 0.000001;

    public static void testMethod(Function function, Method method, double[] realAnswer, double[] x0, double epsilon) throws IOException {
        double[] answer = method.findMinimum(function, x0);
        assertArrayEquals("Incorrect minimum", answer, realAnswer, epsilon);
    }

    public static void testOnFunctions(Method method, double epsilon) throws IOException {
        double[] x0 = {10, 10};

        List<Function> functions = List.of(
                new QuadraticFunction(new double[][]{{2, 0}, {0, 2}}, new double[]{0, 0}, 0),
                new QuadraticFunction(new double[][]{{20, 20}, {20, 40}}, new double[]{10, 10}, 10));

        double[][] answers = {
                {0, 0},
                {0.5, 0}
        };

        for(int i = 0; i < functions.size(); i++){
            testMethod(functions.get(i), method, answers[i], x0, epsilon);
            System.out.println((i+1) + "-th function test passed");
        }
    }
}
