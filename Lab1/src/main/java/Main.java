import interfaces.MathFunction;
import interfaces.Search;
import search.*;
import functions.FuncForLab1;

public class Main {
    public static void main(String[] args) {
        MathFunction function = new FuncForLab1();
        double eps = 0.00000001;
        for (double nEps = eps; nEps < 1; nEps *= 5) {
            Search method = new DichotomyMethod(function, 0.1, 2.5, nEps, nEps / 2);
            System.out.println("(eps = " + nEps + "; delta = eps / 2) x = " + method.searchMinimum());
        }
        /*Search method1 = new DichotomyMethod(function, 0.1, 2.5, 0.0000001, 0.00000005);
        Search method2 = new GoldenRatioSearch(function, 0.1, 2.5, 0.0000001);
        Search forbiddenMethod2 = new ForbiddenGoldenSearch(function, 0.1, 2.5, 0.0000001);
        Search forbiddenMethod3 = new ForbiddenFibonacciSearch(function, 0.1, 2.5, 0.00000001);
        Search method3 = new FibonacciSearch(function, 0.1, 2.5, 0.0000001);
        Search method4 = new ParabolSearch(function,0.1, 2.5, 0.0000001);*/
        //System.out.println(method1.searchMinimum());
        //System.out.println(method2.searchMinimum() /*+ " compare to forbidden: " + forbiddenMethod2.searchMinimum()*/);
        //System.out.println(method3.searchMinimum());
        //System.out.println("forbidden fibonacci: " + forbiddenMethod3.searchMinimum());
        //System.out.println(method4.searchMinimum());
    }
}
