package QuasiNewton;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Search;
import search.GoldenRatioSearch;

import java.io.IOException;

import static SaZhaK.MatrixUtil.*;

public class PowellMethod implements Method {

    private final double eps;

    public PowellMethod(double eps) {
        this.eps = eps;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {
        double[][] C = createI(x0.length);
        double[] w = multiply(function.runGradient(x0), -1);
        while (norm(w) > eps) {
            double[] p = multiply(C, w);
            double[] nextX = findNextX(function, x0, p);
            double[] nextW = multiply(function.runGradient(nextX), -1);
            double[] deltaX = subtract(nextX, x0);
            double[] deltaW = subtract(nextW, w);
            C = getNextC(C, add(deltaX, multiply(C, deltaW)), deltaW);
            x0 = nextX;
            w = nextW;
        }
        return x0;
    }

    private double[][] getNextC(double[][] C, double[] deltaX, double[] deltaW) {
        double k = 1 / dotProduct(deltaW, deltaX);
        return subtract(C, multiply(multiply(deltaX, deltaX), k));
    }

    private double[] findNextX(Function function, double[] x0, double[] p) {
        double a = findLinearMinimum(function, x0, p);
        p = multiply(p, a);
        return add(x0, p);
    }

    private double findLinearMinimum(Function function, double[] x, double[] p) {
        MathFunction f = a -> function.run(add(x, multiply(p, a)));
        Search search = new GoldenRatioSearch(f, 0, 10, eps);
        return search.searchMinimum();
    }

    private double[][] createI(int length) {
        double[][] ans = new double[length][length];
        for (int i = 0; i < length; i++) {
            ans[i][i] = 1;
        }
        return ans;
    }
}
