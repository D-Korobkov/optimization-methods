package NewtonMethods.marquardt;

import interfaces.Method;
import interfaces.Solver;

import java.util.stream.IntStream;

public abstract class MarquardtCommon implements Method {
    protected final Solver solver;
    protected final double epsilon;
    protected final double beta;
    protected final double lambda;

    MarquardtCommon(final Solver solver, final double epsilon, final double beta, final double lambda) {
        this.solver = solver;
        this.epsilon = epsilon;
        this.beta = beta;
        this.lambda = lambda;
    }

    protected double[][] getI(final int dimension) {
        return IntStream.range(0, dimension).mapToObj(i -> {
            double[] line = new double[dimension];
            line[i] = 1;
            return line;
        }).toArray(double[][]::new);
    }
}
