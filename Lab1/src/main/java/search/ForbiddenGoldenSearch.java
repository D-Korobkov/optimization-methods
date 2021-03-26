package search;

import chart.UserPanel;
import interfaces.MathFunction;
import interfaces.Strategy;
import strategies.GoldenRatioSearchStrategy;

@Deprecated
public class ForbiddenGoldenSearch extends AbstractSearch {
    private final Strategy strategy;

    public ForbiddenGoldenSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon, UserPanel userPanel) {
        super(function, leftBorder, rightBorder, epsilon, userPanel);
        strategy = new GoldenRatioSearchStrategy(epsilon);
    }

    @Override
    public double searchMinimum() {
        return searchMinimum(strategy);
    }
}
