import Functions.HandlerForFunctions;
import Functions.QuadraticDiagonalFunction;
import Functions.QuadraticFunction;
import interfaces.Function;
import methods.AbstractGradientMethod;
import methods.ConjugateGradientMethod;
import methods.FastGradientDescentMethod;
import methods.GradientDescentMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) throws IOException {
//        Random random = new Random();
//        for (int SIZE = 100; SIZE <= 100000; SIZE *= 10) {
//            int OBS = 1000;
//            DoubleStream generator = random.doubles(SIZE, 1, OBS);
//            double[] a = generator.sorted().toArray();
//            a[0] = 1;
//            a[SIZE - 1] = OBS;
//            Function f = new QuadraticDiagonalFunction(a, new double[SIZE], 0);
//            double[] x = Arrays.stream(new double[SIZE]).map((d) -> d + 1).toArray();
//            AbstractGradientMethod method = new ConjugateGradientMethod(0.00001);
//            method.findMinimum(f, x);
//            System.out.println(method.counter);
//        }
    }
}