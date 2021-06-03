package NewtonMethods.marquardt;

import cholesky.CholeskySolver;
import interfaces.Function;

import static SaZhaK.MatrixUtil.*;

public class MarquardtMethodVersion2 extends MarquardtCommon {
    public MarquardtMethodVersion2() {
        super(new CholeskySolver(0.000001), 0.000001, 2, 0);
    }

    public MarquardtMethodVersion2(final double epsilon, final double beta) {
        super(new CholeskySolver(epsilon), epsilon, beta, 0);
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) {
        double[][] I = getI(x0.length);
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
                step = Math.max(1, beta * step);
            } while (true);

            x = add(x, direction);
            step /= beta;

            if (norm(direction) <= epsilon) {
                break;
            }
        }

        return x;
    }
}
