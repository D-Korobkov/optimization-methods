package NewtonMethods.marquardt;

import interfaces.Method;
import interfaces.Solver;

import java.util.stream.IntStream;

/**
 * абстрактный класс для двух вариаций метода Марквардта
 */
public abstract class MarquardtCommon implements Method {
    /**
     * метод решения СЛАУ
     */
    protected final Solver solver;
    /**
     * точность вычислений
     */
    protected final double epsilon;
    /**
     * величина для изменения {@code lambda}
     */
    protected final double beta;
    /**
     * величина, на которую будет увеличиваться диагональ матрицы Гессе
     */
    protected final double lambda;

    /**
     * создаёт экземпляра класса
     * @param solver метод решения СЛАУ
     * @param epsilon точность вычислений
     * @param beta величина для изменения {@code lambda}
     * @param lambda величина, на которую будет увеличиваться диагональ матрицы Гессе
     */
    MarquardtCommon(final Solver solver, final double epsilon, final double beta, final double lambda) {
        this.solver = solver;
        this.epsilon = epsilon;
        this.beta = beta;
        this.lambda = lambda;
    }

    /**
     * создаёт единичную матрицу указанного размера
     * @param dimension размерность единичной матрицы
     * @return единичная матрица размера {@code dimension * dimension}
     */
    protected double[][] getI(final int dimension) {
        return IntStream.range(0, dimension).mapToObj(i -> {
            double[] line = new double[dimension];
            line[i] = 1;
            return line;
        }).toArray(double[][]::new);
    }
}
