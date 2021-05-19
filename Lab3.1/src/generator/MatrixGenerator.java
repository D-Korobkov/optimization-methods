package generator;

import SaZhaK.MatrixUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * класс для генерации матриц
 */
public class MatrixGenerator {
    public static final Random RANDOM = new Random();
    private static final String[] NAME_OF_FILES = {"au.txt", "al.txt", "ia.txt", "d.txt", "b.txt", "ja.txt"};

    /**
     * генерирует квадратную матрицу с симметричным профилем согласно правилам в пункте 2 лаборатной работы 3
     * @param dimension размерность матрицы
     * @param k число
     * @return сгенерированная матрица
     */
    public static double[][] generateOrdinaryMatrix(final int dimension, final int k) {
        final double[][] matrix = new double[dimension][dimension];
        final int[] profileOffset = new int[dimension];

        for (int i = 0; i < dimension; ++i) {
            profileOffset[i] = RANDOM.nextInt(i + 1);
        }

        for (int row = 0; row < dimension; ++row) {
            for (int col = profileOffset[row]; col < row; ++col) {
                matrix[row][col] = -RANDOM.nextInt(4) - 1;
            }
        }

        for (int col = 0; col < dimension; ++col) {
            for (int row = profileOffset[col]; row < col; ++row) {
                matrix[row][col] = -RANDOM.nextInt(4) - 1;
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

    /**
     * генерирует матрицу с диагональным преобладанием, описанную в пункте 5.2 лабораторной работы 3
     * @param dimension размерность матрицы
     * @param k число
     * @return сгенерированная матрица
     */
    public static double[][] generateDiagonalDominationMatrix(final int dimension, final int k) {
        final double[][] matrix = new double[dimension][dimension];
        final int[] profileOffset = new int[dimension];

        for (int i = 0; i < dimension; ++i) {
            profileOffset[i] = RANDOM.nextInt(i + 1);
        }

        for (int row = 0; row < dimension; ++row) {
            for (int col = profileOffset[row]; col < row; ++col) {
                int val = -RANDOM.nextInt(4) - 1;
                matrix[row][col] = val;
                matrix[col][row] = val;
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
                matrix[i][i] += 1;
            }
        }

        return matrix;
    }

    /**
     * генерирует матрицу Гильберта
     * @param dimension размерность матрицы
     * @return сгенерированная матрица Гильберта указанной размерности
     */
    public static double[][] generateHilbertMatrix(final int dimension) {
        final double[][] matrix = new double[dimension][dimension];
        for (int i = 1; i <= dimension; ++i) {
            for (int j = 1; j <= dimension; ++j) {
                matrix[i - 1][j - 1] = (double) 1 / (i + j - 1);
            }
        }


        return matrix;
    }

    /**
     * переводит матрицу с симметричным профилем в профильный формат и записывает соответствующие массивы в файлы:
     * <ul>
     *     <li><var>al.txt</var> - профиль строки</li>
     *     <li><var>au.txt</var> - профиль столбца</li>
     *     <li><var>b.txt</var> - результирующий вектор</li>
     *     <li><var>d.txt</var> - диагональ матриицы</li>
     *     <li><var>ia.txt</var> - смещение профилей для каждой из строк (столбца)</li>
     * </ul>
     * @param matrix матрица с симметричным профилем
     * @param path путь к директории, куда следует записать сгенерированные файлы
     * @throws IOException если во время записи произошла ошибка
     */
    public static void parseAndWrite(final double[][] matrix, final String... path) throws IOException {

        try {
            Files.createDirectories(Path.of(String.join(File.separator, path)));
        } catch (IOException e) {
            throw new IOException("Error in creating directories " + Path.of(String.join(File.separator, path) + ". " + e.getMessage()));
        }

        final int size = matrix.length;
        final double[] b = MatrixUtil.multiply(matrix, DoubleStream.iterate(1.0, x -> x + 1.0).limit(size).toArray());
        final double[] d = new double[size];
        final ArrayList<Double> al = new ArrayList<>();
        final ArrayList<Double> au = new ArrayList<>();
        final int[] ia = new int[size + 1];
        for (int i = 0; i < size; i++) {
            d[i] = matrix[i][i];
        }
        ia[0] = 0;
        ia[1] = 0;
        for (int i = 1; i < size; i++) {
            int ind = 0;
            while (ind < i && matrix[i][ind] == 0) {
                ind++;
            }
            while (ind < i) {
                al.add(matrix[i][ind]);
                au.add(matrix[ind][i]);
                ind++;
            }
            ia[i + 1] = al.size();
        }
        for (final String fileName : NAME_OF_FILES) {
            if (fileName.equals("ja.txt")) continue;
            try (final BufferedWriter out = Files.newBufferedWriter(Path.of(String.join(File.separator, path), fileName))) {
                switch (fileName) {
                    case "au.txt" -> out.write(au.stream().map(Object::toString).collect(Collectors.joining(" ")));
                    case "al.txt" -> out.write(al.stream().map(Object::toString).collect(Collectors.joining(" ")));
                    case "ia.txt" -> out.write(Arrays.stream(ia).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
                    case "d.txt" -> out.write(Arrays.stream(d).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
                    case "b.txt" -> out.write(Arrays.stream(b).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
                }
            }
        }
    }

    /**
     * переводит симметричную матрицу в разреженный строчно-столбцовый
     * формат и записывает соответствующие массивы в файлы:
     * <ul>
     *     <li><var>al.txt</var> - разреженная строка</li>
     *     <li><var>au.txt</var> - разреженный столбец</li>
     *     <li><var>b.txt</var> - результирующий вектор</li>
     *     <li><var>d.txt</var> - диагональ</li>
     *     <li><var>ia.txt</var> - смещение для каждой из строк (столбцов)</li>
     *     <li><var>ja.txt</var> - номера столбцов, которые хранятся в <var>al.txt</var> и <var>au.txt</var></li>
     * </ul>
     * @param matrix симметричная матрица
     * @param path путь к директории, куда следует записать сгенерированные файлы
     * @throws IOException если во время записи произошла ошибка
     */
    public static void parserAndWriterOnLineColumn(final double[][] matrix, final String... path) throws IOException {

        try {
            Files.createDirectories(Path.of(String.join(File.separator, path)));
        } catch (IOException e) {
            throw new IOException("Error in creating directories " + Path.of(String.join(File.separator, path) + ". " + e.getMessage()));
        }

        final int size = matrix.length;
        final double[] b = MatrixUtil.multiply(matrix, DoubleStream.iterate(1.0, x -> x + 1.0).limit(size).toArray());
        final double[] d = new double[size];
        final ArrayList<Double> al = new ArrayList<>();
        final ArrayList<Double> au = new ArrayList<>();
        final ArrayList<Integer> ja = new ArrayList<>();
        final int[] ia = new int[size + 1];
        for (int i = 0; i < size; i++) {
            d[i] = matrix[i][i];
        }
        ia[0] = 0;
        ia[1] = 0;
        for (int i = 1; i < size; i++) {
            int ind = 0;
            while (ind < i && matrix[i][ind] == 0) {
                ind++;
            }
            while (ind < i) {
                if (matrix[i][ind] != 0) {
                    al.add(matrix[i][ind]);
                    au.add(matrix[ind][i]);
                    ja.add(ind);
                }
                ind++;
            }
            ia[i + 1] = al.size();
        }
        for (final String fileName : NAME_OF_FILES) {
            try (final BufferedWriter out = Files.newBufferedWriter(Path.of(String.join(File.separator, path), fileName))) {
                switch (fileName) {
                    case "au.txt" -> out.write(au.stream().map(Object::toString).collect(Collectors.joining(" ")));
                    case "al.txt" -> out.write(al.stream().map(Object::toString).collect(Collectors.joining(" ")));
                    case "ia.txt" -> out.write(Arrays.stream(ia).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
                    case "d.txt" -> out.write(Arrays.stream(d).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
                    case "b.txt" -> out.write(Arrays.stream(b).mapToObj(Objects::toString).collect(Collectors.joining(" ")));
                    case "ja.txt" -> out.write(ja.stream().map(Object::toString).collect(Collectors.joining(" ")));
                }
            }
        }
    }

}