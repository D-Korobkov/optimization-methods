package search;

import interfaces.MathFunction;
import interfaces.Strategy;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ForbiddenFibonacciSearch extends AbstractSearch {
    private final Strategy strategy;

    public ForbiddenFibonacciSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new FibonacciStrategy(rightBorder - leftBorder, epsilon);
    }

    @Override
    public double searchMinimum() {
        return super.searchMinimum(strategy);
    }

    private static final class FibonacciStrategy implements Strategy {
        private final List<Long> fibonacci; // maybe list of BigInts or list of Doubles?
        private int iterations = 1, passed = 0;
        private final double lengthOfSection;

        private FibonacciStrategy(double lengthOfSection, double epsilon) {
            this.lengthOfSection = lengthOfSection;

            fibonacci = new ArrayList<>();
            fibonacci.add(0L);
            fibonacci.add(1L);
            while (lengthOfSection / epsilon >= fibonacci.get(iterations)) {
                fibonacci.add(fibonacci.get(iterations) + fibonacci.get(iterations - 1));
                iterations++;
            }
        }

        @Override
        public boolean isEnd(double left, double right) {
            return passed++ > iterations - 2;
        }

        @Override
        public double runForLeftBorder(double left, double right) {
            return left + lengthOfSection * fibonacci.get(iterations - passed - 1) / fibonacci.get(iterations);
        }

        @Override
        public double runForRightBorder(double left, double right) {
            return left + lengthOfSection * fibonacci.get(iterations - passed) / fibonacci.get(iterations);
        }
    }
}