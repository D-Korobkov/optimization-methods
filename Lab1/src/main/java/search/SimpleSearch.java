package search;

import interfaces.MathFunction;
import interfaces.Strategy;

public class SimpleSearch extends AbstractSearch {
    private final Strategy strategy;

    public SimpleSearch(MathFunction function,
                        double leftBorder,
                        double rightBorder,
                        double epsilon,
                        double delta) {
        super(function, leftBorder, rightBorder);
        strategy = new SimpleStrategy(epsilon, delta);
    }

    public double searchMinimum() {
        return super.searchMinimum(strategy);
    }

    private static final class SimpleStrategy implements Strategy {
        private final double epsilon, delta;

        private SimpleStrategy(double epsilon, double delta) {
            this.epsilon = epsilon;
            this.delta = delta;
        }

        @Override
        public boolean isEnd(double left, double right) {
            return right - left <= 2 * epsilon;
        }

        @Override
        public double runForLeftBorder(double left, double right) {
            return (right + left - delta) / 2;
        }

        @Override
        public double runForRightBorder(double left, double right) {
            return (right + left + delta) / 2;
        }
    }
}
