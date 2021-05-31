/**
 * This class represents a main.java.matrix that can contain number values
 * This class extends the AbstractMatrix class
 *
 * @ExtendedFields
 * rows - amount of rows in main.java.matrix
 * columns - amount of columns in main.java.matrix
 * elements - two dimensional array tha contains elements of the specified main.java.matrix
 *
 * @ExtendedMethods {
 *
 * @OverridenMethods {
 * equals
 * hashCode
 * toString
 * add - adds another main.java.matrix given as a parameter to the current main.java.matrix
 * subtract - subtracts another main.java.matrix given as a parameter from the current main.java.matrix
 * multiply - multiplies current main.java.matrix and the number given as a parameter
 * multiply - multiplies current main.java.matrix and another main.java.matrix given as a parameter
 * }
 *
 * getRows - returns number of rows of the main.java.matrix
 * getColumns - returns number of columns of the main.java.matrix
 * setElement - changes value of the element at the specified position
 * getElement - returns value of the element at the specified position
 *
 * print - prints current main.java.matrix to the console
 * transpose - transposes current main.java.matrix
 * }
 *
 * @Constructors
 * public Matrix(int rows, int columns, Double[] elements) - creates a new main.java.matrix that contains Double values
 * The values themselves are given as an array of Double elements
 *
 * @StaticMethods
 * getIdentityMatrix - creates an identity main.java.matrix of the specified size
 *
 * @Methods
 * getTrace - returns the trace of the current main.java.matrix (sum of the diagonal elements)
 * getDeterminant - return the determinant of the current main.java.matrix (algorithm is recursive)
 * getMinorMatrix - creates a new main.java.matrix, which elements are minors of appropriate elements of the current main.java.matrix
 * getAlgebraicAdditionsMatrix - creates a new main.java.matrix, which elements are algebraic additions of appropriate elements of the current main.java.matrix
 * */

package SaZhaK;

public class Matrix extends AbstractMatrix{

    public Matrix(int rows, int columns, Double[] elements) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new Double[rows][columns];

        int lastElementIdx = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                this.elements[i][j] = elements[lastElementIdx++];
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        return ((object instanceof Matrix) && (this.hashCode() == ((Matrix) object).hashCode()));
    }

    @Override
    public int hashCode() {
        double result = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result += (Double)this.elements[i][j];
            }
        }
        return (int) (result * 1000);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                output += this.elements[i][j] + " ";
            }
            output += "\n";
        }
        return output;
    }

    public static Matrix getIdentityMatrix(int size) {
        Double[] elements = new Double[size * size];
        int elementsLastIdx = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) elements[elementsLastIdx++] = 1.;
                else elements[elementsLastIdx++] = 0.;
            }
        }
        return new Matrix(size, size, elements);
    }

    @Override
    public void transpose() throws RuntimeException {
        if (this.rows == this.columns) {
            double temp;
            for (int i = 0; i < this.rows; ++i) {
                for (int j = i; j < this.columns; ++j) {
                    temp = (Double)this.elements[i][j];
                    this.elements[i][j] = this.elements[j][i];
                    this.elements[j][i] = temp;
                }
            }
        } else throw new RuntimeException("Is not a square main.java.matrix");
    }

    public double getTrace() throws RuntimeException {
        if (this.rows == this.columns) {
            double trace = 0;

            for (int i = 0; i < this.rows; ++i) {
                trace += (Double)this.elements[i][i];
            }
            return trace;
        } else throw new RuntimeException("Is not a square main.java.matrix");
    }

    @Override
    public Matrix add(Matrix matrix) throws RuntimeException {
        if (this.rows == matrix.rows && this.columns == matrix.columns) {
            Double[] newElements = new Double[this.rows * this.columns];

            int newElementsLastIdx = 0;
            for (int i = 0; i < this.rows; ++i) {
                for (int j = 0; j < this.columns; ++j) {
                    newElements[newElementsLastIdx++] = (Double)this.elements[i][j] + (Double)matrix.elements[i][j];
                }
            }
            return new Matrix(this.rows, this.columns, newElements);
        } else throw new RuntimeException("Incompatible sizes");
    }

    @Override
    public Matrix subtract(Matrix matrix) throws RuntimeException {
        if (this.rows == matrix.rows && this.columns == matrix.columns) {
            Double[] newElements = new Double[this.rows * this.columns];

            int newElementsLastIdx = 0;
            for (int i = 0; i < this.rows; ++i) {
                for (int j = 0; j < this.columns; ++j) {
                    newElements[newElementsLastIdx++] = (Double)this.elements[i][j] - (Double)matrix.elements[i][j];
                }
            }
            return new Matrix(this.rows, this.columns, newElements);
        } else throw new RuntimeException("Incompatible sizes");
    }

    public Matrix multiply(double constant) {
        Double[] newElements = new Double[this.rows * this.columns];

        int newElementsLastIdx = 0;
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                newElements[newElementsLastIdx++] = (Double)this.elements[i][j] * constant;
            }
        }
        return new Matrix(this.rows, this.columns, newElements);
    }

    @Override
    public Matrix multiply(Matrix matrix) throws RuntimeException {
        if (this.columns == matrix.rows) {
            Double[] newElements = new Double[this.rows * matrix.columns];
            int newElementsLastIdx = 0;
            double curElement = 0;

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < matrix.columns; j++) {
                    for (int k = 0; k < this.columns; k++) {
                        curElement += (Double)this.elements[i][k] * (Double)matrix.elements[k][j];
                    }
                    newElements[newElementsLastIdx++] = curElement;
                    curElement = 0;
                }
            }
            return new Matrix(this.rows, matrix.columns, newElements);
        } else throw new RuntimeException("Incompatible sizes");
    }

    public double getDeterminant() throws RuntimeException {
        if (this.rows == this.columns) {

            int determinant = 0;
            Matrix newMatrix;
            Double[] newElements;

            if (this.rows == 1) {
                return (Double)this.elements[0][0];
            } else if (this.rows == 2) {
                return (Double)this.elements[0][0] * (Double)this.elements[1][1] - (Double)this.elements[0][1] * (Double)this.elements[1][0];
            } else {
                for (int i = 0; i < this.columns; ++i) {

                    newElements = new Double[(this.rows - 1) * (this.rows - 1)];
                    int newELementsLastIdx = 0;

                    for (int j = 1; j < this.rows; ++j) {
                        for (int k = 0; k < this.columns; ++k) {
                            if (k != i) newElements[newELementsLastIdx++] = (Double)this.elements[j][k];
                        }
                    }

                    newMatrix = new Matrix(this.rows - 1, this.rows - 1, newElements);

                    if (i % 2 == 0) determinant += (Double)this.elements[0][i] * newMatrix.getDeterminant();
                    else determinant += (-1) * (Double)this.elements[0][i] * newMatrix.getDeterminant();
                }
                return determinant;
            }
        } else throw new RuntimeException("Is not a square main.java.matrix");
    }

    public Matrix getMinorMatrix() throws RuntimeException {
        if (this.columns == this.rows) {

            Double[] newElements = new Double[this.rows * this.columns - 2];
            int newElementsLastIdx = 0;

            Double[] minors = new Double[this.rows * this.columns];
            int minorsLastIdx = 0;

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    for (int k = 0; k < this.rows; k++) {
                        for (int l = 0; l < this.columns; l++) {
                            if (k != i && l != j) {
                                newElements[newElementsLastIdx++] = (Double)this.elements[k][l];
                            }
                        }
                    }
                    Matrix newMatrix = new Matrix(this.rows - 1, this.columns - 1, newElements);
                    minors[minorsLastIdx++] = newMatrix.getDeterminant();
                    newElementsLastIdx = 0;
                }
            }
            return new Matrix(this.rows, this.columns, minors);
        } else throw new RuntimeException("Is not a square main.java.matrix");
    }

    public Matrix getAlgebraicAdditionsMatrix() throws RuntimeException {
        if (this.columns == this.rows) {

            Double[] newElements = new Double[this.rows * this.columns - 2];
            int newElementsLastIdx = 0;

            Double[] minors = new Double[this.rows * this.columns];
            int minorsLastIdx = 0;

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    for (int k = 0; k < this.rows; k++) {
                        for (int l = 0; l < this.columns; l++) {
                            if (k != i && l != j) {
                                newElements[newElementsLastIdx++] = (Double)this.elements[k][l];
                            }
                        }
                    }
                    Matrix newMatrix = new Matrix(this.rows - 1, this.columns - 1, newElements);

                    if ((i + j) % 2 == 1) minors[minorsLastIdx++] = (-1) * newMatrix.getDeterminant();
                    else minors[minorsLastIdx++] = newMatrix.getDeterminant();

                    newElementsLastIdx = 0;
                }
            }
            return new Matrix(this.rows, this.columns, minors);
        } else throw new RuntimeException("Is not a square main.java.matrix");
    }

    public Matrix getInverseMatrix() {
        Matrix resultMatrix = this.getAlgebraicAdditionsMatrix();
        resultMatrix.transpose();
        return resultMatrix.multiply(1 / this.getDeterminant());
    }
}