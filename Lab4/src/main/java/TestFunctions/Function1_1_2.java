package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.cube;
import static TestFunctions.Helper.sqr;

public class Function1_1_2 implements Function {
    // (2*x + y + 2)^4+(x - 6 * y)^2
    @Override
    public double run(double[] x) {
        return Math.pow((2 * x[0] + x[1] + 2), 4) + sqr(x[0] - 6 * x[1]);
    }

    // (2 (x - 6 y + 4 (2 + 2 x + y)^3), 4 (-3 (x - 6 y) + (2 + 2 x + y)^3))
    @Override
    public double[] runGradient(double[] x) {
        return new double[]{
                2 * (x[0] - 6 * x[1] + 4 * cube(2 + 2 * x[0] + x[1])),
                4 * (-3 * (x[0] - 6 * x[1]) + cube(2 + 2 * x[0] + x[1]))
        };
    }

    @Override
    public double[] multiply(double[] x) {
        return null;
    }

    // 48 (2 x + y + 2)^2 + 2,  24 (2 x + y + 2)^2 - 12
    // 24 (2 x + y + 2)^2 - 12, 12 (2 x + y + 2)^2 + 72)
    @Override
    public double[][] runHessian(double[] x) {
        return new double[][]{
                {48 * sqr(2 * x[0] + x[1] + 2) + 2, 24 * sqr(2 * x[0] + x[1] + 2) - 12},
                {24 * sqr(2 * x[0] + x[1] + 2) - 12, 12 * sqr(2 * x[0] + x[1] + 2) + 72}
        };
    }
}
