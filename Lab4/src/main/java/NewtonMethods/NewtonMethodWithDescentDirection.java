package NewtonMethods;

import SaZhaK.MatrixUtil;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Solver;
import logger.MathLogger;
import search.BrentSearch;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

/**
 * класс для поиска минимума функции методом Ньютона с выбором направления спуска и вычсилением шага методом Брента
 */
public class NewtonMethodWithDescentDirection implements Method {
    /**
     * логгер, записывающий информацию о работе метода в res/log/newton_desc.txt
     */
    private static final MathLogger logger = new MathLogger(Path.of("res", "log", "newton_desc.txt"));

    /**
     * число итераций метода
     */
    private static int numberOfIterations = 0;

    /**
     * метод решения СЛАУ
     */
    private final Solver solver;
    /**
     * точность вычислений
     */
    private final Double epsilon;

    /**
     * создаёт экземпляр класса с пользовательскими параметрами
     * @param solver метод решения СЛАУ
     * @param epsilon точность вычислений
     */
    public NewtonMethodWithDescentDirection(Solver solver, double epsilon){
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
    public NewtonMethodWithDescentDirection() {
        this.solver = new GaussSolver();
        this.epsilon = 0.000001;
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(Function function, double[] x0) throws IOException {
        double diff;
        double[] nextX = x0;
        do {
            numberOfIterations++;

            double[] prevX = nextX;
            double[] gradient = function.runGradient(prevX);
            double[] antiGradient = multiply(gradient, -1);
            double[] p = solver.solve(function.runHessian(prevX), antiGradient);

            final double[] direction = MatrixUtil.dotProduct(p, gradient) >= 0 ? antiGradient : p;

            MathFunction f = alpha -> function.run(MatrixUtil.add(prevX, MatrixUtil.multiply(direction, alpha)));
            double alpha = new BrentSearch(f, 0, 10, epsilon).searchMinimum();
            nextX = add(prevX, multiply(direction, alpha));

            logger.log(String.format("%s %s%n",
                    Arrays.toString(prevX).replaceAll("[\\[\\]]", ""),
                    Arrays.toString(nextX).replaceAll("[\\[\\]]", ""))
            );

            diff = MatrixUtil.norm(MatrixUtil.subtract(nextX, prevX));
        } while (diff > epsilon);

        logger.log(Arrays.toString(nextX).replaceAll("[\\[\\]]", "") + System.lineSeparator());
        logger.log(numberOfIterations + System.lineSeparator());

        return nextX;
    }
}
