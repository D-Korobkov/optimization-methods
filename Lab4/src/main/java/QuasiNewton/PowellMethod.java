package QuasiNewton;

import interfaces.Function;
import java.io.IOException;

import static SaZhaK.MatrixUtil.*;

/**
 * класс для поиска минимума функции методом Пауэлла
 */
public class PowellMethod extends AbstractQuasiMethod {

    /**
     * создание экземпляра класса
     * @param eps точность вычислений
     */
    public PowellMethod(double eps) {
        super(eps);
    }

    public PowellMethod(double eps, String path) {
        super(eps, path);
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(Function function, double[] x0) {
        iterations = 0;
        double[][] C = createI(x0.length);
        double[] w = multiply(function.runGradient(x0), -1);
        do {
            x0 = iterations(function, x0, C, w);
            C = createI(x0.length);
            w = multiply(function.runGradient(x0), -1);
        } while (norm(w) > eps);
        if (log) {
            logger.log("iterations", String.valueOf(iterations));
        }
        return x0;
    }

    /**
     * Итерационнный процесс для findMinimum
     */
    private double[] iterations(Function function, double[]x0, double[][] C, double[] w) {
        int i = 0;
        while (norm(w) > eps && i < w.length) {
            double[] p = multiply(C, w);
            double[] nextX = findNextX(function, x0, p);
            double[] nextW = multiply(function.runGradient(nextX), -1);
            double[] deltaX = subtract(nextX, x0);
            double[] deltaW = subtract(nextW, w);
            C = getNextC(C, add(deltaX, multiply(C, deltaW)), deltaW);
            x0 = nextX;
            w = nextW;
            i++;
        }
        iterations += i;
        return x0;
    }

    /**
     * вычисляет следующее интерационное приближение
     * @param C текущее приближение
     * @param deltaX разность приближений
     * @param deltaW разность градиентов
     * @return следующее итерационное приближение (матрицу близкую к матрице Гессе)
     */
    private double[][] getNextC(double[][] C, double[] deltaX, double[] deltaW) {
        double k = 1 / dotProduct(deltaW, deltaX);
        return subtract(C, multiply(multiply(deltaX, deltaX), k));
    }
}
