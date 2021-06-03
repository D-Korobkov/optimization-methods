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
        //todo
        return H;
    }
}
