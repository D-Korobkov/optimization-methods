import interfaces.MathFunction;

public class FuncForLab1 implements MathFunction {
    public double run(double x) {
        return 10 * x * Math.log(x) - x * x / 2;
    }
}
