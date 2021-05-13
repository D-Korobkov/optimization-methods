import matrix.*;

public class GaussSolver {
    public static double[] gaussForward(ProfileMatrix matrix, double[] b) {
        for (int i = 0; i < matrix.size(); ++i) {
            for(int j = 0; j < i; ++j) {
                b[i] -= b[j] * matrix.getL(i, j);
            }
            b[i] /= matrix.getL(i, i);
        }
        return b;
    }

    public static double[] gaussBackward(ProfileMatrix matrix, double[] y) {
        for (int i = matrix.size() - 1; i >= 0; --i) {
            for (int j = matrix.size() - 1; j > i; --j) {
                y[i] -= y[j] * matrix.getU(i, j);
            }
            y[i] /= matrix.getU(i, i);
        }
        return y;
    }
}