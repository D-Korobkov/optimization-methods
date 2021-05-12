package java;

import java.matrix.Matrix;

public class Main {

    private static double[] gaussForward(Matrix matrix, double[] b) {
        for (int i = 0; i < matrix.size(); ++i) {
            for(int j = 0; j < i; ++j) {
                b[i] -= matrix.getL(i, j);
            }
            b[i] /= matrix.getL(i, i);
        }
        return b;
    }

    private static double[] gaussBackward(Matrix matrix, double[] y) {
        for (int i = matrix.size() - 1; i >= 0; --i) {
            for (int j = matrix.size() - 1; j > i; --j) {
                y[i] -= matrix.getU(i, j);
            }
            y[i] /= matrix.getU(i, i);
        }
        return y;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
