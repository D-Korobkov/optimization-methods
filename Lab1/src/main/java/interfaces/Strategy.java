package interfaces;

public interface Strategy {
    boolean isEnd(double left, double right);

    double runForLeftBorder(double left, double right);

    double runForRightBorder(double left, double right);
}
