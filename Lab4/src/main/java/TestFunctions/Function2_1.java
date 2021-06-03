package TestFunctions;

import SaZhaK.MatrixUtil;
import interfaces.Function;
import interfaces.Matrix;

import static TestFunctions.Helper.*;

public class Function2_1 implements Function {
    @Override
    public double run(double[] x) {
        return 100 * sqr(x[1] - sqr(x[0])) + sqr(1 - x[0]);
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] ans = new double[2];
        ans[0] = findGrad1(x);
        ans[1] = findGrad2(x);
        return ans;
    }

    @Override
    public double[] multiply(double[] x) {

        return null;
    }

    @Override
    public double[][] runHessian(double[] x) {
        double[][] H = new double[2][2];
        H[0][0] = findH11(x);
        H[0][1] = H[1][0] = findH21(x);
        H[1][1] = findH22(x);
        return H;
    }

    private double findGrad1(double[] x) {
        return 2 * (200 * cube(x[0]) - 200 * x[0] * x[1] + x[0] - 1);
    }

    private double findGrad2(double[] x) {
        return 200 * (x[1] - sqr(x[0]));
    }

    private double findH22(double[] x) {
        return 200;
    }

    private double findH21(double[] x) {
        return -400 * x[0];
    }

    private double findH11(double[] x) {
        return 2 * (600 * sqr(x[0]) - 200 * x[1] + 1);
    }

}
