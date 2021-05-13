import GradientMethods.ConjugateGradientMethod;
import matrix.LineColumnMatrix;
import matrix.ProfileMatrix;
import matrix.QuadraticFunction;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    private static void old() throws IOException {
        ProfileMatrix matrix = new ProfileMatrix("out/production/Lab3.1/resources/profile/symmetry");
        matrix.toStringByGetters();
        matrix.changeToLU();
        double[] ans = GaussSolver.gaussBackward(matrix, GaussSolver.gaussForward(matrix, new double[]{1, 2, 3}));
        System.out.println(Arrays.toString(ans));
        System.out.println();
        LineColumnMatrix matrix2 = new LineColumnMatrix("out/production/Lab3.1/resources/line-column/symmetry");
        System.out.println(matrix2);
        ConjugateGradientMethod method = new ConjugateGradientMethod(0.0000001);
        System.out.println(Arrays.toString(method.findMinimum(matrix2, new double[]{1, 2, 3})));
//        QuadraticFunction function = new QuadraticFunction(new double[][] {{1, 2, 0, 0, 0}, {2, 2, 0, 0, 0}, {0, 0, 3, 4, 0}, {0, 0, 100, 12, 0}, {0, 0, 0, 0, 999}}, new double[]{1, 2, 3, 4, 5}, 0);
        QuadraticFunction symmetryF = new QuadraticFunction(new double[][]{{-11, 6, -6}, {6, -6, 3}, {-6, 3, -6}}, new double[]{1, 2, 3}, 0);
        method = new ConjugateGradientMethod(0.0000001);
        System.out.println(Arrays.toString(method.findMinimum(symmetryF, new double[]{1, 2, 3})));
//        System.out.println(Arrays.stream(new int[]{1, 2, 4, 54}).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) throws Exception {
        old();

    }
}
