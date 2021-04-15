package Functions;

import interfaces.Function;
import methods.ConjugateGradientMethod;
import methods.FastGradientDescentMethod;
import methods.GradientDescentMethod;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class to show how methods work
 */
public class HandlerForFunctions {
    /**
     * list of quadratic functions to find minimum of
     */
    private final List<Function> functions;

    /**
     * create a list of three different quadratic functions
     */
    public HandlerForFunctions() {
        this.functions = new ArrayList<>();

        functions.add(new QuadraticFunction(new double[][]{{2, 2}, {2, 2}}, new double[]{-1, -1}, 0.0));
        functions.add(new QuadraticFunction(new double[][]{{40, 2}, {2, 20}}, new double[]{-1, -1}, 0.0));
        functions.add(new QuadraticFunction(new double[][]{{400, 2}, {2, 100}}, new double[]{-1, -1}, 0.0));
    }

    /**
     * constructs an instance of class with specified quadratic functions ({@link Function}) to explore
     * @param functions list of quadratic functions
     */
    public HandlerForFunctions(final List<Function> functions) {
        this.functions = functions;
    }

    /**
     * this function print results of gradient search methods into the specified {@link OutputStream}
     * @param outputStream output stream we want to see the results of gradient search methods where
     * @throws IOException if some errors occurred during printing
     */
    public void printAll(final OutputStream outputStream) throws IOException {
        try (final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            double[] x0 = new double[]{0, 0};
            double epsilon = 0.00001;

            int i = 0;
            for (Function function : functions) {
                ConjugateGradientMethod m1 = new ConjugateGradientMethod(epsilon, true, "conj.out");
                FastGradientDescentMethod m2 = new FastGradientDescentMethod(epsilon, true, "fast.out");
                GradientDescentMethod m3 = new GradientDescentMethod(2, epsilon, true, "gradient.out");

                double[] x;

                out.write("======Function:" + (i + 1) + "\n");

                out.write("==Gradient Descent method for : " + Arrays.toString(x0) + " \n");
                x = m2.findMinimum(function, x0);
                out.write("min: " + Arrays.toString(x) + "\n" + "\n");

                out.write("==Fast Gradient Descent method for : " + Arrays.toString(x0) + " \n");
                x = m3.findMinimum(function, x0);
                out.write("min: " + Arrays.toString(x) + "\n" + "\n");

                out.write("==Conjugate Gradient method for : " + Arrays.toString(x0) + " \n");
                x = m1.findMinimum(function, x0);
                out.write("min: " + Arrays.toString(x) + "\n" + "\n");

                i++;
            }
        }
    }
}
