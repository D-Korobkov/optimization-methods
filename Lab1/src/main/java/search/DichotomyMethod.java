package search;

import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;

/**
 * @see search.AbstractSearch 
 * Класс для поиска методом дихотомии
 */
public class DichotomyMethod extends AbstractSearch {
    /** Поле стратегия */
    private final Strategy strategy;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param function - исследуемая функция
     * @param leftBorder - левая граница отрезка
     * @param rightBorder - правая граница отрезка
     * @param epsilon - требуемая точность
     * @param delta - длина шага (рекомендуется, чтобы delta <= 2 * epsilon)
     * @see AbstractSearch
     */
    public DichotomyMethod(MathFunction function,
                           double leftBorder,
                           double rightBorder,
                           double epsilon,
                           double delta) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new SimpleStrategy(epsilon, delta);
    }

    /**
     * {@link Search#searchMinimum()}
     */
    public double searchMinimum() {
        return super.searchMinimum(strategy);
    }

    /**
     * Класс со стратегией поиска точки минимума в методе дихотомии
     * @see interfaces.Strategy
     */
    private static final class SimpleStrategy implements Strategy {
        private final double epsilon, delta;

        /**
         * Конструктор - создание стратегии
         * @param epsilon - заданная точность
         * @param delta - заданный шаг
         * @see DichotomyMethod
         */
        private SimpleStrategy(double epsilon, double delta) {
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
}
