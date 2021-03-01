package interfaces;

public interface Strategy {
    /**
     * Функция для проверки терминального условия
     * @param left - левая граница текущего отрезка
     * @param right - правая граница текущего отрезка
     * @return возвращает true, если терминальное условие выполнено; иначе - false
     */
    boolean isEnd(double left, double right);

    /**
     * Функция для подсчёта нового значения левой границы отрезка
     * @param left - старая левая граница
     * @param right - старая правая граница
     * @return возвращает новое значение левой границы
     */
    double runForLeftBorder(double left, double right);

    /**
     * Функция для подсчёта нового значения правой границы отрезка
     * @param left - старая левая граница
     * @param right - старая правая граница
     * @return возвращает новое значение правой границы
     */
    double runForRightBorder(double left, double right);
}
