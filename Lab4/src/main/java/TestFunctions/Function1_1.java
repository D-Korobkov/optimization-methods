package TestFunctions;

import functions.QuadraticFunction;
import interfaces.Function;

public class Function1_1 implements Function {

    private static final Function function = new QuadraticFunction(new double[][]{{2, 1.2}, {1.2, 2}}, new double[]{0, 0}, 0);

    @Override
    public double run(double[] x) {
        return function.run(x);
    }

    @Override
    public double[] runGradient(double[] x) {
        return function.runGradient(x);
    }

    @Override
    public double[] multiply(double[] x) {
        return function.multiply(x);
    }

    @Override
    public double[][] runHessian(double[] x) {
        return function.runHessian(x);
    }
}
