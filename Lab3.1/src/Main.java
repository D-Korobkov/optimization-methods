import matrix.Matrix;

import java.util.Arrays;

public class Main {

    private static double[] gaussForward(Matrix matrix, double[] b) {
        for (int i = 0; i < matrix.size(); ++i) {
            for(int j = 0; j < i; ++j) {
                b[i] -= b[j] * matrix.getL(i, j);
            }
            b[i] /= matrix.getL(i, i);
        }
        return b;
    }

    private static double[] gaussBackward(Matrix matrix, double[] y) {
        for (int i = matrix.size() - 1; i >= 0; --i) {
            for (int j = matrix.size() - 1; j > i; --j) {
                y[i] -= y[j] * matrix.getU(i, j);
            }
            y[i] /= matrix.getU(i, i);
        }
        return y;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("out/production/Lab3.1/resources");
        matrix.toStringByGetters();
        System.out.println();
        matrix.changeToLU();
        matrix.toStringByGetters();
        System.out.println();
        matrix.showL();
        System.out.println();
        matrix.showU();
//        for (int i = 0; i < matrix.size(); i++) {
//            for (int j = 0; j < matrix.size(); j++) {
//                matrix.setL(i, j, 1);
//                matrix.setU(i, j, 1);
//            }
//        }
        System.out.println(Arrays.toString(matrix.al));
        System.out.println(Arrays.toString(matrix.au));
        System.out.println(Arrays.toString(matrix.d));
        double[] ans = gaussBackward(matrix, gaussForward(matrix, new double[]{1, 2, 3, 4, 5}));
        System.out.println(Arrays.toString(ans));
    }
}
