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
    }

}
