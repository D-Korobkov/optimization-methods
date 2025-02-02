package search;

import chart.Basic;
import chart.UserPanel;
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

    protected final Basic basic;
    protected int counter = 1;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param function исследуемая функция
     * @param leftBorder левая граница отрезка
     * @param rightBorder правая граница отрезка
     * @param epsilon требуемая точность
     */
    public AbstractSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon, UserPanel userPanel) {
        this.function = function;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.epsilon = epsilon;
        basic = new Basic(userPanel);
    }

    /**
     * Функция нахождения минимума согласно какой-то стратегии
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
            basic.addNewSection(left, right, counter++);
        }
        basic.drawChart(function, leftBorder, rightBorder);
        return (left + right) / 2;
    }

    /**
     * Функция нахождения минимума согласно какой-то стратегии,
     * на каждой итерации пересчитывающая точку и значение функции
     * в этой точке лишь один раз
     * @param calculation стратегия
     * @return возвращает приближённое значение точки минимума
     * @see Search
     * @see Strategy
     */
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
            basic.addNewSection(left, right, counter++);
        }
        basic.drawChart(function, leftBorder, rightBorder);
        return (left + right) / 2;
    }
}
