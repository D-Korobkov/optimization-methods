import QuasiNewton.BFGS;
import QuasiNewton.PowellMethod;
import TestFunctions.*;
import interfaces.Function;
import interfaces.Method;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static final double EPS = 0.0000001;


    private static void test(String methodName, Method method, Function function, double[] start) throws IOException {
        System.out.println(methodName + ": " + Arrays.toString(method.findMinimum(function, start)));
    }

    public static void main(String[] args) throws IOException {
        Function f22 = new Function2_3();
        double[] start = new double[]{1, 1, 1, 1};
        test("BFGS", new BFGS(EPS), f22, start);
        test("Powell", new PowellMethod(EPS), f22, start);
    }
}
