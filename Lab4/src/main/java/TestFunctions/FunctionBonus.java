package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.*;

public class FunctionBonus implements Function {
    @Override
    public double run(double[] x) {
        double ans = 0;
        for (int i = 0; i < 99; i++) {
            ans += 100 * sqr(x[i + 1] - sqr(x[i])) + sqr(1 - x[i]);
        }
        return ans;
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] ans = new double[100];
        ans[0] = -400 * (x[1] - sqr(x[0])) * x[0] - 2 * (1 - x[0]);
        for (int i = 1; i < 98; i++) {
           ans[i] = -400 * (x[i + 1] - sqr(x[i])) * x[i] - 2 * (1 - x[i]) + 200 * (x[i] - sqr(x[i - 1]));
        }
        ans[99] = 200 * (x[99] - sqr(x[98]));
        return ans;
    }

    @Override
    public double[] multiply(double[] x) {
        return null;
    }

    @Override
    public double[][] runHessian(double[] x) {
        double[][] H = new double[100][100];
        //H[0][0] = -400 * ((x[1] - sqr(x[0])) - 2 * sqr(x[0])) + 2;
        H[0][0] = 1200 * x[0] * x[0] - 400 * x[1] + 2;
        H[1][0] = H[0][1] = -400 * x[0];
        for (int i = 1; i < 99; i++) {
            H[i][i - 1] = findIJLess(x, i);
            H[i][i] = findII(x, i);
            H[i][i + 1] = findIJMore(x, i);
        }
        H[98][99] = H[99][98] = -400 * x[98];
        H[99][99] = 200;
        return H;
    }

    private double findIJMore(double[] x, int ind) {
        return -400 * x[ind];
    }

    private double findII(double[] x, int ind) {
        //return -400 * (x[ind + 1] - sqr(x[ind]) - 2 * sqr(x[ind])) + 202;
        return 202 + 1200 * x[ind] * x[ind] - 400 * x[ind + 1];
    }

    private double findIJLess(double[] x, int ind) {
        return -400 * x[ind - 1];
    }
}
