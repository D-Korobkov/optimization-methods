package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.*;

public class Function2_4 implements Function {
    @Override
    public double run(double[] x) {
        return 100 - 2 / zn1(x) - 1 / zn2(x);
    }

    private double zn2(double[] x) {
        return (1 + sqr((x[0] - 2) / 2) + sqr((x[1] - 1) / 3));
    }

    private double zn1(double[] x) {
        return (1 + sqr((x[0] - 1) / 2) + sqr((x[1] - 1) / 3));
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] ans = new double[2];
        ans[0] = findGrad1(x);
        ans[1] = findGrad2(x);
        return ans;
    }

    private double findGrad2(double[] x) {
        return 2 * (x[1] - 1) / (9 * gradZn1(x)) + 4 * (x[1] - 1) / (9 * gradZn2(x));
    }

    private double findGrad1(double[] x) {
        return (x[0] - 2) / (2 * gradZn1(x)) + (x[0] - 1) / gradZn2(x);
    }

    private double gradZn1(double[] x) {
        return sqr(0.25 * sqr(x[0] - 2) + sqr(x[1] - 1) / 9 + 1);
    }

    private double gradZn2(double[] x) {
        return sqr(0.25 * sqr(x[0] - 1) + sqr(x[1] - 1) / 9 + 1);
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
