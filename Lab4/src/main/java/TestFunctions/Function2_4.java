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
        return sqr(differentialZn1(x));
    }

    private double gradZn2(double[] x) {
        return sqr(differentialZn2(x));
    }

    private double differentialZn1(double[] x) {
        return sqr(0.25 * sqr(x[0] - 2) + sqr(x[1] - 1) / 9 + 1);
    }

    private double differentialZn2(double[] x) {
        return 0.25 * sqr(x[0] - 1) + sqr(x[1] - 1) / 9 + 1;
    }

    @Override
    public double[] multiply(double[] x) {
        return null;
    }

    @Override
    public double[][] runHessian(double[] x) {
        double[][] H = new double[2][2];
        H[0][0] = findH11(x);
        H[0][1] = H[1][0] = findH12(x);
        H[1][1] = findH22(x);
        return H;
    }

    private double findH22(double[] x) {
        return -8 * sqr(x[1] - 1) / (81 * cube(differentialZn1(x)))
                -16 * sqr(x[1] - 1) / (81 * cube(differentialZn2(x)))
                + 2 / (9 * sqr(differentialZn1(x))) + 4 / (9 * sqr(differentialZn2(x)));
    }

    private double findH12(double[] x) {
        return -2 * (x[0] - 2) * (x[1] - 1) / (9 * cube(differentialZn1(x)))
                - 4 * (x[0] - 1) * (x[1] - 1) / (9 * cube(differentialZn2(x)));
    }

    private double findH11(double[] x) {
        return -sqr(x[0] - 2) / (2 * cube(differentialZn1(x)))
                - 2 * (sqr(x[0] - 1) / (2 * cube(differentialZn2(x))) - 1 / (2 * sqr(differentialZn2(x))))
                + 1 / (2 * sqr(differentialZn1(x)));
    }
}
