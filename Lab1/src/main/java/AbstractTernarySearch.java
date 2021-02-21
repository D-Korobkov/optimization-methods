public  abstract class AbstractTernarySearch implements Search {
    protected final MathFunction function;
    protected final double leftBorder, rightBorder, epsilon;

    public AbstractTernarySearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        this.function = function;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.epsilon = epsilon;
    }

    protected double searchMinimum(final Strategy calculation) {
        double left = leftBorder;
        double right = rightBorder;
        while (right - left > 2 * epsilon) {
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
