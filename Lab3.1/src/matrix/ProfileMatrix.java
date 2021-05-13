package matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ProfileMatrix {

    public double[] al, au, d;
    public int[] ia;

    private static final String[] NAME_OF_FILES = {"au.txt", "al.txt", "ia.txt", "d.txt"};

    public ProfileMatrix(String pathOfMatrix) {
        for (String fileName : NAME_OF_FILES) {
            try (BufferedReader reader = Files.newBufferedReader(Path.of(pathOfMatrix + File.separator + fileName))){
                switch (fileName) {
                    case "au.txt" -> au = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "al.txt" -> al = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "ia.txt" -> ia = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    case "d.txt" -> d = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public double getIJ(int i, int j) {
        if (i == j) {
            return d[i];
        } else if (i < j) {
            return getU(i, j);
        } else {
            return getL(i, j);
        }
    }

    public int size() {
        return d.length;
    }

    private double profileGet(int i, int j, double[] a, double diag) {
        if (i == j) {
            return diag;
        }
        if (j > i) {
            return 0;
        }
        int realCount = ia[i + 1] - ia[i];
        int imagineCount = i - realCount;
        if (j < imagineCount) {
            return 0;
        } else {
            return a[ia[i] + j - imagineCount];
        }
    }

    public double getL(int i, int j) {
        return profileGet(i, j, al, d[i]);
    }

    public double getU(int i, int j) {
        return profileGet(j, i, au, 1);
    }

    public void setL(int i, int j, double value) {
        if (i == j) {
            d[i] = value;
            return;
        }
        if (i < j) {
            return;
        }
        int realCount = ia[i + 1] - ia[i];
        int imagineCount = i - realCount;
        if (j >= imagineCount) {
            al[ia[i] + j - imagineCount] = value;
        }
    }

    public void setU(int i, int j, double value) {
        if (i >= j) {
            return;
        }
        int realCount = ia[j + 1] - ia[j];
        int imagineCount = j - realCount;
        if (i >= imagineCount) {
            au[ia[j] + i - imagineCount] = value;
        }
    }

    public void changeToLU() {
        int n = size();//TODO change to size
        setL(0, 0, getIJ(0, 0));//TODO chek getIJ



        for(int i = 1; i < n; i++){

            //setting L_ij

            for(int j = 0; j < i; j++){

                double substract = 0;
                for(int k = 0; k < j; k++){
                    substract += getL(i, k) * getU(k, j);
                }
                setL(i, j, getIJ(i, j) - substract);
            }

            //setting U_ji

            for(int j = 0; j < i; j++){
                double substract = 0;
                for(int k = 0; k < j; k++){
                    substract += getL(j, k) * getU(k, i);
                }
                setU(j, i, (getIJ(j, i) - substract) / getL(j, j));

            }

            //setting L_ii

            double substract = 0;

            for(int k = 0; k < i; k++){
                substract += getL(i, k) * getU(k, i);
            }
            setL(i, i, getIJ(i, i) - substract);

            //setting U_ii
            setU(i, i, 1);

        }
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "al=" + Arrays.toString(al) +
                ", au=" + Arrays.toString(au) +
                ", d=" + Arrays.toString(d) +
                ", ia=" + Arrays.toString(ia) +
                '}';
    }

    public void showByGetters() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                System.out.print(getIJ(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void showL() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                System.out.print(getL(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void showU() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                System.out.print(getU(i, j) + " ");
            }
            System.out.println();
        }
    }
}
