package NewtonMethods;

import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;
import interfaces.Solver;

import java.util.stream.IntStream;

import static SaZhaK.MatrixUtil.*;

public class MarquardtMethod implements Method {
    private final Solver solver;
    private final double epsilon;
    private final double beta;
    private final double lambda;

    public MarquardtMethod() {
        solver = new GaussSolver();
        epsilon = 0.000001;
        lambda = 10000;
        beta = 2;
    }

    public MarquardtMethod(Solver solver, double epsilon, double lambda, double beta) {
        this.solver = solver;
        this.epsilon = epsilon;
        this.lambda = lambda;
        this.beta = beta;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) {
        double[][] I = IntStream.range(0, x0.length).mapToObj(i -> {
            double[] line = new double[x0.length];
            line[i] = 1;
            return line;
        }).toArray(double[][]::new);

        double[] x = x0;
        double step = lambda;
        while (true) {
            double fx = function.run(x);
            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);

            double[] direction = solver.solve(add(hessian, multiply(I, step)), antiGradient);
            double[] nextX = add(x, direction);
            double fNext = function.run(nextX);
            while (fNext >= fx) {
                step *= beta;
                direction = solver.solve(add(hessian, multiply(I, step)), antiGradient);
                nextX = add(x, direction);
                fNext = function.run(nextX);
            }

            step /= beta;
            x = nextX;
            if (norm(direction) <= epsilon) {
                break;
            }
        }

        return x;
    }
}
