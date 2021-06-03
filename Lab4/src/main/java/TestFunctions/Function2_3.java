package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.*;

public class Function2_3 implements Function {
    @Override
    public double run(double[] x) {
        return sqr(x[0] + 10 * x[1]) + 5 * sqr(x[2] - x[3]) + sqr(sqr(x[1] - 2 * x[2])) + 10 * sqr(sqr(x[0] - x[3]));
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] ans = new double[4];
        ans[0] = findGrad1(x);
        ans[1] = findGrad2(x);
        ans[2] = findGrad3(x);
        ans[3] = findGrad4(x);
        return ans;
    }

    private double findGrad4(double[] x) {
        return 10 * (-4 * cube(x[0] - x[3]) + x[3] - x[2]);
    }

    private double findGrad3(double[] x) {
        return 10 * (x[2] - x[3]) - 8 * cube(x[1] - 2 * x[2]);
    }

    private double findGrad2(double[] x) {
        return 4 * (5 * (x[0] + 10 * x[1]) + cube(x[1] - 2 * x[2]));
    }

    private double findGrad1(double[] x) {
        return 2 * (20 * cube(x[0] - x[3]) + x[0] + 10 * x[1]);
    }

    @Override
    public double[] multiply(double[] x) {
        return null;
    }

    @Override
    public double[][] runHessian(double[] x) {
        return new double[0][];
    }
}
