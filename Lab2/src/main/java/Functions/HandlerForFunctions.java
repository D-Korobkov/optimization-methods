package Functions;

import interfaces.Function;
import methods.ConjugateGradientMethod;
import methods.FastGradientDescentMethod;
import methods.GradientDescentMethod;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandlerForFunctions {

    private final List<Function> functions;


    public HandlerForFunctions(){
        this.functions = new ArrayList<Function>();

        functions.add(new QuadraticFunction(new double[][]{{2, 2}, {2, 2}}, new double[]{-1, -1}, 0.0));
        functions.add(new QuadraticFunction(new double[][]{{40, 2}, {2, 20}}, new double[]{-1, -1}, 0.0));
        functions.add(new QuadraticFunction(new double[][]{{400, 2}, {2, 100}}, new double[]{-1, -1}, 0.0));

    }

    public void printAll(OutputStream outputStream) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));


        double[] x0 = new double[]{0, 0};
        double epsilon = 0.00001;

        int i = 0;
        for(Function function : functions){
            ConjugateGradientMethod m1 = new ConjugateGradientMethod(epsilon);
            FastGradientDescentMethod m2 = new FastGradientDescentMethod(epsilon);
            GradientDescentMethod m3 = new GradientDescentMethod(2, epsilon);

            double[] x;

            out.write("======Function:" + (i+1) + "\n");

            out.write("==Conjugate Gradient method for : " + Arrays.toString(x0) + " \n");
            x = m1.findMinimum(function, x0);
            out.write("min: " + Arrays.toString(x) + "\n" + "\n");

            out.write("==Gradient Descent method for : " + Arrays.toString(x0) + " \n");
            x = m2.findMinimum(function, x0);
            out.write("min: " + Arrays.toString(x) + "\n" + "\n");

            out.write("==Fast Gradient Descent method for : " + Arrays.toString(x0) + " \n");
            x = m3.findMinimum(function, x0);
            out.write("min: " + Arrays.toString(x) + "\n" + "\n");
            i++;
        }

        out.close();
    }


}
