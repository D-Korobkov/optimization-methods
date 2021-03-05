package search;

import interfaces.MathFunction;
import interfaces.Strategy;
import strategies.GoldenRatioSearchStrategy;

@Deprecated
public class ForbiddenGoldenSearch extends AbstractSearch {
    private final Strategy strategy;

    public ForbiddenGoldenSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        strategy = new GoldenRatioSearchStrategy(epsilon);
    }

    @Override
    public double searchMinimum() {
        return super.searchMinimum(strategy);
    }
}
