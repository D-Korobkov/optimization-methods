package methods;

import interfaces.Method;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public abstract class LoggingMethod implements Method {

    private final boolean log;
    private final Writer out;


    protected LoggingMethod(boolean log, String fileName) throws FileNotFoundException {
        this.log = log;
        this.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
    }

    protected LoggingMethod(boolean log) {
        this.log = log;
        this.out = null;
    }

    protected void log(double[] x, double[] gradient) throws IOException {
        if(!log) return;
        assert out != null;
        //out.write(Arrays.toString(x) + ":" + Arrays.toString(gradient) + "\n");
        out.write(String.format(Locale.US, "(%f, %f)\n", x[0], x[1]));
        out.flush();
    }
}
