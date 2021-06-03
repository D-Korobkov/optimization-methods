package NewtonMethods;

import SaZhaK.MatrixUtil;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;
import interfaces.Solver;
import logger.FieldLogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * класс для поиска минимума функции методом Ньютона без модификаций
 */
public class ClassicNewtonMethod implements Method {

    /**
     * число итераций метода
     */
    private static int numberOfIterations = 0;

    /**
     * метод решения СЛАУ
     */
    Solver solver;
    /**
     * точность вычислений
     */
    Double epsilon;

    /**
     * создаёт экземпляр класса с пользовательскими параметрами
     * @param solver метод решения СЛАУ
     * @param epsilon точность вычислений
     */
    public ClassicNewtonMethod(Solver solver, double epsilon){
        this.solver = solver;
        this.epsilon = epsilon;
    }

    /**
     * дефолтный конструктор:
     * <ul>
     *     <li>СЛАУ будет решаться методом Гаусса с выбором опорного элемента по всей матрице</li>
     *     <li>точность - {@code 10^-6}</li>
     * </ul>
     */
    public ClassicNewtonMethod(){
        this.solver = new GaussSolver();
        this.epsilon = 0.000001;
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    public double[] findMinimumWithLog(Function function, double[] x0, String functionName) throws Exception {

        FieldLogger logger = new FieldLogger("/method/newton/classic/" + functionName + "/", List.of("x", "iterations"));

        double[] curX = x0;
        double diff;

        do {
            numberOfIterations++;

            double[] prevX = curX;

            double[] p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1));

            curX = MatrixUtil.add(prevX, p);

            logger.log("x", Arrays.toString(curX).replace(",", ""));

            diff = MatrixUtil.norm(MatrixUtil.subtract(curX, prevX));

        } while(diff > epsilon);

        logger.log("x", Arrays.toString(curX).replace(",", ""));
        //logger.log("x", Arrays.toString(curX).replaceAll("[ \\[\\]]", "") + System.lineSeparator());
        logger.log("iterations",numberOfIterations + System.lineSeparator());

        logger.close();

        return curX;
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(Function function, double[] x0) {

        double[] curX = x0;
        double diff;

        do {
            numberOfIterations++;

            double[] prevX = curX;

            double[] p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1));

            curX = MatrixUtil.add(prevX, p);

            diff = MatrixUtil.norm(MatrixUtil.subtract(curX, prevX));

        } while(diff > epsilon);

        return curX;
    }


}
