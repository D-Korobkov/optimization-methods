package methods;

import SaZhaK.Matrix;
import interfaces.Function;
import interfaces.Method;

import java.io.*;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

public class ConjugateGradientMethod implements Method {

    private final double epsilon;
    private final boolean log;
    private final BufferedWriter out;

    public ConjugateGradientMethod(double epsilon) {

        this.epsilon = epsilon;
        this.log = false;
        this.out = null;
    }

    public ConjugateGradientMethod(double epsilon, boolean log, String fileName) throws FileNotFoundException {

        this.epsilon = epsilon;

        this.log = log;
        this.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
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

    private void log(double[] x, double[] gradient) throws IOException {
        if(!log) return;
        assert out != null;
        out.write(Arrays.toString(x) + ":" + Arrays.toString(x) + "\n");
        out.flush();
    }
}
