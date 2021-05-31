package SaZhaK;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        }
        else if (denominator == 0) throw new ArithmeticException("Division by zero is not possible");
        else {
            this.numerator = numerator;
            this.denominator = denominator;

            simplify();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Fraction)) return false;
        else return object.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return this.numerator + this.denominator;
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    private void simplify() {
        int maxDivisor = countLeastCommonDivisor(numerator, denominator);
        this.numerator = numerator / maxDivisor;
        this.denominator = denominator / maxDivisor;
    }

    private int countLeastCommonDivisor(int num1, int num2) {
        int dividend = Math.max(num1, num2);
        int divisor = Math.min(num1, num2);
        int reminder = dividend % divisor;

        while (reminder != 0) {
            dividend = divisor;
            divisor = reminder;
            reminder = dividend % divisor;
        }

        return divisor;
    }

    public Fraction add(Fraction fraction) {
        int newNumerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
        int newDenominator = this.denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction subtract(Fraction fraction) {
        int newNumerator = this.numerator * fraction.denominator - fraction.numerator * this.denominator;
        int newDenominator = this.denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction multiply(Fraction fraction) {
        int newNumerator = this.numerator * fraction.numerator;
        int newDenominator = this.denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction divide(Fraction fraction) {
        int newNumerator = this.numerator * fraction.denominator;
        int newDenominator = this.denominator * fraction.numerator;
        return new Fraction(newNumerator, newDenominator);
    }
}