package QuasiNewton;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Search;
import search.GoldenRatioSearch;

import static SaZhaK.MatrixUtil.add;
import static SaZhaK.MatrixUtil.multiply;

public abstract class AbstractQuasiMethod implements Method {
    protected final double eps;

    protected AbstractQuasiMethod(double eps) {
        this.eps = eps;
    }

    protected double[][] createI(int length) {
        double[][] ans = new double[length][length];
        for (int i = 0; i < length; i++) {
            ans[i][i] = 1;
        }
        return ans;
    }

    protected double[] findNextX(Function function, double[] x0, double[] p) {
        double a = findLinearMinimum(function, x0, p);
        p = multiply(p, a);
        return add(x0, p);
    }

    protected double findLinearMinimum(Function function, double[] x, double[] p) {
        MathFunction f = a -> function.run(add(x, multiply(p, a)));
        Search search = new GoldenRatioSearch(f, 0, 10, eps);
        return search.searchMinimum();
    }
}
