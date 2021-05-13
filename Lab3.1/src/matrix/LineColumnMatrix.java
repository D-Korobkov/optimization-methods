package matrix;

import SaZhaK.MatrixUtil;
import interfaces.Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class LineColumnMatrix implements Function {

    private double[] al, au, d, b;
    private int[] ia, ja;

    private static final String[] NAME_OF_FILES = {"au.txt", "al.txt", "ia.txt", "d.txt", "ja.txt"};

    public LineColumnMatrix(String pathOfMatrixAndVector, double[] b) {
        this.b = b;
        for (String fileName : NAME_OF_FILES) {
            try (BufferedReader reader = Files.newBufferedReader(Path.of(pathOfMatrixAndVector + File.separator + fileName))){
                switch (fileName) {
                    case "au.txt" -> au = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "al.txt" -> al = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "ia.txt" -> ia = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    case "ja.txt" -> ja = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    case "d.txt" -> d = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int size() {
        return d.length;
    }

    public double getIJ(int i, int j) {
        if (i == j) {
            return d[i];
        }
        if (i > j) {
            return getLowTriangle(i, j);
        } else {
            return getHighTriangle(i, j);
        }
    }

    private double getLowTriangle(int i, int j) {
        return getFromTriangle(i, j, true);
    }

    private double getHighTriangle(int i, int j) {
        return getFromTriangle(j, i, false);
    }

    private double getFromTriangle(int line, int indInLine, boolean low) {
        int realInJA = ia[line + 1] - ia[line];
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
    public double run(double[] x) {
        double[] a = multiply(x);
        double quad = MatrixUtil.dotProduct(x, a) / 2;
        double one = MatrixUtil.dotProduct(b, x);

        return quad - one;
    }

    @Override
    public double[] runGradient(double[] x) {
        return MatrixUtil.subtract(multiply(x), b);
    }

    @Override
    public double[] multiply(double[] x) {
        double[] ans = new double[size()];
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                ans[i] += getIJ(i, j) * x[j];
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LineColumnMatrix{");
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
