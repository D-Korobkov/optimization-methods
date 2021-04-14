package methods;

import interfaces.Function;
import interfaces.Method;

import java.io.*;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

/**
 * предоставляет возможность искать минимум квадратичных функций методом градиентного спуска
 */
public class GradientDescentMethod implements Method {
    private final double epsilon;
    private final boolean log;
    private final BufferedWriter out;
    private double step;


    public GradientDescentMethod(final double step, final double epsilon, final boolean log, final String fileName) throws FileNotFoundException {
        this.step = step;
        this.epsilon = epsilon;
        this.log = log;
        this.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
    }

    public GradientDescentMethod(final double step, final double epsilon) {
        this.step = step;
        this.epsilon = epsilon;
        this.log = false;
        this.out = null;
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

    private void log(double[] x, double[] gradient) throws IOException {
        if (!log) return;
        assert out != null;
        out.write(Arrays.toString(x) + ":" + Arrays.toString(x) + "\n");
        out.flush();
    }
}
