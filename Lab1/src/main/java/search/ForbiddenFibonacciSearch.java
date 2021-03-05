package search;

import interfaces.MathFunction;
import interfaces.Strategy;
import strategies.FibonacciSearchStrategy;

@Deprecated
public class ForbiddenFibonacciSearch extends AbstractSearch {
    private final Strategy strategy;

    public ForbiddenFibonacciSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new FibonacciSearchStrategy(rightBorder - leftBorder, epsilon);
    }

    @Override
    public double searchMinimum() {
        return searchMinimum(strategy);
    }
}
