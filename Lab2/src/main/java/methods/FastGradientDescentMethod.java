package methods;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import search.BrentSearch;


import java.io.*;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

public class FastGradientDescentMethod implements Method {
    private final double epsilon;
    private final boolean log;
    private final BufferedWriter out;


    public FastGradientDescentMethod(final double epsilon) {

        this.epsilon = epsilon;
        this.log = false;
        this.out = null;
    }

    public FastGradientDescentMethod(final double epsilon, boolean log, String fileName) throws FileNotFoundException {

        this.epsilon = epsilon;
        this.log = log;
        this.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
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

    private void log(double[] x, double[] gradient) throws IOException {
        if(!log) return;
        assert out != null;
        out.write(Arrays.toString(x) + ":" + Arrays.toString(x) + "\n");
        out.flush();
    }
}