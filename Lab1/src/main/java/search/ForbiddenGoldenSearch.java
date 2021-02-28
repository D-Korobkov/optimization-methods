package search;

import interfaces.MathFunction;
import interfaces.Strategy;

public class ForbiddenGoldenSearch extends AbstractSearch {
    private final Strategy strategy;

    public ForbiddenGoldenSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new GoldenRatioStrategy(epsilon);
    }

    @Override
    public double searchMinimum() {
        return super.searchMinimum(strategy);
    }

    private static final class GoldenRatioStrategy implements Strategy {
        private final double epsilon;
        private final double phi = (3 - Math.sqrt(5)) / 2;

        GoldenRatioStrategy(double epsilon) {
            this.epsilon = epsilon;
        }

        @Override
        public boolean isEnd(double left, double right) {
            return right - left <= 2 * epsilon;
        }

        @Override
        public double runForLeftBorder(double left, double right) {
            return left + (right - left) * phi;
        }

        @Override
        public double runForRightBorder(double left, double right) {
            return right - (right - left) * phi;
        }
    }
}