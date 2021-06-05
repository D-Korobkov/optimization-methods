package cholesky;

import SaZhaK.MatrixUtil;
import interfaces.Solver;

import java.util.Arrays;

/**
 * класс для решения СЛАУ методом Холецкого
 */
public class CholeskySolver implements Solver {

    /**
     * реализует разложение Холецкого
     * @param A исходная матрица
     * @param dimension размерность
     * @return нижнетреугольная матрица {@code L}, такая что A = L * L^T, если разложение возможно
     */
    public double[][] decompose(final double[][] A, final int dimension) {
        double[][] L = Arrays.stream(A).map(line -> new double[dimension]).toArray(double[][]::new);

        for (int j = 0; j < dimension; j++) {
            L[j][j] = A[j][j];
            for (int i = 0; i < j; i++) {
                L[j][j] -= L[j][i] * L[j][i];
            }
            L[j][j] = Math.sqrt(L[j][j]);

            for (int i = j + 1; i < dimension; i++) {
                L[i][j] = A[i][j];
                for (int k = 0; k < j; k++) {
                    L[i][j] -= L[i][k] * L[j][k];
                }
                L[i][j] /= L[j][j];
            }
        }

        return L;
    }


    /**
     * прямой ход гаусса
     * @param L нижнетреугольная матрица
     * @param b результирующий вектор
     */
    private void gaussForward(final double[][] L, final double[] b) {
        for (int i = 0; i < L.length; ++i) {
            for (int j = 0; j < i; ++j) {
                b[i] -= b[j] * L[i][j];
            }
            b[i] /= L[i][i];
        }
    }

    /**
     * обратный ход Гаусса
     * @param transposeL верхнетреуугольная матрица
     * @param y результирующий вектор
     */
    private void gaussBackward(final double[][] transposeL, final double[] y) {
        for (int i = transposeL.length - 1; i >= 0; --i) {
            for (int j = transposeL.length - 1; j > i; --j) {
                y[i] -= y[j] * transposeL[i][j];
            }
            y[i] /= transposeL[i][i];
        }
    }

    /**
     * {@inheritDoc}
     * @param A матрица коэффициентов
     * @param B результирующий вектор
     * @return {@code null}, если матрицу {@code A} невозможно разложить методом Холецкого; иначе - решение СЛАУ
     */
    @Override
    public double[] solve(final double[][] A, final double[] B, double epsilon) {
        //System.out.println("Origin0: " + Arrays.deepToString(A));
        double[][] L = decompose(A, A.length);

        double[][] transposeL = Arrays.stream(L).map(line -> Arrays.copyOf(line, line.length)).toArray(double[][]::new);
        MatrixUtil.transposeMatrix(transposeL);

        double[][] checkProduct = MatrixUtil.multiply(L, transposeL);

        //System.out.println("Prod: " + Arrays.deepToString(checkProduct));
        //System.out.println("Origin: " + Arrays.deepToString(A));
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
