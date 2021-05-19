import matrix.ProfileMatrix;


/**
 * Class for solving SoLE by Gauss with LU decomposition
 */
public class LuSolver {

    /**
     * Решение СЛАУ (Ах = b) с нижнетрегугольной матрицей методом Гаусса, прямого хода.
     * Ответ находится в векторе b
     *
     * @param matrix {@link ProfileMatrix} - левая часть уровнения, нижнетреугольная матрица.
     * @param b - правая часть уровнения.
     */
    private static void gaussForward(final ProfileMatrix matrix, final double[] b) {
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                b[i] -= b[j] * matrix.getL(i, j);
            }
            b[i] /= matrix.getL(i, i);
        }
    }

    /**
     * Решение СЛАУ (Ах = y) с верхнетреугольной матрицей методом Гаусса, обратного хода.
     * Ответ находится в векторе b
     *
     * @param matrix {@link ProfileMatrix} - левая часть уровнения, верхнетреугольная матрица матрица.
     * @param y - правая часть уровнения.
     */
    private static void gaussBackward(final ProfileMatrix matrix, final double[] y) {
        for (int i = matrix.size() - 1; i >= 0; --i) {
            for (int j = matrix.size() - 1; j > i; --j) {
                y[i] -= y[j] * matrix.getU(i, j);
            }
            y[i] /= matrix.getU(i, i);
        }
    }


    /**
     * Решение СЛАУ (Ах = y) через LU разложение + метод Гаусса.
     * Ответ находится в векторе b
     *
     * @param matrix {@link ProfileMatrix} - левая часть уровнения
     * @param b - правая часть уровнения.
     */
    public static void solve(final ProfileMatrix matrix, final double[] b) {
//        System.out.println("matrix");
//        matrix.showByGetters();
        matrix.changeToLU();
//        System.out.println("L");
//        matrix.showL();
//        System.out.println("U");
//        matrix.showU();
        gaussForward(matrix, b);
        gaussBackward(matrix, b);
    }
}