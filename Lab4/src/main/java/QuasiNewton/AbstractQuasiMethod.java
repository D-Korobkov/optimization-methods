package QuasiNewton;

import interfaces.Function;
import interfaces.MathFunction;
import interfaces.Method;
import interfaces.Search;
import logger.FieldLogger;
import search.BrentSearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    protected int iterations = 0;
    public boolean log = false;
    public FieldLogger logger;

    /**
     * создаёт экземпляр класса
     * @param eps точность вычислений
     */
    protected AbstractQuasiMethod(double eps) {
        this.eps = eps;
    }

    protected AbstractQuasiMethod(double eps, String path) {
        this(eps);
        log = true;
        logger = new FieldLogger(path, List.of("iterations", "trace", "alpha"));
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
        double[] ans = add(x0, p);
        if (log) {
            logger.log("alpha", String.valueOf(a));
            logger.log("trace", "[" + Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(" ")) + " ]");
        }
        return ans;
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
        Search search = new BrentSearch(f, 0, 10, eps);
        return search.searchMinimum();
    }
}
