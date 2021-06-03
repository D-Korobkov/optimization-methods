package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.*;

public class Function2_4 implements Function {
    @Override
    public double run(double[] x) {
        return 100 - 2 / functionZn1(x) - 1 / functionZn2(x);
    }

    private double functionZn2(double[] x) {
        return (1 + sqr((x[0] - 2) / 2) + sqr((x[1] - 1) / 3));
    }

    private double functionZn1(double[] x) {
        return (1 + sqr((x[0] - 1) / 2) + sqr((x[1] - 1) / 3));
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] ans = new double[2];
        final double zn1 = gradZn1(x);
        final double zn2 = gradZn2(x);
        ans[0] = findGrad1(x, zn1, zn2);
        ans[1] = findGrad2(x, zn1, zn2);
        return ans;
    }

    private double findGrad2(double[] x, double zn1, double zn2) {
        return 2 * (x[1] - 1) / (9 * zn1) + 4 * (x[1] - 1) / (9 * zn2);
    }

    private double findGrad1(double[] x, double zn1, double zn2) {
        return (x[0] - 2) / (2 * zn1) + (x[0] - 1) / zn2;
    }

    private double gradZn1(double[] x) {
        return sqr(differentialZn1(x));
    }

    private double gradZn2(double[] x) {
        return sqr(differentialZn2(x));
    }

    private double differentialZn1(double[] x) {
        return 0.25 * sqr(x[0] - 2) + sqr(x[1] - 1) / 9 + 1;
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
        double zn1 = differentialZn1(x);
        double zn2 = differentialZn2(x);
        H[0][0] = findH11(x, zn1, zn2);
        H[0][1] = H[1][0] = findH12(x, zn1, zn2);
        H[1][1] = findH22(x, zn1, zn2);
        return H;
    }

    private double findH22(double[] x, double zn1, double zn2) {
        return -8 * sqr(x[1] - 1) / (81 * cube(zn1))
                -16 * sqr(x[1] - 1) / (81 * cube(zn2))
                + 2 / (9 * sqr(zn1)) + 4 / (9 * sqr(zn2));
    }

    private double findH12(double[] x, double zn1, double zn2) {
        return -2 * (x[0] - 2) * (x[1] - 1) / (9 * cube(zn1))
                - 4 * (x[0] - 1) * (x[1] - 1) / (9 * cube(zn2));
    }

    private double findH11(double[] x, double zn1, double zn2) {
        return -sqr(x[0] - 2) / (2 * cube(zn1))
                - 2 * (sqr(x[0] - 1) / (2 * cube(zn2)) - 1 / (2 * sqr(zn2)))
                + 1 / (2 * sqr(zn1));
    }
}
