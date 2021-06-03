import NewtonMethods.ClassicNewtonMethod;
import QuasiNewton.BFGS;
import QuasiNewton.PowellMethod;
import TestFunctions.*;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    public static final double EPS = 0.0000001;


    private static void test(String methodName, Method method, Function function, double[] start) throws IOException {
        System.out.println(methodName + ": " + Arrays.toString(method.findMinimum(function, start)));
    }

    private static void setLogger(){


    }


    public static void main(String[] args) throws IOException {

        ClassicNewtonMethod m = new ClassicNewtonMethod(new GaussSolver(),  EPS);

        m.findMinimum(new Function2_1(), new double[]{2, 2});

    }
}
