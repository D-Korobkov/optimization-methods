package interfaces;

import SaZhaK.Matrix;

public interface Function {
    double run(double[] x);
    double[] runGradient(double[] x);
}
