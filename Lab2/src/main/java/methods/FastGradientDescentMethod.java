package methods;

import interfaces.Function;
import interfaces.MathFunction;
import search.ParabolSearch;

import java.io.*;

import static SaZhaK.MatrixUtil.*;


/**
 * Class of Fast Gradient realisation
 */
public class FastGradientDescentMethod extends AbstractGradientMethod {
    /**
     * Standard constructor
     * @param epsilon {@link AbstractGradientMethod#epsilon}
     * @see AbstractGradientMethod#AbstractGradientMethod(double)
     */
    public FastGradientDescentMethod(final double epsilon) {
        super(epsilon);
    }

    /**
     * Full constructor
     * @param epsilon {@link #epsilon}
     * @param log {@link AbstractGradientMethod#log}
     * @param fileName output file for {@link AbstractGradientMethod#out}
     * @throws FileNotFoundException if specified output file was not found
     * @see AbstractGradientMethod#AbstractGradientMethod(double, boolean, String)
     */
    public FastGradientDescentMethod(final double epsilon, boolean log, String fileName) throws FileNotFoundException {
        super(epsilon, log, fileName);
    }


    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {
        log(new double[]{3.0, 3.0}, new double[]{3.0, 3.0});
        double[] x = x0;
        while (true) {
            double[] gradient = function.runGradient(x);
            if (norm(gradient) < epsilon) {
                break;
            }
            log(x, gradient);
            x = subtract(x, multiply(gradient, calculateStep(x, gradient, function)));
        }
        return x;
    }

    /**
     * function for computing of the step.
     * @param x start point
     * @param gradient gradient of the fucntion
     * @param function explored function
     * @return step for {@link #findMinimum(Function, double[])}
     */
    private double calculateStep(double[] x, double[] gradient, Function function) {
        MathFunction fun = alpha -> function.run(subtract(x, multiply(gradient, alpha)));
        return new ParabolSearch(fun, 0, 10, epsilon).searchMinimum();
    }
}
