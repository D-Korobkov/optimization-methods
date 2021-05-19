import GradientMethods.ConjugateGradientMethod;
import SaZhaK.MatrixUtil;
import generator.MatrixGenerator;
import interfaces.Function;
import interfaces.Method;
import matrix.LineColumnMatrix;
import matrix.ProfileMatrix;
import matrix.QuadraticFunction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
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

    private static void ordinaryResearch() throws IOException {
        String path = "out/production/Lab3.1/ordinaryResearch/";
        Files.createDirectories(Path.of(path));
        int size = 10;
//        for (int size = 10; size <= 10; size *= 10) {
        System.out.println("size: " + size);
        for (int k = 6; k <= 6; k++) {
            System.out.println("k: " + k);
            double[][] matrix = MatrixGenerator.generateOrdinaryMatrix(size, k);
            MatrixGenerator.parseAndWrite(matrix, path);
            try (BufferedReader reader = Files.newBufferedReader(Path.of(path + File.separator + "b.txt"))) {
                double[] b1 = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
//                    double[] b2 = Arrays.copyOf(b1, b1.length);
//                    ProfileMatrix profileMatrix = new ProfileMatrix(path);
//                    profileMatrix.showByGetters();
                double[] ans1 = new CommonGaussMethod(matrix, b1).solve();
                System.out.println(Arrays.toString(ans1));

//                    double[] ans2 = LuSolver.solve(profileMatrix, b2);
//                    System.out.println(Arrays.toString(ans2));

                double[] x = IntStream.range(1, b1.length + 1).mapToDouble((i) -> ((double) i)).toArray();
                double[] missed = MatrixUtil.subtract(ans1, x);
                System.out.println(MatrixUtil.norm(missed) + " " + MatrixUtil.norm(missed) / MatrixUtil.norm(x));
//                    System.out.println(MatrixUtil.norm(missed) / MatrixUtil.norm(x));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            }
        }
    }

//    private static void testHilbert(int number, int k) throws IOException {
//        String path = "out/production/Lab3.1/hilbert/test" + number;
//        Files.createDirectories(Path.of(path));
//
//        final double[][] matrix = MatrixGenerator.generateHilbertMatrix(k);
//        MatrixGenerator.parseAndWrite(matrix, path);
//
//        final double[] b = MatrixUtil.multiply(matrix, DoubleStream.iterate(1.0, x -> x + 1.0).limit(k).toArray());
//        final double[] b1 = Arrays.copyOf(b, b.length);
//
//        ProfileMatrix profileMatrix = new ProfileMatrix(path);
//        System.out.println(Arrays.toString(LuSolver.solve(profileMatrix, b)));
//        System.out.println(Arrays.toString(new CommonGaussMethod(matrix, b1).solve()));
//    }

//    private static void findTrouble() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        double[][] matrix = new double[10][];
//        for (int i = 0; i < 10; i++) {
//            matrix[i] = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
//        }
//        reader.close();
//        String path = "out/production/Lab3.1/findMissed/";
//        MatrixGenerator.parseAndWrite(matrix, path);
//        ProfileMatrix profileMatrix = new ProfileMatrix(path);
//        double[] x = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        double[] ans = LuSolver.solve(profileMatrix, MatrixUtil.multiply(matrix, x));
//        System.out.println(Arrays.toString(ans));
//    }

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

    private static void testRefactorLU() throws IOException {
        String path = "out/production/Lab3.1/testRefactorLU/";
        Files.createDirectories(Path.of(path));
        double[][] matrix = MatrixGenerator.generateOrdinaryMatrix(5, 5);
        System.out.println("Matrix");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        MatrixGenerator.parseAndWrite(matrix, path);
        ProfileMatrix profileMatrix = new ProfileMatrix(path);
        System.out.println("Getters");
        profileMatrix.showByGetters();
        profileMatrix.changeToLU();
        System.out.println("L");
        profileMatrix.showL();
        System.out.println("U");
        profileMatrix.showU();
    }

    private static void checkArgs(String[] args) {
        if (args == null || Arrays.stream(args).anyMatch(Objects::isNull) || args.length == 0) {
            throw new IllegalArgumentException("Args shouldn't be null and have length > 0");
        }
        Path.of(args[0]);
        if (args.length > 1) {
            String message = "If you give more than 1 args, second must be hilbert $dimension | ordinary $dimension $ordinary";
            args[1] = args[1].toLowerCase();
            if (args[1].equals("hilbert") && args.length == 3) {
                try {
                    Integer.parseInt(args[2]);
                } catch (NumberFormatException ignored) {
                    throw new IllegalArgumentException(message);
                }
            } else if (args[1].equals("ordinary") && args.length == 4) {
                try {
                    Integer.parseInt(args[2]);
                    Integer.parseInt(args[3]);
                } catch (NumberFormatException ignored) {
                    throw new IllegalArgumentException(message);
                }
            } else {
                throw new IllegalArgumentException(message);
            }
        }
    }

    private static double[] readB(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path, "b.txt"))) {
            return Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error in getting vector b from 'Ax = b' " + e.getMessage());
        }
    }

    private static void solve(String[] args) {
        //args: path [hilbert $dimension | ordinary $dimension $ordinary]
        checkArgs(args);
        if (args.length > 1) {
            try {
                Files.createDirectories(Path.of(args[0]));
            } catch (IOException e) {
                System.err.println("Error in creating directories " + args[0] + ". " + e.getMessage());
            }
            if (args[1].equals("hilbert")) {
                MatrixGenerator.parseAndWrite(MatrixGenerator.generateHilbertMatrix(Integer.parseInt(args[2])), args[0]);
            } else {
                MatrixGenerator.parseAndWrite(MatrixGenerator.generateOrdinaryMatrix(Integer.parseInt(args[2]), Integer.parseInt(args[3])), args[0]);
            }
        }
        final double[] b = readB(args[0]);
        ProfileMatrix matrix = new ProfileMatrix(args[0]);
        matrix.showByGetters();
        LuSolver.solve(matrix, b);
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(args[0], "ans.txt"))) {
            writer.write(Arrays.stream(b).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            System.err.println("Error in writing answer. " + e.getMessage());
        }
    }

    private static void solveBonus(String matrixType) throws IOException{
        for(int i = 10; i <= 1000; i*=10){
            System.out.println("Size = " + i);
            String path = "src/resources/line-column/" + matrixType + "/" + i;
            LineColumnMatrix matrix = new LineColumnMatrix(path);
            Method m = new ConjugateGradientMethod(0.000001);
            double[] xSolved = m.findMinimum(matrix, new double[matrix.size()]);
            double[] xReal = DoubleStream.iterate(1.0, x -> x + 1.0).limit(i).toArray();

            double miss = MatrixUtil.norm(MatrixUtil.subtract(xReal, xSolved));

            System.out.println(miss);
            System.out.println(miss/MatrixUtil.norm(xReal));

            double[] f = matrix.getB();
            double missF = MatrixUtil.norm(MatrixUtil.subtract(f, matrix.multiply(xSolved)));

            System.out.println((miss/MatrixUtil.norm(xReal))/(missF/MatrixUtil.norm(f)));

        }
    }

    public static void writeLineColumnMatrices(String matrixType){
        for(int i = 10; i <= 1000; i*=10){
            String path = "src/resources/line-column/" + matrixType+ "/" + i;
            double[][] matrix = MatrixGenerator.generateDiagonalDominationMatrix(i, 4);
            MatrixGenerator.parserAndWriterOnLineColumn(matrix, path);
        }
    }

    public static void main(String[] args) throws IOException {
        solve(args);
    }
}
