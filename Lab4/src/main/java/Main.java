import NewtonMethods.ClassicNewtonMethod;
import QuasiNewton.BFGS;
import QuasiNewton.PowellMethod;
import TestFunctions.*;
import gauss.GaussSolver;
import interfaces.Function;
import interfaces.Method;
import logger.MathLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    public static final double EPS = 0.0000001;


    private static void test(String methodName, Method method, Function function, double[] start) throws IOException {
        System.out.println(methodName + ": " + Arrays.toString(method.findMinimum(function, start)));
    }

    private static void setLogger(){


    }


    public static void main(String[] args) throws Exception {

        MathLogger logger = new MathLogger(Path.of("logger/main"), List.of("test1", "test2"));

        logger.log("general test");
        logger.log("test1", "test1  test");
        logger.log("test2", "test2 test");

        logger.close();

    }
}
