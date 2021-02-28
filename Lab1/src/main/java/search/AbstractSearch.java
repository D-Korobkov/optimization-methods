package search;

import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;

public abstract class AbstractSearch implements Search {
    protected final MathFunction function;
    protected final double leftBorder, rightBorder, epsilon;
    private int counter = 0;
    private double prevLength = 1.0;

    public AbstractSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        this.function = function;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.epsilon = epsilon;
    }

    protected double searchMinimum(final Strategy calculation) {
        double left = leftBorder;
        double right = rightBorder;
        while (!calculation.isEnd(left, right)) {
            double leftMid = calculation.runForLeftBorder(left, right);
            double rightMid = calculation.runForRightBorder(left, right);
            printData(left, right, leftMid, rightMid, function.run(leftMid), function.run(rightMid));
            if (function.run(leftMid) <= function.run(rightMid)) {
                right = rightMid;
            } else {
                left = leftMid;
            }
        }
        return (left + right) / 2;
    }

    public void printData(double left, double right, double lMid, double rMid, double f1, double f2) {
        System.out.println(counter++ + " [" + left + ";" + right + "] " + (right - left) / prevLength + " " + lMid + ";" + rMid + " " + f1 + ";" + f2);
        prevLength = right - left;
    }
}
