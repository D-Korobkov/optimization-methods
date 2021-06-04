package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.cube;
import static TestFunctions.Helper.sqr;

public class Function1_1_1 implements Function {
    @Override
    public double run(double[] x) {
        return Math.pow(x[0] - x[1], 4) + sqr(x[0] + x[1]);
    }

    @Override
    public double[] runGradient(double[] x) {
        return new double[]{
                2 * (2 * cube(x[0] - x[1]) + x[0] + x[1]),
                2 * (-2 * cube(x[0] - x[1]) + x[0] + x[1])
        };
    }

    @Override
    public double[] multiply(double[] x) {
        return null;
    }

    @Override
    public double[][] runHessian(double[] x) {
        return new double[][]{
                {12 * sqr(x[0] - x[1]) + 2, -12 * sqr(x[0] - x[1]) + 2},
                {-12 * sqr(x[0] - x[1]) + 2, 12 * sqr(x[0] - x[1]) + 2}
        };
    }
}
