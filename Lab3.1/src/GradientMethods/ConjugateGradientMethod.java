package GradientMethods;

import interfaces.Function;

import java.io.FileNotFoundException;
import java.io.IOException;

import static SaZhaK.MatrixUtil.*;


/**
 * Class of ConjugateGradient realisation
 */
public class ConjugateGradientMethod extends AbstractGradientMethod {

    /**
     * Standard constructor
     *
     * @param epsilon {@link AbstractGradientMethod#epsilon}
     * @see AbstractGradientMethod#AbstractGradientMethod(double)
     */
    public ConjugateGradientMethod(final double epsilon) {
        super(epsilon);
    }

    /**
     * Full constructor
     *
     * @param epsilon  {@link #epsilon}
     * @param log      {@link AbstractGradientMethod#log}
     * @param fileName output file for {@link AbstractGradientMethod#out}
     * @throws FileNotFoundException if specified output file was not found
     * @see AbstractGradientMethod#AbstractGradientMethod(double, boolean, String)
     */
    public ConjugateGradientMethod(final double epsilon, final boolean log, final String fileName) throws FileNotFoundException {

        super(epsilon, log, fileName);
    }


    @Override
    public double[] findMinimum(final Function function, double[] x0) throws IOException {
//        log(new double[]{3.0, 3.0}, new double[]{3.0, 3.0});
        do {
            x0 = startIteration(function, x0);
        } while (norm(function.runGradient(x0)) >= epsilon);
        return x0;
    }

    /**
     * Iterative method of ConjugateGradient.
     *
     * @param function explored fucntion
     * @param prevX    point of start for current iterative launch
     * @return temporary result of minimum
     * @throws IOException exception from {@link #log(double[], double[])}
     */
    private double[] startIteration(final Function function, double[] prevX) throws IOException {
        final int n = prevX.length;
        double[] prevGrad = function.runGradient(prevX);
        double normPrevGrad = norm(prevGrad);
        double[] prevP = multiply(prevGrad, -1);
        for (int i = 1; i < n && normPrevGrad >= epsilon; i++) {
            final double[] mulApPrev = function.multiply(prevP);
            final double aPrev = normPrevGrad * normPrevGrad / dotProduct(mulApPrev, prevP);
            final double[] nextX = add(prevX, multiply(prevP, aPrev));
            final double[] nextGrad = add(prevGrad, multiply(mulApPrev, aPrev));
            final double normNextGrad = norm(nextGrad);
            final double b = normNextGrad * normNextGrad / normPrevGrad / normPrevGrad;
            final double[] nextP = add(multiply(nextGrad, -1), multiply(prevP, b));
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
