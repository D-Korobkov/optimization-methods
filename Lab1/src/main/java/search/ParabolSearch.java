package search;

import interfaces.MathFunction;

public class ParabolSearch {
    public double x1, x2, x3, eps;
    public MathFunction func;

    ParabolSearch(double left, double middle, double right, double eps, MathFunction func) {
        this.eps = eps;
        this.x1 = left;
        this.x3 = right;
        this.x2 = middle;
        this.func = func;
        if (func.run(x2) > func.run(x1) || func.run(x2) > func.run(x3)) {
            throw new IllegalArgumentException("You must give left, middle, right that f(middle) <= f(left) and f(middle) <= f(right)");
        }
    }

    public double search() { // a0 useless
        double prevX, nextX, fNextX, f1, f2, f3, a1, a2;
        nextX = x1;
        f1 = func.run(x1);
        f2 = func.run(x2);
        f3 = func.run(x3);
        do {
            prevX = nextX;
            a1 = (f2 - f1) / (x2 - x1);
            a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);
            nextX = (x1 + x2 - a1 / a2) / 2;
            fNextX = func.run(nextX);
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
        } while (Math.abs(prevX - nextX) > eps);
        return nextX;
    }
}
