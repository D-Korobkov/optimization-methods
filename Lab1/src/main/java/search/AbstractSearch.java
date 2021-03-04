package search;

import interfaces.MathFunction;
import interfaces.Search;
import interfaces.Strategy;

/**
 * Класс для поиска приближённой точки минимума унимодальной функции
 */
public abstract class AbstractSearch implements Search {
    /** Поле исследуемая функция */
    protected final MathFunction function;
    /** Поле левая граница исследуемого значения */
    protected final double leftBorder;
    /** Поле правая граница исследуемого значения */
    protected final double rightBorder;
    /** Поле заданная точность вычислений */
    protected final double epsilon;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param function исследуемая функция
     * @param leftBorder левая граница отрезка
     * @param rightBorder правая граница отрезка
     * @param epsilon требуемая точность
     */
    public AbstractSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        this.function = function;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.epsilon = epsilon;
    }

    /**
     * Функция нахождения минимума согласно какй-то стратегии
     * @param calculation стратегия
     * @return возвращает приближённое значение точки минимума
     * @see Search
     * @see Strategy
     */
    protected double searchMinimum(final Strategy calculation) {
        double left = leftBorder;
        double right = rightBorder;
        while (!calculation.isEnd(left, right)) {
            double leftMid = calculation.runForLeftBorder(left, right);
            double rightMid = calculation.runForRightBorder(left, right);
            if (function.run(leftMid) <= function.run(rightMid)) {
                right = rightMid;
            } else {
                left = leftMid;
            }
        }
        return (left + right) / 2;
    }

    protected double optimizedSearchMinimum(final Strategy calculation) {
        double left = leftBorder;
        double right = rightBorder;
        double leftMid = calculation.runForLeftBorder(left, right);
        double rightMid = calculation.runForRightBorder(left, right);
        double f1 = function.run(leftMid);
        double f2 = function.run(rightMid);
        while (!calculation.isEnd(left, right)) {
            if (f1 <= f2) {
                right = rightMid;
                rightMid = leftMid;
                f2 = f1;
                leftMid = calculation.runForLeftBorder(left, right);
                f1 = function.run(leftMid);
            } else {
                left = leftMid;
                leftMid = rightMid;
                f1 = f2;
                rightMid = calculation.runForRightBorder(left, right);
                f2 = function.run(rightMid);
            }
        }
        return (left + right) / 2;
    }
}
