package NewtonMethods;

import cholesky.CholeskySolver;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;
import interfaces.Solver;

import java.util.stream.IntStream;

import static SaZhaK.MatrixUtil.*;

public class MarquardtMethodVersion2 implements Method {
    private final Solver solver;
    private final double epsilon;
    private final double beta;
    private final double lambda = 0;

    public MarquardtMethodVersion2() {
        epsilon = 0.000001;
        beta = 2;
        solver = new CholeskySolver(epsilon);
    }

    public MarquardtMethodVersion2(final double epsilon, final double beta) {
        this.epsilon = epsilon;
        this.beta = beta;
        this.solver = new CholeskySolver(epsilon);
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
            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);

            double[] direction;
            do {
                direction = solver.solve(add(hessian, multiply(I, step)), antiGradient);
                if (direction != null) {
                    break;
                }
                step = Math.max(1, 2 * step);
            } while (true);

            x = add(x, direction);

            if (norm(direction) <= epsilon) {
                break;
            }
        }

        return x;
    }
}
