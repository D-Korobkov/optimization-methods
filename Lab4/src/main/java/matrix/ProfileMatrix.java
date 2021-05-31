package matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Class Class of ProfileMatrix realisation
 */
public class ProfileMatrix {

    /**
     * profile rows of lower triangle
     */
    public double[] al;
    /**
     * profile columns of upper triangle
     */
    private double[] au;
    /**
     * main.java.matrix diagonal
     */
    private double[] d;

    /**
     * index array for al, au
     */
    public int[] ia;

    /**
     * is main.java.matrix LU decompositioned
     */
    boolean isLU;

    /**
     * Set of files for reading and setting ProfileMatrix.
     */
    private static final String[] NAME_OF_FILES = {"au.txt", "al.txt", "ia.txt", "d.txt"};


    /**
     * Constructor for ProfileMatrix, that reads {@link #readFromPath(String)} it from directory.
     *
     * @param pathOfMatrix - path to directory, that contains files for main.java.matrix
     */
    public ProfileMatrix(final String pathOfMatrix) {
        this(pathOfMatrix, false);
    }

    /**
     * Constructor for ProfileMatrix, that reads {@link #readFromPath(String)} it from directory.
     * Can specify that main.java.matrix is already LU decompositioned
     *
     * @param pathOfMatrix - path to directory, that contains files for main.java.matrix
     * @param isLU - is main.java.matrix already LU
     */
    public ProfileMatrix(final String pathOfMatrix, final boolean isLU) {
        this.isLU = isLU;
        readFromPath(pathOfMatrix);

    }

    /**
     * Reading Matrix from directory. Using following files: au.txt, al.txt, ia.txt, d.txt.
     *
     * @param pathOfMatrix - path to directory, that contains files for main.java.matrix
     * @see generator.MatrixGenerator
     */
    private void readFromPath(final String pathOfMatrix) {
        for (final String fileName : NAME_OF_FILES) {
            try (final BufferedReader reader = Files.newBufferedReader(Path.of(pathOfMatrix + File.separator + fileName))) {
                switch (fileName) {
                    case "au.txt" -> au = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "al.txt" -> al = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                    case "ia.txt" -> ia = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    case "d.txt" -> d = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter for specified element. Works before LU decomposition
     *
     * @param i - row
     * @param j - column
     * @return - value of ij element
     */
    public double getIJ(final int i, final int j) {
        if (isLU) {
            throw new IllegalStateException("Matrix is LU. Use getU, getL");
        }
        return getIJWithoutException(i, j);
    }

    /**
     * Getter for specified element.
     *
     * @param i - row
     * @param j - column
     * @return - value of ij element
     */
    private double getIJWithoutException(final int i, final int j) {
        if (i == j) {
            return d[i];
        } else if (i < j) {
            return getHighTriangle(i, j);
        } else {
            return getLowTriangle(i, j);
        }
    }

    /**
     * Getter of main.java.matrix dimension.
     * @return - size of diagonal(=dimension)
     */
    public int size() {
        return d.length;
    }

    /**
     * Getter for specified element from specified profile
     *
     * @param i - row if match to upper triangle
     * @param j - column if match to upper triangle
     * @param profile - upper or lower a
     * @return - value of ij element of a
     */
    private double profileGet(final int i, final int j, final double[] profile) {
        if (i == j) {
            throw new IllegalStateException("Coordinate from diag " + i + " " + j);
        }
        if (j > i) {
            return 0;
        }
        final int realCount = ia[i + 1] - ia[i];
        final int imagineCount = i - realCount;
        if (j < imagineCount) {
            return 0;
        } else {
            return profile[ia[i] + j - imagineCount];
        }
    }

    /**
     * Getter for specified element from lower triangle
     *
     * @param i - row
     * @param j - column
     * @return - value of ij element from lower triangle
     */
    public double getLowTriangle(final int i, final int j) {
        return profileGet(i, j, al);
    }

    /**
     * Getter for specified element from upper triangle
     *
     * @param i - row
     * @param j - column
     * @return - value of ij element from upper triangle
     */
    public double getHighTriangle(final int i, final int j) {
        return profileGet(j, i, au);
    }

    /**
     * Asserting that main.java.matrix is LU.
     */
    private void assertLU() {
        if (!isLU) {
            throw new IllegalStateException("Matrix isn't in LU");
        }
    }

    /**
     * Getter for specified element from L main.java.matrix (A = LU)
     *
     * @param i - row
     * @param j - column
     * @return - value of ij element from L
     */
    public double getL(final int i, final int j) {
        assertLU();
        if (i == j) {
            return d[i];
        } else {
            return getLowTriangle(i, j);
        }
    }

    /**
     * Getter for specified element from U main.java.matrix (A = LU)
     *
     * @param i - row
     * @param j - column
     * @return - value of ij element from U
     */
    public double getU(final int i, final int j) {
        assertLU();
        if (i == j) {
            return 1;
        } else {
            return getHighTriangle(i, j);
        }
    }

    /**
     * Setter of specified profile element for specified profile
     *
     * @param i - row if matched to upper triangle
     * @param j - column if matched to upper triangle
     * @param value - setting value
     * @param profile - upper or lower a
     */
    private void setProfile(final int i, final int j, final double value, final double[] profile) {
        final int realCount = ia[i + 1] - ia[i];
        final int imagineCount = i - realCount;
        if (j >= imagineCount) {
            profile[ia[i] + j - imagineCount] = value;
        }
    }

    /**
     * Setter of specified element in L
     *
     * @param i - row
     * @param j - column
     * @param value - setting value
     */
    public void setL(final int i, final int j, final double value) {
        assertLU();
        if (i == j) {
            d[i] = value;
            return;
        }
        if (i < j) {
            return;
        }
        setProfile(i, j, value, al);
    }

    /**
     * Setter of specified element in U
     *
     * @param i - row
     * @param j - column
     * @param value - setting value
     */
    public void setU(final int i, final int j, final double value) {
        assertLU();
        if (i >= j) {
            return;
        }
        setProfile(j, i, value, au);
    }

    /**
     * Decomposition of a main.java.matrix into LU
     */
    public void changeToLU() {
        if (isLU) {
            return;
        }
        isLU = true;

        setL(0, 0, getIJWithoutException(0, 0));

        for (int i = 1; i < size(); i++) {
            //setting L_ij
            for (int j = 0; j < i; j++) {
                double substract = 0;
                for (int k = 0; k < j; k++) {
                    substract += getL(i, k) * getU(k, j);
                }
                setL(i, j, getIJWithoutException(i, j) - substract);
            }

            //setting U_ji
            for (int j = 0; j < i; j++) {
                double substract = 0;
                for (int k = 0; k < j; k++) {
                    substract += getL(j, k) * getU(k, i);
                }
                setU(j, i, (getIJWithoutException(j, i) - substract) / getL(j, j));
            }

            //setting L_ii
            double substract = 0;
            for (int k = 0; k < i; k++) {
                substract += getL(i, k) * getU(k, i);
            }
            setL(i, i, getIJWithoutException(i, i) - substract);

            //setting U_ii
            setU(i, i, 1);
        }
    }

    /**
     * @return - string representation of main.java.matrix
     */
    @Override
    public String toString() {
        return "Matrix{" +
                "al=" + Arrays.toString(al) +
                ", au=" + Arrays.toString(au) +
                ", d=" + Arrays.toString(d) +
                ", ia=" + Arrays.toString(ia) +
                '}';
    }

    /**
     * Writing main.java.matrix in "square" format to System.out
     */
    public void showByGetters() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                System.out.print(getIJ(i, j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Writing L part os Matrix in "square" format to System.out
     */
    public void showL() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                System.out.print(getL(i, j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Writing U part os Matrix in "square" format to System.out
     */
    public void showU() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                System.out.print(getU(i, j) + " ");
            }
            System.out.println();
        }
    }
}
