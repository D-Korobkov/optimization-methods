package generator;

import java.util.Random;

public class MatrixGenerator {
    public static Random random;

    public static double[][] generateOrdinaryMatrix(int dimension, int k) {
        double[][] matrix = new double[dimension][dimension];
        int[] profileOffset = new int[dimension];

        for (int i = 0; i < dimension; ++i) {
            profileOffset[i] = random.nextInt(i + 1);
        }

        for (int row = 0; row < dimension; ++row) {
            for (int col = profileOffset[row]; col < row; ++col) {
                matrix[row][col] = -random.nextInt(4) - 1;
            }
        }

        for (int col = 0; col < dimension; ++col) {
            for (int row = profileOffset[col]; row < col; ++row) {
                matrix[row][col] = -random.nextInt(4) - 1;
            }
        }

        for (int i = 0; i < dimension; ++i) {
            double sum = 0;
            for (int j = 0; j < dimension; ++j) {
                if (j != i) {
                    sum -= matrix[i][j];
                }
            }
            matrix[i][i] = sum;
            if (i == 0) {
                matrix[i][i] += Math.pow(0.1, k);
            }
        }

        return matrix;
    }

    public static double[][] generateHilbertMatrix(int dimension) {
        double[][] matrix = new double[dimension][dimension];
        for (int i = 1; i <= dimension; ++i) {
            for (int j = 1; j <= dimension; ++j) {
                matrix[i - 1][j - 1] = (double) 1 / (i + j - 1);
            }
        }

        return matrix;
    }

    public static void parse() {

    }
}
