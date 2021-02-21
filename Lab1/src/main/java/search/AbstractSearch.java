package search;

import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;

public abstract class AbstractSearch implements Search {
    protected final MathFunction function;
    protected final double leftBorder, rightBorder;

    public AbstractSearch(MathFunction function, double leftBorder, double rightBorder) {
        this.function = function;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    protected double searchMinimum(final Strategy calculation) {
        double left = leftBorder;
        double right = rightBorder;
        while (!calculation.isEnd(left, right)) {
            double leftMid = calculation.runForLeftBorder(left, right);
            double rightMid = calculation.runForRightBorder(left, right);
            if (function.run(leftMid) <= function.run(rightMid)) {
                right = rightMid;
            } else {
                left = leftMid;
            }
        }
        return (left + right) / 2;
    }
}
