import java.util.stream.IntStream;

/**
 * класс для решения СЛАУ методом Гаусса с выбором опорного элемента по всей квадратной матрице
 */
public class CommonGaussMethod {
    /** поле для хранения плотной матрицы */
    private final double[][] matrix;
    /** поле для хранения результирующего вектора */
    private final double[] b;
    /** поле для хранения текущей перестановки столбцов матрицы */
    private final int[] columnPermutation;
    /** поле для хранения текущей перестановки строк матрицы */
    private final int[] rowPermutation;
    /** поле для хранения размерности матрицы */
    private final int n;

    /**
     * создаёт экземпляр
     * @param matrix квадратная матрица
     * @param b результирующий вектор
     */
    public CommonGaussMethod(final double[][] matrix, final double[] b) {
        this.matrix = matrix;
        this.b = b;
        n = matrix.length;
        rowPermutation = IntStream.range(0, n).toArray();
        columnPermutation = IntStream.range(0, n).toArray();
    }

    /**
     * фунция приводит исходную матрицу к диагональному виду,
     * в качестве опорного элемента выбирается максимальный элемент матрицы на текущем шаге
     */
    private void diagonalized() {
        for (int i = 0; i < n; i++) {
            double maxElement = Math.abs(matrix[rowPermutation[i]][columnPermutation[i]]);
            int row = i;
            int col = i;

            for (int j = i; j < n; ++j) {
                for (int k = i; k < n; ++k) {
                    if (j == i && k == i) {
                        continue;
                    }
                    double elem = Math.abs(matrix[rowPermutation[j]][columnPermutation[k]]);
                    if (elem >= maxElement) {
                        maxElement = elem;
                        row = j;
                        col = k;
                    }
                }
            }

            int tmp = rowPermutation[i];
            rowPermutation[i] = rowPermutation[row];
            rowPermutation[row] = tmp;
            tmp = columnPermutation[i];
            columnPermutation[i] = columnPermutation[col];
            columnPermutation[col] = tmp;

            for (int j = 0; j < n; ++j) {
                if (j == i) continue;
                double delta = -(matrix[rowPermutation[j]][columnPermutation[i]] / matrix[rowPermutation[i]][columnPermutation[i]]);

                matrix[rowPermutation[j]][columnPermutation[i]] = 0.0;

                for (int k = i + 1; k < n; ++k) {
                    matrix[rowPermutation[j]][columnPermutation[k]] += delta * matrix[rowPermutation[i]][columnPermutation[k]];
                }

                b[rowPermutation[j]] += delta * b[rowPermutation[i]];
            }
        }
    }


    /**
     * функция для инициации решения СЛАУ
     * @return вектор <var>{x_1, x_2, ..., x_n}</var> - решение СЛАУ
     */
    public double[] solve() {
        diagonalized();

        double[] x = new double[n];
        for (int i = 0; i < n; ++i) {
            x[columnPermutation[i]] = b[rowPermutation[i]] / matrix[rowPermutation[i]][columnPermutation[i]];
        }

        return x;
    }
}
