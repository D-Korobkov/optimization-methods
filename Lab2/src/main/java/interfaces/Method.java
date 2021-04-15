package interfaces;

import java.io.IOException;

/**
 * Interface for searching minimum of the function
 */
public interface Method {
    /**
     * search minimum of the function with start from the point in x_0
     * @param function explores function
     * @param x0 point for the start of exploring
     * @return minimum of the function
     * @throws IOException exception when log from function computing throw exception
     */
    double[] findMinimum(Function function, double[] x0) throws IOException;
}
