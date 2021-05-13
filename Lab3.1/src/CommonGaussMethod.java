import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

public class CommonGaussMethod {
    private final double[][] matrix;
    private final double[] b;
    private final int[] rowPermutation;
    private final int[] columnPermutation;
    private final int n;
    private final double epsilon;

    // matrix * x = b
    public CommonGaussMethod(final double[][] matrix, final double[] b, final double epsilon) {
        this.matrix = matrix;
        this.b = b;
        this.epsilon = epsilon;
        n = matrix.length;
        rowPermutation = IntStream.range(0, n).toArray();
        columnPermutation = IntStream.range(0, n).toArray();
    }

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

            double element = Math.abs(matrix[rowPermutation[i]][columnPermutation[i]]);
            if (element <= epsilon * element) {
                continue; // считаем, что 0
            }

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


    public double[] solve() {
        diagonalized();

        double[] x = new double[n];
        for (int i = 0; i < n; ++i) {
            x[columnPermutation[i]] = b[rowPermutation[i]] / matrix[rowPermutation[i]][columnPermutation[i]];
        }

        return x;
    }
}
