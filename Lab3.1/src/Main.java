import GradientMethods.ConjugateGradientMethod;
import SaZhaK.MatrixUtil;
import generator.MatrixGenerator;
import matrix.LineColumnMatrix;
import matrix.ProfileMatrix;
import matrix.QuadraticFunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
        final double[] b1 = Arrays.copyOf(b, b.length);

        ProfileMatrix profileMatrix = new ProfileMatrix(path);
        System.out.println(Arrays.toString(LuSolver.solve(profileMatrix, b)));
        System.out.println(Arrays.toString(new CommonGaussMethod(matrix, b1).solve()));
    }

    private static void ordinaryResearch() throws IOException {
        String path = "out/production/Lab3.1/ordinaryResearch/";
        Files.createDirectories(Path.of(path));

        for (int size = 10; size <= 10; size *= 10) {
            System.out.println("size: " + size);
            int k = 4;
//            for (int k = 0; k < 5; k++) {
                System.out.println("k: " + k);
                double[][] matrix = MatrixGenerator.generateOrdinaryMatrix(size, k);
                MatrixGenerator.parseAndWrite(matrix, path);
                try (BufferedReader reader = Files.newBufferedReader(Path.of(path + File.separator + "b.txt"))) {
                    double[] b1 = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    double[] b2 = Arrays.copyOf(b1, b1.length);
                    ProfileMatrix profileMatrix = new ProfileMatrix(path);
                    profileMatrix.showByGetters();
//                    double[] ans1 = new CommonGaussMethod(matrix, b1).solve();
//                    System.out.println(Arrays.toString(ans1));

                    double[] ans2 = LuSolver.solve(profileMatrix, b2);
                    System.out.println(Arrays.toString(ans2));

                    double[] x = IntStream.range(1, b1.length + 1).mapToDouble((i) -> ((double) i)).toArray();
                    double[] missed = MatrixUtil.subtract(ans2, x);
                    System.out.println(MatrixUtil.norm(missed));
                    System.out.println(MatrixUtil.norm(missed) / MatrixUtil.norm(x));
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
        }
    }

    private static void findTrouble() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double[][] matrix  = new double[10][];
        for (int i = 0; i < 10; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        }
        reader.close();
        String path = "out/production/Lab3.1/findMissed/";
        MatrixGenerator.parseAndWrite(matrix, path);
        ProfileMatrix profileMatrix = new ProfileMatrix(path);
        double[] x = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] ans = LuSolver.solve(profileMatrix, MatrixUtil.multiply(matrix, x));
        System.out.println(Arrays.toString(ans));
    }

    private static void testLineColumn() {
        int dimension = 5;
        double[][] matrix = MatrixGenerator.generateOrdinaryMatrix(dimension, 5);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        String path = "out/production/Lab3.1/testLineColumn/";
        MatrixGenerator.parserAndWriterOnLineColumn(matrix, path);
        LineColumnMatrix lineColumnMatrix = new LineColumnMatrix(path);
        System.out.println(lineColumnMatrix);
    }

    public static void main(String[] args) throws IOException {
//        old();
        //checkGenerator();
//        testHilbert(1, 4);
//        testHilbert(2, 6);
//        testHilbert(3, 8);
//        testHilbert(4, 10);
//        testHilbert(5, 100);

//        ordinaryResearch();
//        findTrouble();
//        testLineColumn();
    }

}
