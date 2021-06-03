package NewtonMethods.marquardt;

import cholesky.CholeskySolver;
import interfaces.Function;
import logger.MathLogger;

import java.nio.file.Path;
import java.util.Arrays;

import static SaZhaK.MatrixUtil.*;

/**
 * класс для поиска минимума функции методом Марквардта с использованием разложения Холецкого
 */
public class MarquardtMethodVersion2 extends MarquardtCommon {
    /**
     * логгер, записывающий информацию о работе метода в res/log/marquardt_v2.txt
     */
    private static final MathLogger logger = new MathLogger(Path.of("res", "log", "marquardt_v2.txt"));

    /**
     * число итераций метода
     */
    private static int numberOfIterations = 0;

    /**
     * дефолтный конструктор:
     * <ul>
     *     <li>СЛАУ будет решаться с использованием разложения Холецкого</li>
     *     <li>точность - {@code 10^-6}</li>
     *     <li>значение {@code beta} - {@code 2}</li>
     *     <li>начальное значение {@code lambda} - {@code 0}</li>
     * </ul>
     */
    public MarquardtMethodVersion2() {
        super(new CholeskySolver(0.000001), 0.000001, 2, 0);
    }

    /**
     * <p>создаёт экземпляр класса с пользовательскими параметрами параметрами</p>
     * <ul>
     *     <li>СЛАУ будет решаться с использованием разложения Холецкого</li>
     *     <li>начальное значение {@code lambda} - {@code 0}</li>
     * </ul>
     * {@link MarquardtCommon}
     */
    public MarquardtMethodVersion2(final double epsilon, final double beta) {
        super(new CholeskySolver(epsilon), epsilon, beta, 0);
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(Function function, double[] x0) {
        double[][] I = getI(x0.length);
        double[] x = x0;
        double step = lambda;

        while (true) {
            int numberOfCholeskyDecompositions = 0;
            numberOfIterations++;

            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);

            double[] direction;
            do {
                numberOfCholeskyDecompositions++;

                direction = solver.solve(add(hessian, multiply(I, step)), antiGradient);
                if (direction != null) {
                    break;
                }
                step = Math.max(1, beta * step);
            } while (true);

            x = add(x, direction);
            step /= beta;

            logger.log(String.format("%s %s %s%n", numberOfIterations, step, numberOfCholeskyDecompositions));

            if (norm(direction) <= epsilon) {
                break;
            }
        }

        logger.log(Arrays.toString(x).replaceAll("[\\[\\]]", "") + System.lineSeparator());

        return x;
    }
}
