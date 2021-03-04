package search;

import interfaces.MathFunction;
import interfaces.Search;

/**
 * Класс поиска методом Парабол
 * @see search.AbstractSearch
 */
public class ParabolSearch extends AbstractSearch {

    /**
     * Точка отвечающая условиям F(left) >= F(middleSrart) && F(middleSrart) <= F(rightBorder)
     */
    private final double middleStart;


    /**
     * Конструктор - создание объекта с заданными свойствами
     * @param function - функция, на которой ищут минимум
     * @param leftBorder - левая граница поиска
     * @param rightBorder - правая граница поиска
     * @param epsilon - точность вычислений
     */
    public ParabolSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder, epsilon);
        if (Double.isNaN(function.run(leftBorder)) || Double.isNaN(function.run(rightBorder))) {
            throw new IllegalArgumentException("Function must be defined in given points");
        }
        if (function.run(leftBorder) < function.run(rightBorder)) {
            if (function.run(leftBorder) > function.run(leftBorder + epsilon)) {
                middleStart = leftBorder + epsilon;
            } else {
                middleStart = leftBorder;
            }
        } else {
            if (function.run(rightBorder) > function.run(rightBorder - epsilon)) {
                middleStart = rightBorder - epsilon;
            } else {
                middleStart = rightBorder;
            }
        }
    }

    /**
     * Функция поиска минимума {@link Search#searchMinimum()}
     * @return возвращает точку минимума на промежутке
     */
    @Override
    public double searchMinimum() {
        if (leftBorder == middleStart || middleStart == rightBorder) {
            return middleStart;
        }
        double x1 = leftBorder;
        double x2 = middleStart;
        double x3 = rightBorder;
        double prevX, nextX, fNextX, f1, f2, f3, a1, a2;
        nextX = x1;
        f1 = function.run(x1);
        f2 = function.run(x2);
        f3 = function.run(x3);
        do {
            prevX = nextX;
            a1 = (f2 - f1) / (x2 - x1);
            a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);
            nextX = (x1 + x2 - a1 / a2) / 2;
            fNextX = function.run(nextX);
            if (nextX < x2) {
                if (fNextX >= f2) {
                    x1 = nextX;
                    f1 = fNextX;
                } else {
                    x3 = x2;
                    f3 = f2;
                    x2 = nextX;
                    f2 = fNextX;
                }
            } else {
                if (fNextX <= f2) {
                    x1 = x2;
                    f1 = f2;
                    x2 = nextX;
                    f2 = fNextX;
                } else {
                    x3 = nextX;
                    f3 = fNextX;
                }
            }
        } while (Math.abs(prevX - nextX) > epsilon);
        return nextX;
    }


}
