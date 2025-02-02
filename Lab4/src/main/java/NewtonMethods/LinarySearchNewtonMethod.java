package NewtonMethods;

import SaZhaK.MatrixUtil;
import gauss.GaussSolver;
import interfaces.*;
import logger.FieldLogger;
import search.BrentSearch;

import java.util.Arrays;
import java.util.List;

/**
 * класс для поиска минимума функции методом Ньютона с использованием метода Брента для вычисления шага
 */
public class LinarySearchNewtonMethod implements Method {
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
     *
     * @param solver  метод решения СЛАУ
     * @param epsilon точность вычислений
     */
    public LinarySearchNewtonMethod(Solver solver, double epsilon) {
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
    public LinarySearchNewtonMethod() {
        this.solver = new GaussSolver();
        this.epsilon = 0.000001;
    }

    /**
     * {@inheritDoc}
     *
     * @param function исследуемая функция
     * @param x0       начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(Function function, double[] x0) {
        double[] prevX = x0;
        double[] p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1), epsilon);

        double[] curX = MatrixUtil.add(prevX, p);

        while (MatrixUtil.norm(MatrixUtil.subtract(curX, prevX)) > epsilon && MatrixUtil.norm(p) > epsilon) {
            prevX = curX;
            p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1), epsilon);

            double[] finalPrevX = prevX;
            double[] finalP = p;
            MathFunction fun = v -> function.run(MatrixUtil.add(finalPrevX, MatrixUtil.multiply(finalP, v)));
            Search search = new BrentSearch(fun, -100, 100, epsilon);//TODO: deal with borders

            double a = search.searchMinimum();

            curX = MatrixUtil.add(prevX, MatrixUtil.multiply(p, a));
        }

        return curX;
    }

    public double[] findMinimumWithLog(Function function, double[] x0, String functionName) throws Exception {
        FieldLogger logger = new FieldLogger(
                "/method/newton/linear/" + functionName + "/", List.of("x", "iterations", "alpha")
        );
        numberOfIterations = 1;

        logger.log("x", Arrays.toString(x0).replaceAll("[ \\[\\]]", "").replace(",", ", "));

        double[] prevX = x0;
        double[] p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1), epsilon);

        double[] curX = MatrixUtil.add(prevX, p);

        logger.log("x", Arrays.toString(curX).replaceAll("[ \\[\\]]", "").replace(",", ", "));

        while (MatrixUtil.norm(MatrixUtil.subtract(curX, prevX)) > epsilon && MatrixUtil.norm(p) > epsilon) {
            numberOfIterations++;
            prevX = curX;

            p = solver.solve(function.runHessian(prevX), MatrixUtil.multiply(function.runGradient(prevX), -1), epsilon);

            double[] finalPrevX = prevX;
            double[] finalP = p;
            MathFunction fun = v -> function.run(MatrixUtil.add(finalPrevX, MatrixUtil.multiply(finalP, v)));

            Search search = new BrentSearch(fun, -1000000, 100000, epsilon);//TODO: deal with borders

            double a = search.searchMinimum();


            curX = MatrixUtil.add(prevX, MatrixUtil.multiply(p, a));

            logger.log("x", Arrays.toString(curX).replaceAll("[ \\[\\]]", "").replace(",", ", "));

            logger.log("alpha", Double.toString(a));
        }

        logger.log("iterations", Integer.toString(numberOfIterations));
        logger.log("x", String.format("%s", Arrays.toString(curX).replaceAll("[\\[\\]]", "")));
        logger.close();

        return curX;
    }
}
