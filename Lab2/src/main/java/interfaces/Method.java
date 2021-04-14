package interfaces;

import java.io.IOException;

public interface Method {
    double[] findMinimum(Function function, double[] x0) throws IOException;
}
