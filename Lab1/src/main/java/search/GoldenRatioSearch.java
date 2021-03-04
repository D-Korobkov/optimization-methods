package search;

import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;
import strategies.GoldenRatioSearchStrategy;

/**
 * Метод золотого сечения
 */
public class GoldenRatioSearch extends AbstractSearch {
    /**
     * Поле стратегия для поиска минимума методом золотого сечения
     *
     * @see Strategy
     * @see GoldenRatioSearchStrategy
     */
    private final Strategy strategy;

    /**
     * {@link AbstractSearch#AbstractSearch(MathFunction, double, double, double)}
     */
    public GoldenRatioSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new GoldenRatioSearchStrategy(epsilon);
    }

    /**
     * {@link Search#searchMinimum()}
     */
    @Override
    public double searchMinimum() {
        return super.optimizedSearchMinimum(strategy);
    }
}
