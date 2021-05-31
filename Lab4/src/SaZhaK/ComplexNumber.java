/**
 * This class represents complex numbers in maths
 *
 * @Fields
 *  re - real part of a complex number
 *  im - imaginary part of a complex number
 *  radius - length of a radius vector in a rectangular coordinate system (Re, Im) to the point represented by the imaginary number
 *  angle - size of an angle between the Re axis and the radius vector in degrees (clockwise)
 *
 * @Methods
 *  getRe - returns the value of a real part of the number
 *  getIm - returns the value of an imaginary part of the number
 *  getRadius - returns the value of a radius
 *  getAngle - returns the value of an angle
 *
 *  add - adds the number given as a parameter to the current number and returns the value of a result as a new complex number
 *  subtract - subtracts the number given as a parameter from the current number and returns the value of a result as a new complex number
 *  multiply - multiplies the number given as a parameter and the current number and returns the value of a result as a new complex number
 *  divide - divides the current number by the number given as a parameter and returns the value of a result as a new complex number
 *
 *  getConjugate - returns the value of a conjugate complex number as a new complex number
 *
 *  pow - extents the current complex number to the power given as a parameter
 *  roots - returns an array of roots of the power given as a parameter as an array of new complex numbers
 * */

package SaZhaK;

public class ComplexNumber {
    private double re;
    private double im;
    private double radius;
    private double angle;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
        this.radius = Math.sqrt(Math.pow(re, 2) +
                Math.pow(im, 2));
        this.angle = Math.toDegrees(Math.acos(re / this.radius));
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public double getRadius () {
        return radius;
    }

    public double getAngle () {
        return angle;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ComplexNumber)) throw new ClassCastException();

        if (((ComplexNumber) obj).re == this.re && ((ComplexNumber) obj).im == this.im) return true;
        else return false;
    }

    public String toString() {
        if (im >= 0) return this.re + " + " + this.im + "i";
        else return this.re + " - " + (-1 * this.im) + "i";
    }

    public ComplexNumber add (ComplexNumber z) {
        return new ComplexNumber(this.re + z.re, this.im + z.im);
    }

    public ComplexNumber subtract (ComplexNumber z) {
        return new ComplexNumber(this.re - z.re, this.im - z.im);
    }

    public ComplexNumber multiply (ComplexNumber z) {
        return new ComplexNumber(this.re * z.re - this.im * z.im, this.re * z.im + this.im * z.re);
    }

    public ComplexNumber divide (ComplexNumber z) {
        return new ComplexNumber((this.re * z.re + this.im * z.im) / (Math.pow(z.re, 2) + Math.pow(z.im, 2)),
                (this.im * z.re - this.re * z.im) / (Math.pow(z.re, 2) + Math.pow(z.im, 2)));
    }

    public ComplexNumber getConjugate () {
        return new ComplexNumber(this.re, -1 * this.im);
    }

    public void pow (int power) {
        radius = Math.pow(radius, power);
        angle = (angle * power) % 360;
        re = radius * Math.cos(angle);
        im = radius * Math.sin(angle);
    }

    public ComplexNumber[] roots (int power) {
        ComplexNumber[] roots = new ComplexNumber[power];
        double curRadius = Math.pow(this.radius, 1. / power);
        double curAngle;

        for (int k = 0; k < power; k++) {
            curAngle = (this.angle + 360 * (k+1)) / power;
            roots[k] = new ComplexNumber(curRadius * Math.cos(curAngle), curRadius * Math.signum(curAngle));
        }
        return roots;
    }
}