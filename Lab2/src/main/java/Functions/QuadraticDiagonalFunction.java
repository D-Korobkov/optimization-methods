package Functions;

import SaZhaK.MatrixUtil;
import interfaces.Function;

public class QuadraticDiagonalFunction implements Function {

    final double[] A;
    final double[] B;
    final double C;
    final int dimention;

    public QuadraticDiagonalFunction(double[] a, double[] b, double c) {
        A = a;
        B = b;
        C = c;
        dimention = a.length;
    }


    @Override
    public double run(double[] x) {
        double quad = 0;
        for (int i = 0; i < dimention; i++) {
            quad += A[i] * x[i] * x[i];
        }
        quad /= 2;
        double one = MatrixUtil.dot_product(B, x);
        return quad - one + C;
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] quad = new double[dimention];
        for (int i = 0; i < dimention; i++) {
            quad[i] += A[i] * x[i];
        }
        return MatrixUtil.subtract(quad, B);
    }

    @Override
    public double[] multiply(double[] x) {
        double[] res = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            res[i] = A[i] * x[i];
        }
        return res;
    }
}
