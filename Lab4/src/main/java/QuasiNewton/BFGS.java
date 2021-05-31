package QuasiNewton;

import SaZhaK.MatrixUtil;
import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Search;
import search.GoldenRatioSearch;

import java.io.IOException;

import static SaZhaK.MatrixUtil.*;

public class BFGS extends AbstractQuasiMethod {

    public BFGS(double eps) {
        super(eps);
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {
        double[][] C = createI(x0.length);
        double[] grad = function.runGradient(x0);
        while (norm(grad) > eps) {
            double[] p = multiply(multiply(C, grad), -1);
            double[] nextX = findNextX(function, x0, p);
            double[] nextGrad = function.runGradient(nextX);
            C = getNextC(C, subtract(nextX, x0), subtract(nextGrad, grad));
            x0 = nextX;
            grad = nextGrad;
        }
        return x0;
    }

    private double[][] getNextC(double[][] C, double[] s, double[] y) {
        double p = 1 / dotProduct(y, s);
        double[][] nextC = subtract(createI(C.length), multiply(multiply(s, y), p));
        nextC = multiply(nextC, C);
        nextC = multiply(nextC, subtract(createI(C.length), multiply(multiply(y, s), p)));
        nextC = add(nextC, multiply(multiply(s, s), p));
        return nextC;
    }
}
