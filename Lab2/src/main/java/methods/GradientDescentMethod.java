package methods;

import interfaces.Function;
import interfaces.Method;

import static SaZhaK.MatrixUtil.*;

/**
 * предоставляет возможность искать минимум квадратичных функций методом градиентного спуска
 */
public class GradientDescentMethod implements Method {
    private final double epsilon;
    private double step;

    public GradientDescentMethod(final double step, final double epsilon) {
        this.step = step;
        this.epsilon = epsilon;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) {
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
        }
        return prevX;
    }
}
