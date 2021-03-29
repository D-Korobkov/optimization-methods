package interfaces;

import SaZhaK.Matrix;

public interface XYFunction {
    double run(double[] x);
    double runGradient(double[] x);
}
