package strategies;

import interfaces.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс со стратегией поиска точки минимума методом Фибоначчи
 *
 * @see interfaces.Strategy
 */
public class FibonacciSearchStrategy implements Strategy {
    /** Поле список чисел Фибоначчи */
    private final List<Long> fibonacci;
    /** Поле количество требуемых итераций */
    private int iterations = 1;
    /** Поле количество совершённых итераций */
    private int passed = 0;
    /** Поле длина начального отрезка */
    private final double lengthOfSection;


    /**
     * Конструктор - создание стратегии и вычисление значений чисел фибоначчи
     *
     * @param lengthOfSection длина начального отрезка
     * @param epsilon заданная точность
     * @see search.FibonacciSearch
     */
    public FibonacciSearchStrategy(double lengthOfSection, double epsilon) {
        this.lengthOfSection = lengthOfSection;

        fibonacci = new ArrayList<>();
        fibonacci.add(0L);
        fibonacci.add(1L);
        while (lengthOfSection / epsilon >= fibonacci.get(iterations)) {
            fibonacci.add(fibonacci.get(iterations) + fibonacci.get(iterations - 1));
            iterations++;
        }
    }

    /**
     * {@link interfaces.Strategy#isEnd(double, double)}
     */
    @Override
    public boolean isEnd(double left, double right) {
        return passed++ > iterations - 2;
    }

    /**
     * {@link interfaces.Strategy#runForLeftBorder(double, double)}
     */
    @Override
    public double runForLeftBorder(double left, double right) {
        return left + lengthOfSection * fibonacci.get(iterations - passed - 1) / fibonacci.get(iterations);
    }

    /**
     * {@link interfaces.Strategy#runForRightBorder(double, double)}
     */
    @Override
    public double runForRightBorder(double left, double right) {
        return left + lengthOfSection * fibonacci.get(iterations - passed) / fibonacci.get(iterations);
    }
}
