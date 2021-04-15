package methods;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import search.BrentSearch;


import java.io.*;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

public class FastGradientDescentMethod extends LoggingMethod{
    private final double epsilon;


    public FastGradientDescentMethod(final double epsilon) {
        super(false);
        this.epsilon = epsilon;
    }

    public FastGradientDescentMethod(final double epsilon, boolean log, String fileName) throws FileNotFoundException {
        super(log, fileName);
        this.epsilon = epsilon;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {
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

    private double calculateStep(double[] x, double[] gradient, Function function) {
        MathFunction fun = alpha -> function.run(subtract(x, multiply(gradient, alpha)));
        return new BrentSearch(fun,0, 10, epsilon).searchMinimum();
    }
}
