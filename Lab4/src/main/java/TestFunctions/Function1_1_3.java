package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.sqr;

public class Function1_1_3 implements Function {
    //3(x - 1)^2 + 5*y^2 + 6xy + 2
    @Override
    public double run(double[] x) {
        return 3 * sqr(x[0] - 1) + 5 * x[1] * x[1] + 6 * x[0] * x[1] + 2;
    }

    // (6 (x + y - 1), 6 x + 10 y)
    @Override
    public double[] runGradient(double[] x) {
        return new double[]{
                6 * (x[0] + x[1] - 1),
                6 * x[0] + 10 * x[1]
        };
    }

    @Override
    public double[] multiply(double[] x) {
        return new double[0];
    }

    @Override
    public double[][] runHessian(double[] x) {
        return new double[][]{{6, 6}, {6, 10}};
    }
}
