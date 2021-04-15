package methods;

import interfaces.Function;
import interfaces.Method;

import java.io.*;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.norm;
import static SaZhaK.MatrixUtil.subtract;
import static SaZhaK.MatrixUtil.multiply;

public class GradientDescentMethod extends LoggingMethod {
    private double step;
    private final double epsilon;


    public GradientDescentMethod(final double step, final double epsilon, final boolean log, final String fileName) throws FileNotFoundException {
        super(log, fileName);
        this.step = step;
        this.epsilon = epsilon;
    }

    public GradientDescentMethod(final double step, final double epsilon) {
        super(false);
        this.step = step;
        this.epsilon = epsilon;
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
            } else {
                step /= 2;
            }
            log(prevX, gradient);
        }
        return prevX;
    }
}
