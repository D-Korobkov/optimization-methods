package QuasiNewton;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Search;
import search.GoldenRatioSearch;
import static SaZhaK.MatrixUtil.add;
import static SaZhaK.MatrixUtil.multiply;

/**
 * класс, содержащий общие функции для квазиньютоновских методов
 */
public abstract class AbstractQuasiMethod implements Method {
    /**
     * точность вычислений
     */
    protected final double eps;

    /**
     * создаёт экземпляр класса
     * @param eps точность вычислений
     */
    protected AbstractQuasiMethod(double eps) {
        this.eps = eps;
    }

    /**
     * создание единичной матрицы указанной размерности
     * @param length размерность матрицы
     * @return единичная матрица размера {@code length * length}
     */
    protected double[][] createI(int length) {
        double[][] ans = new double[length][length];
        for (int i = 0; i < length; i++) {
            ans[i][i] = 1;
        }
        return ans;
    }

    /**
     * вычисление следующего приближения
     * @param function функция, на которой ищется точка минимума
     * @param x0 некоторое приближение
     * @param p вектор направления
     * @return следующее приближение
     */
    protected double[] findNextX(Function function, double[] x0, double[] p) {
        double a = findLinearMinimum(function, x0, p);
        p = multiply(p, a);
        return add(x0, p);
    }

    /**
     * вычисление величины шага с использованием метода золотого сечения
     * @param function функция, на которой ищется точка минимума
     * @param x начальная точка
     * @param p вектор направления
     * @return величина оптимального шага
     */
    protected double findLinearMinimum(Function function, double[] x, double[] p) {
        MathFunction f = a -> function.run(add(x, multiply(p, a)));
        Search search = new GoldenRatioSearch(f, 0, 10, eps);
        return search.searchMinimum();
    }
}
