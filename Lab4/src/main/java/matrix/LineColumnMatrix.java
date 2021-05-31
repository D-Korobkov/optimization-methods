package matrix;

import SaZhaK.MatrixUtil;
import interfaces.Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Класс, реализующий хранение матриц в разреженно строчно-столбцовом формате
 */
public class LineColumnMatrix implements Function {

    /**
     * Массив al - строки нижнего треуольника без нулей
     */
    private double[] al;
    /**
     * Массив au - столбцы верхнего треугольника без нулей
     */
    private double[] au;
    /**
     * Массив d - диагональ матрицы
     */
    private double[] d;
    /**
     * Массив b - правая часть уравнения
     */
    private double[] b;
    /**
     * Массив ia - индексный массив для al, au
     */
    private int[] ia;
    /**
     * Массив ja - массив номеров столбцов (строк) в al (au)
     */
    private int[] ja;

    /**
     * Набор всех файлов для чтения и заполнения класса.
     */
    private static final String[] NAME_OF_FILES = {"au.txt", "al.txt", "ia.txt", "d.txt", "ja.txt", "b.txt"};

    /**
     * Стандартный конструктор.
     * @param pathOfMatrixAndVector - путь, по которому лежат файлы из {@link #NAME_OF_FILES}
     */
    public LineColumnMatrix(final String pathOfMatrixAndVector) {
        for (final String fileName : NAME_OF_FILES) {
            try (final BufferedReader reader = Files.newBufferedReader(Path.of(pathOfMatrixAndVector + File.separator + fileName))) {
                switch (fileName) {
                    case "au.txt" -> au = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "al.txt" -> al = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "ia.txt" -> ia = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    case "ja.txt" -> ja = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    case "d.txt" -> d = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "b.txt" -> b = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Размер матрицы
     * @return рамзер матрицы
     */
    public int size() {
        return d.length;
    }

    /**
     * Доступ к элементам матрицы по индексам строки и столбца.
     * @param i - строка матрицы
     * @param j - столбец матрицы
     * @return элемент из матрицы. Аналогично плотной матрице А - A[i][j].
     */
    public double getIJ(final int i, final int j) {
        if (i == j) {
            return d[i];
        }
        if (i > j) {
            return getLowTriangle(i, j);
        } else {
            return getHighTriangle(i, j);
        }
    }

    /**
     * Возвращает элемент из нижнего треугольника без диагонали
     * @param i - строка
     * @param j - столбец
     * @return значение из нижнего треугольника
     */
    private double getLowTriangle(final int i, final int j) {
        return getFromTriangle(i, j, true);
    }

    /**
     * Возвращает элемент из верхнего треугольника без диагонали
     * @param i - строка
     * @param j - столбец
     * @return значение из верхнего треугольника
     */
    private double getHighTriangle(final int i, final int j) {
        return getFromTriangle(j, i, false);
    }

    /**
     * Абстракция для получения элемента из треугольника вне диагонали.
     * @param line - строка треугольника
     * @param indInLine - столбец треугольника, менший чем строка
     * @param low - флаг, что берут из из нижнего треугольника
     * @return значение из треугольной матрицы
     */
    private double getFromTriangle(final int line, final int indInLine, final boolean low) {
        if (line <= indInLine) {
            throw new IllegalArgumentException("Not Triangle indexes line = " + (low ? line : indInLine) + "; column = " + (!low ? line : indInLine) + ";");
        }
        final int realInJA = ia[line + 1] - ia[line];
        int offset = 0;
        for (; offset < realInJA; offset++) {
            if (ja[ia[line] + offset] == indInLine) {
                break;
            } else if (ja[ia[line] + offset] > indInLine) {
                return 0;
            }
        }
        if (offset == realInJA) {
            return 0;
        }
        if (low) {
            return al[ia[line] + offset];
        } else {
            return au[ia[line] + offset];
        }
    }

    @Override
    public double run(final double[] x) {
        final double[] a = multiply(x);
        final double quad = MatrixUtil.dotProduct(x, a) / 2;
        final double one = MatrixUtil.dotProduct(b, x);

        return quad - one;
    }

    @Override
    public double[] runGradient(final double[] x) {
        return MatrixUtil.subtract(multiply(x), b);
    }

    @Override
    public double[] multiply(final double[] x) {
        final double[] ans = new double[size()];
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                ans[i] += getIJ(i, j) * x[j];
            }
        }
        return ans;
    }

    @Override
    public double[][] runHessian(double[] x) {
        return null;
    }

    /**
     * Получение вектора правой части.
     * @return вектор b
     */
    public double[] getB() {
        return b;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LineColumnMatrix{");
        sb.append(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                sb.append(getIJ(i, j)).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        sb.append("}");
        return sb.toString();
    }
}
