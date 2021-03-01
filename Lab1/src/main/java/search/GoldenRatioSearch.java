package search;

import interfaces.MathFunction;

public class GoldenRatioSearch extends AbstractSearch {
    private static final double phi = (Math.sqrt(5) - 1) / 2;

    public GoldenRatioSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
    }

    @Override
    public double searchMinimum() {
        double left = leftBorder;
        double right = rightBorder;
        double leftMid = leftBorder + (1 - phi) * (rightBorder - leftBorder);
        double rightMid = leftBorder + phi * (rightBorder - leftBorder);
        double f1 = function.run(leftMid);
        double f2 = function.run(rightMid);
        while (right - left > 2 * epsilon) {
            if (f1 <= f2) {
                right = rightMid;
                rightMid = leftMid;
                f2 = f1;
                leftMid = right - phi * (right - left);
                f1 = function.run(leftMid);
            } else {
                left = leftMid;
                leftMid = rightMid;
                f1 = f2;
                rightMid = left + phi * (right - left);
                f2 = function.run(rightMid);
            }
        }
        return (left + right) / 2;
    }
}
