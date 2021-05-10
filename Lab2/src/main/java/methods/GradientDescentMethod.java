package methods;

import interfaces.Function;
import java.io.*;

import static SaZhaK.MatrixUtil.*;


/**
 * предоставляет возможность искать минимум квадратичных функций методом градиентного спуска
 */
public class GradientDescentMethod extends AbstractGradientMethod {
    /**
     * step of Gradient method 
     */
    private double step;


    /**
     * Full constructor
     * @param step {@link #step}
     * @param epsilon {@link #epsilon}
     * @param log {@link AbstractGradientMethod#log}
     * @param fileName output file for {@link AbstractGradientMethod#out}
     * @throws FileNotFoundException if specified output file was not found
     * @see AbstractGradientMethod#AbstractGradientMethod(double, boolean, String)
     */
    public GradientDescentMethod(final double step, final double epsilon, final boolean log, final String fileName) throws FileNotFoundException {

        super(epsilon, log, fileName);
        this.step = step;
    }

    /**
     * Standard constructor
     * @param step {@link #step}
     * @param epsilon {@link AbstractGradientMethod#epsilon}
     * @see AbstractGradientMethod#AbstractGradientMethod(double) 
     */
    public GradientDescentMethod(final double step, final double epsilon) {
        super(epsilon);
        this.step = step;

    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {



        double[] prevX = x0;
        double prevFunctionValue = function.run(x0);
        while (true) {
            double[] gradient = function.runGradient(prevX);
            if (norm(gradient) < epsilon) {
                break;
            }

            double[] nextX = subtract(prevX, multiply(gradient, step));
            double nextFunctionValue = function.run(nextX);
            if (nextFunctionValue < prevFunctionValue) {
                prevX = nextX;
                prevFunctionValue = nextFunctionValue;
                log(prevX, gradient);
            } else {
                step /= 2;
            }
        }
        return prevX;
    }
}
