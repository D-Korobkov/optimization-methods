package interfaces;

/**
 * Interface for realisation of R^m function, where m >= 2
 */
public interface Function {
    /**
     * Compute the function meaning in the point given like vector
     * @param x vector that represent point
     * @return meaning of function like f(x)
     */
    double run(double[] x);

    /**
     * Compute gradient of the function in given point
     * @param x vector that represent point
     * @return vector that represent gradient of the function
     */
    double[] runGradient(double[] x);

    /**
     * function that compute (A * x)
     * @param x point for multiply
     * @return result of computing (A * x)
     */
    double[] multiply(double[] x);
}
