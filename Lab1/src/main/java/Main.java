import chart.UserPanel;
import functions.MultiModalFunction;
import interfaces.MathFunction;
import interfaces.Search;
import search.*;
import functions.FuncForLab1;

public class Main {
    public static void main(String[] args) {
        UserPanel panel = new UserPanel(new FuncForLab1());
        panel.show();

        /*MathFunction function = new FuncForLab1();
        double eps = 0.000001;
        Search method = new ParabolSearch(function, 0.1, 2.5, eps);
        method.searchMinimum();*/
        /*for (double nEps = eps; nEps < 1; nEps *= 5) {
            BrentSearch method = new BrentSearch(function, 0.1, 2.5, nEps);
            //System.out.println("(eps = " + nEps + ") x = " + method.searchMinimum());
            method.searchMinimum();
            System.out.println(Math.abs(Math.log(nEps)));
            //System.out.println(method.searchMinimum());
        }*/
    }
}
