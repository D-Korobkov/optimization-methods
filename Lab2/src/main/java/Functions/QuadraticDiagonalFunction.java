package Functions;

import interfaces.Function;

public class QuadraticDiagonalFunction implements Function {

    final double[] A;
    final double[] B;
    final double C;

    public QuadraticDiagonalFunction(double[] a, double[] b, double c) {
        A = a;
        B = b;
        C = c;
    }


    @Override
    public double run(double[] x) {
        return 0;
    }

    @Override
    public double[] runGradient(double[] x) {
        return new double[0];
    }
}
