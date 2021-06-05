import NewtonMethods.ClassicNewtonMethod;
import NewtonMethods.LinarySearchNewtonMethod;
import NewtonMethods.NewtonMethodWithDescentDirection;
import NewtonMethods.marquardt.MarquardtMethodVersion1;
import NewtonMethods.marquardt.MarquardtMethodVersion2;
import TestFunctions.*;
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
        //m.findMinimumWithLog(new Function1_1(), new double[]{4, 1}, "Function1_1");
        //m.findMinimumWithLog(new Function1_2(), new double[]{-1.2, 1}, "Function1_2");
        //m.findMinimumWithLog(new Function1_1_1(), new double[]{-10, -10}, "Function1_1_1");
        //m.findMinimumWithLog(new Function1_1_2(), new double[]{-10, -10}, "Function1_1_2");
        //m.findMinimumWithLog(new Function1_1_3(), new double[]{-10, -10}, "Function1_1_3");

        LinarySearchNewtonMethod m2 = new LinarySearchNewtonMethod();
        //m2.findMinimumWithLog(new Function1_1(), new double[]{4, 1}, "Function1_1");
        //m2.findMinimumWithLog(new Function1_2(), new double[]{-1.2, 1}, "Function1_2");
        //m2.findMinimumWithLog(new Function1_1_1(), new double[]{-10, -10}, "Function1_1_1");
        //m2.findMinimumWithLog(new Function1_1_2(), new double[]{-10, -10}, "Function1_1_2");
        //m2.findMinimumWithLog(new Function1_1_3(), new double[]{-10, -10}, "Function1_1_3");

        NewtonMethodWithDescentDirection m3 = new NewtonMethodWithDescentDirection();
        //m3.findMinimumWithLog(new Function1_1(), new double[]{4, 1}, "Function1_1");
        //m3.findMinimumWithLog(new Function1_2(), new double[]{-1.2, 1}, "Function1_2");
        //m3.findMinimumWithLog(new Function1_1_1(), new double[]{-10, -10}, "Function1_1_1");
        //m3.findMinimumWithLog(new Function1_1_2(), new double[]{-10, -10}, "Function1_1_2");
        //m3.findMinimumWithLog(new Function1_1_3(), new double[]{-10, -10}, "Function1_1_3");
        double[] startBonus = new double[100];
        MarquardtMethodVersion1 marq1 = new MarquardtMethodVersion1();
        MarquardtMethodVersion2 marq2 = new MarquardtMethodVersion2();
        Arrays.fill(startBonus, 5);
        //m.findMinimumWithLog(new FunctionBonus(), startBonus, "FunctionBonus");
        //marq1.findMinimumWithLog(new FunctionBonus(), startBonus, "FunctionBonus");
        //marq2.findMinimumWithLog(new FunctionBonus(), startBonus, "FunctionBonus");
        m2.findMinimumWithLog(new FunctionBonus(), startBonus, "FunctionBonus");

    }
}
