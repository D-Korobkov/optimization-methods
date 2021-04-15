package methods;

import SaZhaK.Matrix;
import interfaces.Function;
import interfaces.Method;

import java.io.*;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

public class ConjugateGradientMethod extends LoggingMethod {

    private final double epsilon;

    public ConjugateGradientMethod(double epsilon) {
        super(false);
        this.epsilon = epsilon;
    }

    public ConjugateGradientMethod(double epsilon, boolean log, String fileName) throws FileNotFoundException {
        super(log, fileName);
        this.epsilon = epsilon;
    }

    @Override
    public double[] findMinimum(final Function function, double[] x0) throws IOException {
        do {
            x0 = startIteration(function, x0);
        } while(norm(function.runGradient(x0)) >= epsilon);
        return x0;
    }

    private double[] startIteration(final Function function, double[] prevX) throws IOException {
        final int n = prevX.length;
        double [] prevGrad = function.runGradient(prevX);
        double normPrevGrad = norm(prevGrad);
        double [] prevP = multiply(prevGrad, -1);
        for (int i = 1; i < n && normPrevGrad >= epsilon; i++) {
            double[] mulApPrev = function.multiply(prevP);
            double aPrev = normPrevGrad * normPrevGrad / scalar(mulApPrev, prevP);
            double[] nextX = add(prevX, multiply(prevP, aPrev));
            double[] nextGrad = add(prevGrad, multiply(mulApPrev, aPrev));
            double normNextGrad = norm(nextGrad);
            double b = normNextGrad * normNextGrad / normPrevGrad / normPrevGrad;
            double[] nextP = add(multiply(nextGrad, -1), multiply(prevP, b));
            prevP = nextP;
            prevGrad = nextGrad;
            prevX = nextX;
            normPrevGrad = normNextGrad;
            log(prevX, prevGrad);
        }
        return prevX;
    }

    private double scalar(final double[] a, final double[] b) {
        double res = 0;
        for (int i = 0; i < a.length; i++) {
            res += a[i] * b[i];
        }
        return res;
    }
}
