import Functions.QuadraticFunction;
import methods.GradientDescentMethod;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] a = new double[][]{
                {128, 12},
                {12, 128}
        };
        double[] b = new double[]{10, -30};
        double c = 13;
        QuadraticFunction f = new QuadraticFunction(a, b, c);
        System.out.println("real = 13; cur = " + f.run(new double[]{0, 0}));
        System.out.println("real = 173; cur = " + f.run(new double[]{1, 1}));
        System.out.println("real = 8.83; cur = " + f.run(new double[]{0.1, -0.24}));
        System.out.println(
                Arrays.toString(
                        new GradientDescentMethod(0.1, 0.00001)
                                .findMinimum(f, new double[]{1, 1})
                )
        );
    }
}
