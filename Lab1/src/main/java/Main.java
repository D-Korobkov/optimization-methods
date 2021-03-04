import functions.MultiModalFunction;
import interfaces.MathFunction;
import interfaces.Search;
import search.*;
import functions.FuncForLab1;

public class Main {
    public static void main(String[] args) {
        MathFunction function = new FuncForLab1();
        double eps = 0.000000000001;
        for (double nEps = eps; nEps < 1; nEps *= 5) {
            Search method = new BrentSearch(function, 0.1, 2.5, nEps);
            //System.out.println("(eps = " + nEps + ") x = " + method.searchMinimum());
            System.out.println(Math.abs(Math.log(nEps)) + " ");
            method.searchMinimum();
        }
    }
}
