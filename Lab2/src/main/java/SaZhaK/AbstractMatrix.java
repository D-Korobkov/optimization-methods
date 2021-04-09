/**
 * This class represents an abstract matrix that can be implemented to store different types of values and execute specific operations
 *
 * @Fields
 * rows - amount of rows in matrix
 * columns - amount of columns in matrix
 * elements - two dimensional array tha contains elements of the specified matrix
 *
 * @Methods
 * getRows - returns number of rows of the matrix
 * getColumns - returns number of columns of the matrix
 * setElement - changes value of the element at the specified position
 * getElement - returns value of the element at the specified position
 *
 * print - prints current matrix to the console
 * transpose - transposes current matrix
 *
 * @AbstractMethods
 * add - adds another matrix given as a parameter to the current matrix
 * subtract - subtracts another matrix given as a parameter from the current matrix
 * multiply - multiplies current matrix and another matrix given as a parameter
* */

package SaZhaK;

public abstract class AbstractMatrix {
    protected int rows;
    protected int columns;
    protected Object[][] elements;

    int getRows() {
        return this.rows;
    }

    int getColumns() {
        return this.columns;
    }

    public void setElement(int row, int column, Object element) {
        this.elements[row - 1][column - 1] = element;
    }

    public Object getElement(int row, int column) {
        return this.elements[row - 1][column - 1];
    }

    public void print() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                System.out.print(this.elements[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void transpose() throws RuntimeException {
        if (this.rows == this.columns) {
            Object temp;
            for (int i = 0; i < this.rows; ++i) {
                for (int j = i; j < this.columns; ++j) {
                    temp = this.elements[i][j];
                    this.elements[i][j] = this.elements[j][i];
                    this.elements[j][i] = temp;
                }
            }
        } else throw new RuntimeException("Is not a square matrix");
    }

    public abstract Matrix add(Matrix matrix);
    public abstract Matrix subtract(Matrix matrix);
    public abstract Matrix multiply(Matrix matrix);
}