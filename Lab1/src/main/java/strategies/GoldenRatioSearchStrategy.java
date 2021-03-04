package strategies;

import interfaces.Strategy;

/**
 * Класс со стратегией поиска точки минимума методом золотого сечения
 *
 * @see interfaces.Strategy
 */
public class GoldenRatioSearchStrategy implements Strategy {
    /** Поле значение золотого сечения, уменьшенное на единицу */
    private static final double phi = (Math.sqrt(5) - 1) / 2;
    /** Поле заданная точность */
    private final double epsilon;

    /**
     * Конструктор - создание стратегии
     *
     * @param epsilon заданная точность
     * @see search.GoldenRatioSearch
     */
    public GoldenRatioSearchStrategy(double epsilon) {
        this.epsilon = epsilon;
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
        return right - phi * (right - left);
    }

    /**
     * {@link interfaces.Strategy#runForRightBorder(double, double)}
     */
    @Override
    public double runForRightBorder(double left, double right) {
        return left + phi * (right - left);
    }
}
