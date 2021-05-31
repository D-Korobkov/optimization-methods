/**
 * This class represents polynomials
 *
 * @Fields
 *  usedNames - list of used user names
 *  lastDefaultName - first unused letter to be used as a polynomials default name
 *  degree - degree of the current polynomial
 *  coefficients - coefficients of the current polynomial
 *  name - name of the current polynomial
 *
 * @Methods
 *  add - returns a sum of polynomial given as a parameter and the current polynomial
 *  subtract - returns a difference of polynomial given as a parameter and the current polynomial
 *  multiply - returns a product of polynomial given as a parameter and the current polynomial
 *  multiply - returns a product of number given as a parameter and the current polynomial
 * */

package SaZhaK;

import java.util.LinkedList;

public class Polynomial {
    private static LinkedList<Character> usedNames = new LinkedList<>();
    private static Character lastDefaultName = 'A';
    private int degree;
    private double[] coefficients;
    private char name;

    public Polynomial(double[] coefficients) {
        this.degree = coefficients.length - 1;
        this.coefficients = coefficients;
        this.name = generateNewName();
        Polynomial.lastDefaultName++;
    }

    public Polynomial(double[] coefficients, char name) {
        this.degree = coefficients.length - 1;
        this.coefficients = coefficients;
        this.name = name;
        Polynomial.usedNames.add(name);
    }

    private Character generateNewName() {
        Character curName = Polynomial.lastDefaultName;
        while (true) {
            if (checkIfNameExists(curName)) {
                ++curName;
            } else {
                break;
            }
        }
        return curName;
    }

    private boolean checkIfNameExists(char curName) {
        for (Character name : Polynomial.usedNames) {
            if (name.equals(curName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < coefficients.length; i++) {
            hashCode += coefficients[i];
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Polynomial)) return false;
        return this.hashCode() == object.hashCode();
    }

    @Override
    public String toString() {
        String polynomial = "";
        int curDegree = this.degree;

        polynomial += this.name + "(x) = ";
        if (checkIfInteger(coefficients[0]) && coefficients[0] != 1) polynomial += (int) coefficients[0];
        else if (coefficients[0] != 1) polynomial += coefficients[0];
        polynomial += "x^" + (curDegree--);

        for (int i = 1; i < degree; i++) {
            if (coefficients[i] > 0) {
                polynomial += " + ";
                if (checkIfInteger(coefficients[i])) polynomial += (int) coefficients[i];
                else polynomial += coefficients[i];

                if (curDegree != 1) polynomial += "x^" + (curDegree--);
                else polynomial += "x";
            } else if (coefficients[i] < 0) {
                polynomial += " - ";
                if (checkIfInteger(coefficients[i])) polynomial += (int) coefficients[i] * (-1);
                else polynomial += coefficients[i] * (-1);

                if (curDegree != 1) polynomial += "x^" + (curDegree--);
                else polynomial += "x";
            }
        }

        if (coefficients[degree] > 0) {
            polynomial += " + ";
            if (checkIfInteger(coefficients[degree])) polynomial += (int) coefficients[degree];
            else polynomial += coefficients[degree];
        } else if (coefficients[degree] < 0) {
            polynomial += " - ";
            if (checkIfInteger(coefficients[degree])) polynomial += (int) coefficients[degree] * (-1);
            else polynomial += coefficients[degree] * (-1);
        }
        return polynomial;
    }

    private boolean checkIfInteger(double number) {
        return (number * 10) % 10 == 0;
    }

    public Polynomial add(Polynomial addend) {
        Polynomial max, min;
        if (this.degree > addend.degree) {
            max = this;
            min = addend;
        } else {
            max = addend;
            min = this;
        }
        double[] newCoefficients = new double[max.degree + 1];
        for (int i = 0; i < max.degree + 1; i++) {
            if (i < max.degree - min.degree) {
                newCoefficients[i] = max.coefficients[i];
            } else {
                newCoefficients[i] = max.coefficients[i] + min.coefficients[i - (max.degree - min.degree)];
            }
        }
        return new Polynomial(newCoefficients);
    }

    public Polynomial subtract(Polynomial subtrahend) {
        Polynomial max, min;
        if (this.degree > subtrahend.degree) {
            max = this;
            min = subtrahend;
        } else {
            max = subtrahend;
            min = this;
        }
        double[] newCoefficients = new double[max.degree + 1];
        for (int i = 0; i < max.degree + 1; i++) {
            if (i < max.degree - min.degree) {
                if (this.degree > subtrahend.degree) newCoefficients[i] = max.coefficients[i];
                else newCoefficients[i] = (-1) * max.coefficients[i];
            } else {
                if (this.degree > subtrahend.degree)
                    newCoefficients[i] = max.coefficients[i] - min.coefficients[i - (max.degree - min.degree)];
                else newCoefficients[i] = min.coefficients[i - (max.degree - min.degree)] - max.coefficients[i];
            }
        }
        return new Polynomial(newCoefficients);
    }

    public Polynomial multiply(Polynomial multiplier) {
        double[] newCoefficients = new double[this.degree + multiplier.degree + 1];

        double curCoefficient;
        for (int i = 0; i < this.degree + multiplier.degree + 1; i++) {
            curCoefficient = 0;

            for (int k = 0; k < this.degree + 1; k++) {
                for (int l = 0; l < multiplier.degree + 1; l++) {
                    if (k + l == i) {
                        curCoefficient += this.coefficients[k] * multiplier.coefficients[l];
                    }
                }
            }
            newCoefficients[i] = curCoefficient;
        }

        return new Polynomial(newCoefficients);
    }

    public Polynomial multiply(Double multiplier) {
        double[] newCoefficients = new double[this.degree + 1];

        for (int i = 0; i < this.degree + 1; i++) {
            newCoefficients[i] = this.coefficients[i] * multiplier;
        }

        return new Polynomial(newCoefficients);
    }
}