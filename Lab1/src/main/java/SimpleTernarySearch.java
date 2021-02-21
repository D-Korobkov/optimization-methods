public class SimpleTernarySearch extends AbstractTernarySearch {
    private final double delta;

    public SimpleTernarySearch(MathFunction function,
                               double leftBorder,
                               double rightBorder,
                               double epsilon,
                               double delta)
    {
        super(function, leftBorder, rightBorder, epsilon);
        this.delta = delta;
    }

    public double searchMinimum() {
        return super.searchMinimum(new SimpleStrategy());
    }

    class SimpleStrategy implements Strategy {
        public double runForLeftBorder(double left, double right) {
            return (right + left - delta) / 2;
        }

        public double runForRightBorder(double left, double right) {
            return (right + left + delta) / 2;
        }
    }
}
