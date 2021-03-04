package functions;

import interfaces.MathFunction;

/**
 * Класс изучаемой функции
 */
public class FuncForLab1 implements MathFunction {
    /**
     * {@link MathFunction#run(double)}
     */
    public double run(double x) {
        return 10 * x * Math.log(x) - x * x / 2;
    }
}
