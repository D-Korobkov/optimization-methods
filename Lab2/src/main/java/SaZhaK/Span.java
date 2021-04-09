/**
 * This class represents a span between two numbers
 *
 * @Fields
 * leftBorder - left border of the current interval
 * rightBorder - right border of the current interval
 *
 * @Methods
 * getLeftBorder - returns the left border of the current interval
 * getRightBorder - return the right border of the current interval
 *
 * isInInterval - checks if the number given as a parameter suits the current interval
 * isInSegment - checks if the number given as a parameter suits current the segment
 * */

package SaZhaK;

public class Span {
    private double leftBorder;
    private double rightBorder;

    public Span(double leftBorder, double rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public double getLeftBorder() {
        return this.leftBorder;
    }

    public double getRightBorder() {
        return this.rightBorder;
    }

    public boolean isInInterval(double number) {
        return (number > this.leftBorder && number < this.rightBorder);
    }

    public boolean isInSegment(double number) {
        return (number >= this.leftBorder && number <= this.rightBorder);
    }
}