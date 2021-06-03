import TestFunctions.Function2_1;
import TestFunctions.Function2_2;
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


    //TODO: add new functions(non quadratiq too)
    public static void testOnFunctions(Method method, double epsilon) throws IOException {
        double[] x0 = {10, 10};

        List<FunctionTestHandler> functions = List.of(
                new FunctionTestHandler(
                        new QuadraticFunction(new double[][]{{2, 0}, {0, 2}},
                        new double[]{0, 0}, 0),
                        new double[]{0, 0},
                        x0,
                        "Simple function 1"),
                new FunctionTestHandler(
                        new QuadraticFunction(new double[][]{{20, 20}, {20, 40}}, new double[]{10, 10}, 10),
                        new double[]{0.5, 0},
                        x0,
                        "Simple function 2"),
                new FunctionTestHandler(
                        new Function2_1(),
                        new double[]{1, 1},
                        new double[] {2, 2},
                        "Function2_1"),
                new FunctionTestHandler(
                        new Function2_2(),
                        new double[]{3, 2},
                        new double[] {4, 3},
                        "Function2_2"),
                new FunctionTestHandler(
                        new Function2_2(),
                        new double[]{3, 2},
                        new double[] {3, 2},
                        "Function2_2")
        );


        for(FunctionTestHandler i : functions){
            testMethod(i.getFunction(), method, i.getAnswer(), i.getX0(), epsilon);
            System.out.println(i.getName() + " test passed");
        }
    }
}
