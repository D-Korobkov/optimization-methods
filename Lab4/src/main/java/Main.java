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
        Function f = new Function2_4();
        double[] start = new double[]{0, 0};
        test("BFGS", new BFGS(EPS), f, start);
        test("Powell", new PowellMethod(EPS), f, start);
    }
}
