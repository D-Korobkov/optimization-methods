package NewtonMethods.marquardt;

import cholesky.CholeskySolver;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Solver;
import logger.FieldLogger;
import search.BrentSearch;
import search.GoldenRatioSearch;
import search.ParabolSearch;

import java.util.Arrays;
import java.util.List;

import static SaZhaK.MatrixUtil.*;

/**
 * класс для поиска минимума функции методом Марквардта без использования разложения Холецкого
 */
public class MarquardtMethodVersion1 extends MarquardtCommon {
    /**
     * дефолтный конструктор:
     * <ul>
     *     <li>СЛАУ будет решаться методом Гаусса с выбором опорного элемента по всей матрице</li>
     *     <li>точность - {@code 10^-6}</li>
     *     <li>значение {@code beta} - {@code 2}</li>
     *     <li>начальное значение {@code lambda} - {@code 10000}</li>
     * </ul>
     */
    public MarquardtMethodVersion1() {
        super(new GaussSolver(), 0.000001, 0.5, 1000000000000000D);
    }

    /**
     * <p>создаёт экземпляр класса с пользовательскими параметрами параметрами</p>
     * {@link MarquardtCommon}
     */
    public MarquardtMethodVersion1(final Solver solver, final double epsilon, final double lambda, final double beta) {
        super(solver, epsilon, beta, lambda);
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(final Function function, final double[] x0) {
        double[][] I = getI(x0.length);
        double[] x = x0;
        double step = lambda;

        double[] optimalDirection;
        do {
            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);
            double[] direction = solver.solve(add(hessian, multiply(I, step)), antiGradient, epsilon);

            double alpha = new BrentSearch(getOptimizedFunction(function, x, direction), -100000, 100000, epsilon).searchMinimum();
            optimalDirection = multiply(direction, alpha);

            x = add(x, optimalDirection);
            step *= beta;
        } while (norm(optimalDirection) > epsilon);

        return x;
    }

    public double[] findMinimumWithLog(final Function function, final double[] x0, final String functionName) throws Exception {
        FieldLogger logger = new FieldLogger(
                "/method/newton/marquardt_1/" + functionName + "/", List.of("lambda", "x")
        );

        double[][] I = getI(x0.length);
        double[] x = x0;
        double step = lambda;

        double[] optimalDirection;
        do {
            logger.log("lambda", Double.toString(step));

            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);
            double[] direction = solver.solve(add(hessian, multiply(I, step)), antiGradient, epsilon);

            double alpha = findAlpha(function, x, direction);
            optimalDirection = multiply(direction, alpha);

            x = add(x, optimalDirection);
            step *= beta;
        } while (norm(optimalDirection) > epsilon);

        logger.log("x", Arrays.toString(x).replaceAll("[\\[\\]]", ""));
        logger.close();

        return x;
    }

    private double findAlpha(final Function f, final double[] x, final double[] p) {
        return new BrentSearch(getOptimizedFunction(f, x, p), -100000, 100000, epsilon).searchMinimum();
    }

    private MathFunction getOptimizedFunction(final Function f, final double[] x, final double[] p) {
        return alpha -> f.run(add(Arrays.copyOf(x, x.length), multiply(Arrays.copyOf(p, p.length), alpha)));
    }
}
