package QuasiNewton;

import interfaces.Function;
import java.io.IOException;
import static SaZhaK.MatrixUtil.*;

/**
 * класс для поиска минимума функции методом Бройдена-Флетчера-Шено
 */
public class BFGS extends AbstractQuasiMethod {

    /**
     * создание экземпляра класса
     * @param eps точность вычислений
     */
    public BFGS(double eps) {
        super(eps);
    }

    /**
     * {@inheritDoc}
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума функции
     */
    @Override
    public double[] findMinimum(Function function, double[] x0) {
        double[][] C = createI(x0.length);
        double[] grad = function.runGradient(x0);
        while (norm(grad) > eps) {
            double[] p = multiply(multiply(C, grad), -1);
            double[] nextX = findNextX(function, x0, p);
            double[] nextGrad = function.runGradient(nextX);
            C = getNextC(C, subtract(nextX, x0), subtract(nextGrad, grad));
            x0 = nextX;
            grad = nextGrad;
        }
        return x0;
    }

    /**
     * вычисляет следующее интерационное приближение
     * @param C текущее приближение
     * @param s разность приближений
     * @param y разность градиентов
     * @return следующее итерационное приближение (матрицу близкую к матрице Гессе)
     */
    private double[][] getNextC(double[][] C, double[] s, double[] y) {
        double p = 1 / dotProduct(y, s);
        double[][] nextC = subtract(createI(C.length), multiply(multiply(s, y), p));
        nextC = multiply(nextC, C);
        nextC = multiply(nextC, subtract(createI(C.length), multiply(multiply(y, s), p)));
        nextC = add(nextC, multiply(multiply(s, s), p));
        return nextC;
    }
}
