package search;

import chart.UserPanel;
import interfaces.MathFunction;
import interfaces.Strategy;
import strategies.FibonacciSearchStrategy;

@Deprecated
public class ForbiddenFibonacciSearch extends AbstractSearch {
    private final Strategy strategy;

    public ForbiddenFibonacciSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon, UserPanel userPanel) {
        super(function, leftBorder, rightBorder, epsilon, userPanel);
        strategy = new FibonacciSearchStrategy(rightBorder - leftBorder, epsilon);
    }

    @Override
    public double searchMinimum() {
        return searchMinimum(strategy);
    }
}
