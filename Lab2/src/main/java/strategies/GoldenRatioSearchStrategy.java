package strategies;

import interfaces.Strategy;

/**
 * Класс со стратегией поиска точки минимума методом золотого сечения
 *
 * @see Strategy
 */
public class GoldenRatioSearchStrategy implements Strategy {
    /** Поле равное Ф + 1 - sqrt(5), где Ф - это золотая пропорция */
    private static final double phi = (3 - Math.sqrt(5)) / 2;
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
     * {@link Strategy#isEnd(double, double)}
     */
    @Override
    public boolean isEnd(double left, double right) {
        return right - left <= 2 * epsilon;
    }

    /**
     * {@link Strategy#runForLeftBorder(double, double)}
     */
    @Override
    public double runForLeftBorder(double left, double right) {
        return left + phi * (right - left);
    }

    /**
     * {@link Strategy#runForRightBorder(double, double)}
     */
    @Override
    public double runForRightBorder(double left, double right) {
        return right - phi * (right - left);
    }
}
