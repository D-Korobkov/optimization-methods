package strategies;

import interfaces.Strategy;

/**
 * Класс со стратегией поиска точки минимума методом дихотомии
 *
 * @see interfaces.Strategy
 */
public class DichotomySearchStrategy implements Strategy {

    private final double epsilon, delta;

    /**
     * Конструктор - создание стратегии
     *
     * @param epsilon заданная точность
     * @param delta   заданный шаг
     * @see search.DichotomySearch
     */
    public DichotomySearchStrategy(double epsilon, double delta) {
        this.epsilon = epsilon;
        this.delta = delta;
    }

    /**
     * {@link interfaces.Strategy#isEnd(double, double)}
     */
    @Override
    public boolean isEnd(double left, double right) {
        return right - left <= 2 * epsilon;
    }

    /**
     * {@link interfaces.Strategy#runForLeftBorder(double, double)}
     */
    @Override
    public double runForLeftBorder(double left, double right) {
        return (right + left - delta) / 2;
    }

    /**
     * {@link interfaces.Strategy#runForRightBorder(double, double)}
     */
    @Override
    public double runForRightBorder(double left, double right) {
        return (right + left + delta) / 2;
    }
}
