import interfaces.MathFunction;
import interfaces.Search;
import search.*;

public class Main {
    public static void main(String[] args) {
        MathFunction function = new FuncForLab1();
        Search method1 = new SimpleSearch(function, 0.1, 1, 0.0000001, 0.00000005);
        Search method2 = new GoldenRatioSearch(function, 0.1, 1, 0.0000001);
        Search method3 = new FibonacciSearch(function, 0.1, 1, 0.0000001);
        Search method4 = new ParabolSearch(function,0.1, 1, 0.0000001);
        Search method5 = new BrentSearch(function, 0.1, 1, 0.0000001);
        System.out.println(method1.searchMinimum());
        System.out.println(method2.searchMinimum());
        System.out.println(method3.searchMinimum());
        System.out.println(method4.searchMinimum());
        System.out.println(method5.searchMinimum());
    }
}
