package interfaces;

import logger.FieldLogger;

import java.io.IOException;

/**
 * интерефейс для методов, осуществляющих поиск минимума функции
 */
public interface Method {
    /**
     * метод находит минимум исследуемой функции, стартуя с указанного начального приближения
     * @param function исследуемая функция
     * @param x0 начальное приближение
     * @return точка минимума исследуемой функции
     */
    double[] findMinimum(Function function, double[] x0) throws IOException;
}
