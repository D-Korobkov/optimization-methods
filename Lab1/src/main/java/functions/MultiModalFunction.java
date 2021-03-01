package functions;

import interfaces.MathFunction;

public class MultiModalFunction implements MathFunction {

    @Override
    public double run(double x) {
        return x * Math.sin(x) - Math.cos(x);
    }
}
