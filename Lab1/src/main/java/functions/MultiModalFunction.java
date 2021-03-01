package functions;

import interfaces.MathFunction;

/**
 * Класс многомодальной функции
 */
public class MultiModalFunction implements MathFunction {

    /**
     * {@link MathFunction#run(double)}
     */
    @Override
    public double run(double x) {
        return x * Math.sin(x) - Math.cos(x);
    }
}
