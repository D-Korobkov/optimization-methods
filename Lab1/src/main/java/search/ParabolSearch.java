package search;

import interfaces.MathFunction;

public class ParabolSearch extends AbstractSearch {
    private double x1, x2, x3;
    private final double epsilon;

    public ParabolSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder);
        this.epsilon = epsilon;
        this.x1 = leftBorder;
        this.x3 = rightBorder;
        if (Double.isNaN(function.run(leftBorder)) || Double.isNaN(function.run(rightBorder))) {
            throw new IllegalArgumentException("Function must be defined in given points");
        }
        if (function.run(leftBorder) < function.run(rightBorder)) {
            if (function.run(leftBorder) > function.run(leftBorder + epsilon)) {
                x2 = leftBorder + epsilon;
            } else {
                x2 = leftBorder;
            }
        } else {
            if (function.run(rightBorder) > function.run(rightBorder - epsilon)) {
                x2 = rightBorder - epsilon;
            } else {
                x2 = rightBorder;
            }
        }
    }

    @Override
    public double searchMinimum() {
        if (x1 == x2 || x2 == x3) {
            return x2;
        }
        double prevX, nextX, fNextX, f1, f2, f3, a1, a2;
        nextX = x1;
        f1 = function.run(x1);
        f2 = function.run(x2);
        f3 = function.run(x3);
        do {
            prevX = nextX;
            a1 = (f2 - f1) / (x2 - x1);
            a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);
            nextX = (x1 + x2 - a1 / a2) / 2;
            fNextX = function.run(nextX);
            if (nextX < x2) {
                if (fNextX >= f2) {
                    x1 = nextX;
                    f1 = fNextX;
                } else {
                    x3 = x2;
                    f3 = f2;
                    x2 = nextX;
                    f2 = fNextX;
                }
            } else {
                if (fNextX <= f2) {
                    x1 = x2;
                    f1 = f2;
                    x2 = nextX;
                    f2 = fNextX;
                } else {
                    x3 = nextX;
                    f3 = fNextX;
                }
            }
        } while (Math.abs(prevX - nextX) > epsilon);
        return nextX;
    }


}
