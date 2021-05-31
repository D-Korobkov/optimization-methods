package NewtonMethods;

import SaZhaK.MatrixUtil;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Solver;
import search.BrentSearch;
import java.io.IOException;
import static SaZhaK.MatrixUtil.*;

public class NewtonMethodWithDescentDirection implements Method {
    Solver solver;
    Double epsilon;

    public NewtonMethodWithDescentDirection(Solver solver, double epsilon){
        this.solver = solver;
        this.epsilon = epsilon;
    }

    public NewtonMethodWithDescentDirection() {
        this.solver = new GaussSolver();
        this.epsilon = 0.000001;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {
        double diff;
        double[] nextX = x0;
        do {
            double[] prevX = nextX;
            double[] gradient = function.runGradient(prevX);
            double[] antiGradient = multiply(gradient, -1);
            double[] p = solver.solve(function.runHessian(prevX), antiGradient);

            final double[] direction = MatrixUtil.dotProduct(p, gradient) >= 0 ? antiGradient : p;

            MathFunction f = alpha -> function.run(MatrixUtil.add(prevX, MatrixUtil.multiply(direction, alpha)));
            double alpha = new BrentSearch(f, 0, 10, epsilon).searchMinimum();
            nextX = add(prevX, multiply(direction, alpha));

            diff = MatrixUtil.norm(MatrixUtil.subtract(nextX, prevX));
        } while (diff > epsilon);

        return nextX;
    }
}
