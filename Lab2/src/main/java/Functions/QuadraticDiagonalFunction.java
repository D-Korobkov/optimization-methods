package Functions;

import SaZhaK.MatrixUtil;
import interfaces.Function;

/**
 * CLass for economic representation of quadratic function from R^n by diagonal matrix.
 */
public class QuadraticDiagonalFunction implements Function {
    /**
     * Matrix of square terms. B_{ii} = A_{i} = 2 * x{i}^2
     */
    final double[] A;
    /**
     * Vector of one-dimensional terms
     */
    final double[] B;
    /**
     * Constant of function
     */
    final double C;
    /**
     * Dimention of matrix
     */
    final int dimention;

    /**
     * Standard constructor for the function given by diagonal matrix
     * @param a {@link #A}
     * @param b {@link #B}
     * @param c {@link #C}
     */
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
        double one = MatrixUtil.dotProduct(B, x);
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
