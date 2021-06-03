package NewtonMethods;

import SaZhaK.MatrixUtil;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;
import interfaces.Solver;
import java.io.IOException;

public class ClassicNewtonMethod implements Method {


    Solver solver;
    Double epsilon;

    public ClassicNewtonMethod(Solver solver, double epsilon){
        this.solver = solver;
        this.epsilon = epsilon;
    }

    public ClassicNewtonMethod(){
        this.solver = new GaussSolver();
        this.epsilon = 0.000001;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {

        double[] curX = x0;
        double diff;

        do {
            double[] prevX = curX;

            double[] p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1));

            curX = MatrixUtil.add(prevX, p);

            diff = MatrixUtil.norm(MatrixUtil.subtract(curX, prevX));

        } while(diff > epsilon);

        return curX;
    }
}
