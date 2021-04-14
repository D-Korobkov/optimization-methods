package interfaces;

import SaZhaK.Matrix;

import java.io.IOException;
import java.util.Vector;

public interface Method {

    public double[] findMinimum(Function function, double[] x0) throws IOException;

}
