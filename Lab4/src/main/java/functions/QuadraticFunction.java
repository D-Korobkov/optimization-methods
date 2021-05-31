package functions;

import SaZhaK.MatrixUtil;
import interfaces.Function;

/**
 * Class for representation of quadratic function of R^n
 */
public class QuadraticFunction implements Function {
    /**
     * Matrix of square terms. A_{ij}=A_{ji}, A_{ii} = 2 * x{i}^2
     */
    final double[][] A;
    /**
     * Vector of one-dimensional terms
     */
    final double[] B;
    /**
     * Constant of function
     */
    final double C;

    /**
     * Standard constructor of matrix representation of function
     * @param a {@link #A}
     * @param b {@link #B}
     * @param c {@link #C}
     */
    public QuadraticFunction(double[][] a, double[] b, double c) {
        A = a;
        B = b;
        C = c;
    }

    @Override
    public double run(double[] x) {
        double[] a = MatrixUtil.multiply(A, x);
        double quad = MatrixUtil.dotProduct(x, a) / 2;
        double one = MatrixUtil.dotProduct(B, x);

        return quad - one + C;
    }

    @Override
    public double[] runGradient(double[] x) {
        return MatrixUtil.subtract(MatrixUtil.multiply(A, x), B);
    }

    @Override
    public double[] multiply(double[] x) {
        return MatrixUtil.multiply(A, x);
    }

    @Override
    public double[][] runHessian(double[] x) {
        return A;
    }
}
