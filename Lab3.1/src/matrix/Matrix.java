package matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Matrix {

    public double al[], au[], ia[], d[];
    private static final String[] NAME_OF_FILES = {"au.txt", "al.txt", "ia.txt", "d.txt"};

    public Matrix(String pathOfMatrix) {
        for (String fileName : NAME_OF_FILES) {
            try (BufferedReader reader = Files.newBufferedReader(Path.of(pathOfMatrix + File.pathSeparator + fileName))){
                switch (fileName) {
                    case "au.txt" -> au = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "al.txt" -> al = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "ia.txt" -> ia = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
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
//            au
            
        } else {
//            al
        }
        return 0;
    }

    public double getLowTriangleIJ(int i, int j) {
        return 0;
    }

    public double getUpTrieangleIJ(int i, int j) {
        return 0;
    }

    public int size() {
        return d.length;
    }

    public double getL(int i, int j) {
        return 0;
    }

    public double getU(int i, int j) {
        return 0;
    }

    public void setL(int i, int j, double value) {

    }

    public void setU(int i, int j, double value) {

    }

    private void changeToLU() {
        int n = 10000;//TODO change to size
        setL(0, 0, getIJ(0, 0));//TODO chek getIJ



        for(int i = 1; i < n; i++){

            //setting L_ij

            for(int j = 0; j < i; j++){

                double substract = 0;
                for(int k = 0; k < j; k++){
                    substract += getL(i, k) * getU(k, j);
                }
                setL(i, i, getIJ(i, j) - substract);
            }

            //setting U_ji

            for(int j = 0; j < i; j++){



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
}
