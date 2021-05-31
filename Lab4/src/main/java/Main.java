import NewtonMethods.ClassicNewtoneMethod;
import functions.QuadraticFunction;
import interfaces.Function;
import interfaces.Method;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static void classicNewtoneChecker() throws IOException {
        Method classicNewtone = new ClassicNewtoneMethod();
        Function f1 = new QuadraticFunction(new double[][]{{2, 0}, {0, 2}}, new double[]{0, 0}, 0);
        Function f2 = new QuadraticFunction(new double[][]{{20, 20}, {20, 40}}, new double[]{10, 10}, 10);

        System.out.println("Real = [0, 0] :: Ans = " + Arrays.toString(classicNewtone.findMinimum(f1, new double[]{2, 2})));
        System.out.println("Real = [0, 0] :: Ans = " + Arrays.toString(classicNewtone.findMinimum(f1, new double[]{2, 2})));
        System.out.println("Real = [0.5, 0] :: Ans = " + Arrays.toString(classicNewtone.findMinimum(f2, new double[]{2, 2})));
        System.out.println("Real = [0.5, 0] :: Ans = " + Arrays.toString(classicNewtone.findMinimum(f2, new double[]{2, 2})));
    }

    public static void main(String[] args) throws IOException {

        classicNewtoneChecker();

    }
}
