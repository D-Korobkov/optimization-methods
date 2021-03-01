package search;

import interfaces.MathFunction;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSearch extends AbstractSearch {
    private List<Long> fibonacci;
    private final double lengthOfSection;
    private int iterations = 1;

    public FibonacciSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        this.lengthOfSection = rightBorder - leftBorder;
        countFibonacci();
    }


    private void countFibonacci() {
        fibonacci = new ArrayList<>();
        fibonacci.add(0L);
        fibonacci.add(1L);
        while (lengthOfSection / epsilon >= fibonacci.get(iterations)) {
            fibonacci.add(fibonacci.get(iterations) + fibonacci.get(iterations - 1));
            iterations++;
        }
    }


    @Override
    public double searchMinimum() {
        double left = leftBorder;
        double right = rightBorder;
        double leftMid = leftBorder + lengthOfSection * fibonacci.get(iterations - 2) / fibonacci.get(iterations);
        double rightMid = leftBorder + lengthOfSection * fibonacci.get(iterations - 1) / fibonacci.get(iterations);
        double f1 = function.run(leftMid);
        double f2 = function.run(rightMid);
        for (int idx = iterations - 2; idx >= 0; idx--) {
            if (f1 <= f2) {
                right = rightMid;
                rightMid = leftMid;
                f2 = f1;
                leftMid = left + lengthOfSection * fibonacci.get(idx) / fibonacci.get(iterations);
                f1 = function.run(leftMid);
            } else {
                left = leftMid;
                leftMid = rightMid;
                f1 = f2;
                rightMid = left + lengthOfSection * fibonacci.get(idx + 1) / fibonacci.get(iterations);
                f2 = function.run(rightMid);
            }
        }
        return (left + right) / 2;
    }
}
