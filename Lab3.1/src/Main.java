import GradientMethods.ConjugateGradientMethod;
import SaZhaK.MatrixUtil;
import generator.MatrixGenerator;
import matrix.LineColumnMatrix;
import matrix.ProfileMatrix;
import matrix.QuadraticFunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import java.util.stream.DoubleStream;

public class Main {

    private static void old() throws IOException {
        ProfileMatrix matrix = new ProfileMatrix("out/production/Lab3.1/resources/profile/symmetry");
        matrix.showByGetters();
        matrix.changeToLU();
        //double[] ans = GaussSolver.gaussBackward(matrix, GaussSolver.gaussForward(matrix, new double[]{1, 2, 3}));
        //System.out.println(Arrays.toString(ans));
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

    private static void checkGenerator() {
        int dimension = 5;
        double[][] matrix = MatrixGenerator.generateOrdinaryMatrix(dimension, 5);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        String path = "out/production/Lab3.1/checkGenerator/";
        MatrixGenerator.parseAndWrite(matrix, path);
        ProfileMatrix profileMatrix = new ProfileMatrix(path);
        profileMatrix.showByGetters();
    }

    private static void testHilbert(int number, int k) throws IOException {
        String path = "out/production/Lab3.1/hilbert/test" + number;
        Files.createDirectories(Path.of(path));

        final double[][] matrix = MatrixGenerator.generateHilbertMatrix(k);
        MatrixGenerator.parseAndWrite(matrix, path);

        final double[] b = MatrixUtil.multiply(matrix, DoubleStream.iterate(1.0, x -> x + 1.0).limit(k).toArray());
        final double[] b1 = b.clone();

        ProfileMatrix profileMatrix = new ProfileMatrix(path);
        System.out.println(Arrays.toString(LuSolver.solve(profileMatrix, b)));
        System.out.println(Arrays.toString(new CommonGaussMethod(matrix, b1, 0.0000001).solve()));
    }

    private static void ordinaryResearch() {
        String path = "out/production/Lab3.1/ordinaryResearch/";
        for (int size = 10; size <= 1000; size *= 10) {
            System.out.println("size: " + size);
            for (int k = 0; k < 5; k++) {
                System.out.println("k: " + k);
                MatrixGenerator.parseAndWrite(MatrixGenerator.generateOrdinaryMatrix(size, k), path);
                try (BufferedReader reader = Files.newBufferedReader(Path.of(path + File.separator + "b.txt"))) {
                    double[] b = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    ProfileMatrix profileMatrix = new ProfileMatrix(path);
                    double[] ans = LuSolver.solve(profileMatrix, b);
                    System.out.println(Arrays.toString(ans));
                    System.out.println();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        old();
//        testHilbert(1, 4);
//        testHilbert(2, 100);
//        testHilbert(3, 200);
//        checkGenerator();
        ordinaryResearch();
    }
}
