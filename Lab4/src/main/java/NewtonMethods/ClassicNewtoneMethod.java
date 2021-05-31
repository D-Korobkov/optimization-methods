package NewtonMethods;

import SaZhaK.Matrix;
import SaZhaK.MatrixUtil;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;
import interfaces.Solver;

import java.io.IOException;

public class ClassicNewtoneMethod implements Method {


    Solver solver;
    Double epsilon;

    public ClassicNewtoneMethod(Solver solver, double epsilon){
        this.solver = solver;
        this.epsilon = epsilon;
    }

    public ClassicNewtoneMethod(){
        this.solver = new GaussSolver();
        this.epsilon = 0.000001;
    }

    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {

        double[] prevX = x0;
        double[] p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1));

        double[] curX = MatrixUtil.add(prevX, p);

        while(MatrixUtil.norm(MatrixUtil.subtract(curX, prevX)) < epsilon || MatrixUtil.norm(p) < epsilon){
            prevX = curX;

            p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1));

            curX = MatrixUtil.add(prevX, p);

        }

        return curX;
    }
}
