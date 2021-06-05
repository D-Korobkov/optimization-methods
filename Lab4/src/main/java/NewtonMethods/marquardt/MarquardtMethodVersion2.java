package NewtonMethods.marquardt;

import cholesky.CholeskySolver;
import interfaces.Function;
import logger.FieldLogger;

import java.util.Arrays;
import java.util.List;

import static SaZhaK.MatrixUtil.*;

/**
 * класс для поиска минимума функции методом Марквардта с использованием разложения Холецкого
 */
public class MarquardtMethodVersion2 extends MarquardtCommon {

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
        super(new CholeskySolver(), 0.000001, 2, 0);
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
        super(new CholeskySolver(), epsilon, beta, 0);
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
            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);

            double[] direction;
            do {
                direction = solver.solve(add(hessian, multiply(I, step)), antiGradient, epsilon);
                if (direction != null) {
                    break;
                }
                step = Math.max(1, beta * step);
            } while (true);

            x = add(x, direction);

            if (norm(direction) <= epsilon) {
                break;
            }
        }

        return x;
    }

    public double[] findMinimumWithLog(Function function, double[] x0, String functionName) throws Exception {
        FieldLogger logger = new FieldLogger(
                "/method/newton/marquardt_2/" + functionName + "/", List.of("lambda", "cholesky", "x")
        );

        double[][] I = getI(x0.length);
        double[] x = x0;
        double step = lambda;

        while (true) {
            double[] antiGradient = multiply(function.runGradient(x), -1);
            double[][] hessian = function.runHessian(x);

            double[] direction;
            int numberOfCholeskyDecompositions = 0;
            do {
                numberOfCholeskyDecompositions++;

                direction = solver.solve(add(hessian, multiply(I, step)), antiGradient, epsilon);
                if (direction != null) {
                    break;
                }
                step = Math.max(1, beta * step);
            } while (true);

            x = add(x, direction);

            logger.log("lambda", Double.toString(step));
            logger.log("cholesky", Integer.toString(numberOfCholeskyDecompositions));

            if (norm(direction) <= epsilon) {
                break;
            }
        }

        logger.log("x", Arrays.toString(x).replaceAll("[\\[\\]]", ""));
        logger.close();

        return x;
    }
}
