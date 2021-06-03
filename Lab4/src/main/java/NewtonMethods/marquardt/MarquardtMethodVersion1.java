package NewtonMethods.marquardt;

import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Solver;

import static SaZhaK.MatrixUtil.*;

public class MarquardtMethodVersion1 extends MarquardtCommon {
    public MarquardtMethodVersion1() {
        super(new GaussSolver(), 0.000001, 2, 10000);
    }

    public MarquardtMethodVersion1(final Solver solver, final double epsilon, final double lambda, final double beta) {
        super(solver, epsilon, beta, lambda);
    }

    @Override
    public double[] findMinimum(final Function function, final double[] x0) {
        double[][] I = getI(x0.length);
        double[] x = x0;
        double step = lambda;

        while (true) {
            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);

            double[] direction = solver.solve(add(hessian, multiply(I, step)), antiGradient);
            double[] nextX = add(x, direction);

            double fx = function.run(x);
            double fNext = function.run(nextX);
            while (fNext > fx) {
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
