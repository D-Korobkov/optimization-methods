package methods;

import interfaces.Method;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public abstract class LoggingMethod implements Method {

    private final boolean log;
    private final Writer out;
    private double[] prevX = new double[]{0, 0};


    protected LoggingMethod(boolean log, String fileName) throws FileNotFoundException {
        this.log = log;
        this.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
    }

    protected LoggingMethod(boolean log) {
        this.log = log;
        this.out = null;
    }

    protected void log(double[] x, double[] x1) throws IOException {
        if(!log) return;
        assert out != null;
        //out.write(Arrays.toString(x) + ":" + Arrays.toString(gradient) + "\n");
        out.write(String.format(Locale.US, "Vector((%f, %f), (%f, %f))\n", prevX[0], prevX[1], x[0], x[1]));
        out.flush();
        prevX = x;
    }
}
