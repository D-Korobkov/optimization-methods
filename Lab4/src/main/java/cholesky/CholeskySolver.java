package cholesky;

import SaZhaK.MatrixUtil;
import interfaces.Solver;

import java.util.Arrays;

public class CholeskySolver implements Solver {
    private final double epsilon;

    public CholeskySolver() {
        epsilon = 0.000001;
    }

    public CholeskySolver(final double epsilon) {
        this.epsilon = epsilon;
    }

    public double[][] decompose(final double[][] A, final int dimension) {
        double[][] L = Arrays.stream(A).map(line -> new double[line.length]).toArray(double[][]::new);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * L[j][k];
                }
                if (i == j) {
                    L[i][j] = Math.sqrt(A[i][i] - sum);
                } else {
                    L[i][j] = (1.0 / L[j][j] * (A[i][j] - sum));
                }
            }
        }

        return L;
    }


    private void gaussForward(final double[][] L, final double[] b) {
        for (int i = 0; i < L.length; ++i) {
            for (int j = 0; j < i; ++j) {
                b[i] -= b[j] * L[i][j];
            }
            b[i] /= L[i][i];
        }
    }

    private void gaussBackward(final double[][] transposeL, final double[] y) {
        for (int i = transposeL.length - 1; i >= 0; --i) {
            for (int j = transposeL.length - 1; j > i; --j) {
                y[i] -= y[j] * transposeL[i][j];
            }
            y[i] /= transposeL[i][i];
        }
    }

    @Override
    public double[] solve(final double[][] A, final double[] B) {
        double[][] L = decompose(A, A.length);

        double[][] transposeL = Arrays.stream(L).map(line -> Arrays.copyOf(line, line.length)).toArray(double[][]::new);
        MatrixUtil.transposeMatrix(transposeL);

        double[][] checkProduct = MatrixUtil.multiply(L, transposeL);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (Double.isNaN(checkProduct[i][j]) || Math.abs(A[i][j] - checkProduct[i][j]) > epsilon) {
                    return null;
                }
            }
        }

        double[] answer = Arrays.copyOf(B, B.length);

        gaussForward(L, answer);
        gaussBackward(transposeL, answer);

        return answer;
    }
}
