package Functions;

import SaZhaK.Matrix;
import SaZhaK.MatrixUtil;
import interfaces.Function;

public class QuadraticFunction implements Function {

    final double[][] A;
    final double[] B;
    final double C;

    public QuadraticFunction(double[][] a, double[] b, double c) {
        A = a;
        B = b;
        C = c;
    }


    @Override
    public double run(double[] x) {
        double[] a = MatrixUtil.multiply(A, x);
        double quad = MatrixUtil.dot_product(x, a) / 2;
        double one = MatrixUtil.dot_product(B, x);

        return quad - one + C;
    }

    @Override
    public double[] runGradient(double[] x) {
        return MatrixUtil.subtract(MatrixUtil.multiply(A, x), B);
    }

    @Override
    public double[] multiply(double[] x) {
        return SaZhaK.MatrixUtil.multiply(A, x);
    }
}
