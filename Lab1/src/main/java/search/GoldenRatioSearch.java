package search;

import chart.UserPanel;
import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;
import strategies.GoldenRatioSearchStrategy;

/**
 * Класс для поиска методом золотого сечения
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
    public GoldenRatioSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon, UserPanel userPanel) {
        super(function, leftBorder, rightBorder, epsilon, userPanel);
        strategy = new GoldenRatioSearchStrategy(epsilon);
    }

    /**
     * {@link Search#searchMinimum()}
     */
    @Override
    public double searchMinimum() {
        return optimizedSearchMinimum(strategy);
    }
}
