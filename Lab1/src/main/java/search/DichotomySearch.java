package search;

import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;
import strategies.DichotomySearchStrategy;

/**
 * Класс для поиска методом дихотомии
 *
 * @see search.AbstractSearch
 */
public class DichotomySearch extends AbstractSearch {
    /**
     * Поле стратегия для поиска минимума методом дихотомии
     * @see Strategy
     * @see DichotomySearchStrategy
     */
    private final Strategy strategy;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param function    исследуемая функция
     * @param leftBorder  левая граница отрезка
     * @param rightBorder правая граница отрезка
     * @param epsilon     требуемая точность
     * @param delta       длина шага (рекомендуется, чтобы delta <= 2 * epsilon)
     * @see AbstractSearch
     */
    public DichotomySearch(MathFunction function,
                           double leftBorder,
                           double rightBorder,
                           double epsilon,
                           double delta) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new DichotomySearchStrategy(epsilon, delta);
    }

    /**
     * {@link Search#searchMinimum()}
     */
    public double searchMinimum() {
        return searchMinimum(strategy);
    }
}
