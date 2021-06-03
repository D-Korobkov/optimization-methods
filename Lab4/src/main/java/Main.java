import NewtonMethods.ClassicNewtonMethod;
import NewtonMethods.NewtonMethodWithDescentDirection;
import TestFunctions.Function2_1;
import interfaces.Function;
import interfaces.Method;
import logger.FieldLogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final double EPS = 0.0000001;


    private static void test(String methodName, Method method, Function function, double[] start) throws IOException {
        System.out.println(methodName + ": " + Arrays.toString(method.findMinimum(function, start)));
    }

    private static void setLogger(){


    }


    public static void main(String[] args) throws Exception {

        ClassicNewtonMethod m = new ClassicNewtonMethod();

        m.findMinimumWithLog(new Function2_1(), new double[]{10, 10}, "Function2_1");

    }
}
