package methods;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import search.BrentSearch;


import static SaZhaK.MatrixUtil.*;

public class FastGradientDescentMethod implements Method {
    private final double epsilon;

    public FastGradientDescentMethod(final double epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) {
        double[] x = x0;
        while (true) {
            double[] gradient = function.runGradient(x);
            if (norm(gradient) < epsilon) {
                break;
            }
            x = subtract(x, multiply(gradient, calculateStep(x, gradient, function)));
        }
        return x;
    }

    private double calculateStep(double[] x, double[] gradient, Function function) {
        MathFunction fun = alpha -> function.run(subtract(x, multiply(gradient, alpha)));
        return new BrentSearch(fun,0, 10, epsilon).searchMinimum();
    }
}
