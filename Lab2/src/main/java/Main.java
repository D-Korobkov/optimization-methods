import Functions.HandlerForFunctions;
import Functions.QuadraticDiagonalFunction;
import Functions.QuadraticFunction;
import interfaces.Function;
import methods.ConjugateGradientMethod;
import methods.FastGradientDescentMethod;
import methods.GradientDescentMethod;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        HandlerForFunctions handler = new HandlerForFunctions();

        handler.printAll(System.out);


        /*double[][] a = new double[][]{
                {128, 12},
                {12, 128}
        };
        double[] b = new double[]{10, -30};
        double c = 13;
        QuadraticFunction f = new QuadraticFunction(a, b, c);
        /*System.out.println("real = 13; cur = " + f.run(new double[]{0, 0}));
        System.out.println("real = 173; cur = " + f.run(new double[]{1, 1}));
        System.out.println("real = 8.83; cur = " + f.run(new double[]{0.1, -0.24}));
        System.out.println(
                Arrays.toString(
                        new GradientDescentMethod(0.1, 0.00001)
                                .findMinimum(f, new double[]{1, 1})
                )
        );*/
        /*System.out.println(
                Arrays.toString(
                        new FastGradientDescentMethod(0.00001)
                                .findMinimum(f, new double[]{1, 1})
                )
        );*/
        /*System.out.println(
                Arrays.toString(
                        new ConjugateGradientMethod(0.00001)
                                .findMinimum(f, new double[]{1, 1})
                )
        );*/
        double[] aDiag = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] bDiag = new double[]{-1 ,-1, -1, -1, -1, -1, -1, -1, -1, -1};
        double cDiag = 0; // a^2 + a + b^2 + b + ... + j^2 + j
        QuadraticDiagonalFunction fDiag = new QuadraticDiagonalFunction(aDiag, bDiag, cDiag);
//        System.out.println("real = 13; cur = " + fDiag.run(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
//        System.out.println("real = 173; cur = " + fDiag.run(new double[]{1, 1, 1 ,1, 1, 1, 1, 1, 1, 1}));
//        System.out.println("real = 8.83; cur = " + fDiag.run(new double[]{0.1, -0.24}));
        /*System.out.println(
                Arrays.toString(
                        new GradientDescentMethod(0.0001, 0.00001)
                                .findMinimum(fDiag, new double[]{0,0,0,0,0,0,0,0,0,0})
                )
        );
        System.out.println(
                Arrays.toString(
                        new FastGradientDescentMethod(0.00001)
                                .findMinimum(fDiag, new double[]{1, 1, 1 ,1, 1, 1, 1, 1, 1, 1})
                )
        );
        System.out.println(
                Arrays.toString(
                        new ConjugateGradientMethod(0.00001)
                                .findMinimum(fDiag, new double[]{1, 1, 1 ,1, 1, 1, 1, 1, 1, 1})
                )
        );*/

        Function f1 = new QuadraticDiagonalFunction(new double[]{3, 7}, new double[]{0, 1}, 0);
        System.out.println(
                Arrays.toString(
                        new FastGradientDescentMethod(0.00001).findMinimum(f1, new double[]{-1, -2})
                )
        );
    }
}