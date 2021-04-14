package interfaces;

public interface Function {
    double run(double[] x);

    double[] runGradient(double[] x);

    double[] multiply(double[] x);
}
