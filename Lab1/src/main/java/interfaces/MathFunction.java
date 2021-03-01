package interfaces;

/**
 * Интерфейс математической функции
 */
public interface MathFunction {
    /**
     * Метод вычисления значения функции в точке
     * @param x - значение точки, где нужно вычислить функцию
     * @return - значение f(x)
     */
    double run(double x);
}
