public class Main {
    public static void main(String[] args) {
        MathFunction function = new FuncForLab1();
        Search method1 = new SimpleTernarySearch(function, -1, 1, 0.000001, 0.0000005);
        System.out.print(method1.searchMinimum());
    }
}
