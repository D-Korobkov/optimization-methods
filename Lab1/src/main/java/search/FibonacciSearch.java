package search;

import chart.UserPanel;
import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;
import strategies.FibonacciSearchStrategy;

/**
 * Класс для поиска методом Фибоначчи
 * @see search.AbstractSearch
 */
public class FibonacciSearch extends AbstractSearch {
    /**
     * Поле стратегия для поиска минимума методом Фибоначчи
     * @see Strategy
     * @see FibonacciSearchStrategy
     */
    private final Strategy strategy;

    /**
     * {@link AbstractSearch#AbstractSearch(MathFunction, double, double, double)}
     */
    public FibonacciSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon, UserPanel userPanel) {
        super(function, leftBorder, rightBorder, epsilon, userPanel);
        strategy = new FibonacciSearchStrategy(rightBorder - leftBorder, epsilon);
    }

    /**
     * {@link Search#searchMinimum()}
     */
    @Override
    public double searchMinimum() {
        return optimizedSearchMinimum(strategy);
    }
}
