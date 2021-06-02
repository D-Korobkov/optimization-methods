import interfaces.Function;

public class FunctionTestHandler implements Function{

    protected final String stringRepresentation;
    protected final String name;

    protected final Function function;
    protected final double[] x0;
    protected final double[] answer;

    public FunctionTestHandler(Function function, double[] answer, double[] x0, String stringRepresentation, String name){
        this.function = function;
        this.answer = answer;
        this.x0 = x0;
        this.stringRepresentation = stringRepresentation;
        this.name = name;

    }

    public FunctionTestHandler(Function function, double[] answer, double[] x0, String name){
        this.function = function;
        this.answer = answer;
        this.x0 = x0;
        this.stringRepresentation = null;
        this.name = name;
    }

    public double[] getAnswer() {
        return answer;
    }

    public String getName() {
        return name;
    }

    public double[] getX0() {
        return x0;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public double run(double[] x) {
        return function.run(x);
    }

    @Override
    public double[] runGradient(double[] x) {
        return function.runGradient(x);
    }

    @Override
    public double[] multiply(double[] x) {
        return function.multiply(x);
    }

    @Override
    public double[][] runHessian(double[] x) {
        return function.runHessian(x);
    }
}
