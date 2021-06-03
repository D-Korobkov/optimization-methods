package interfaces;

/**
 * интерфейс для классов, решающих СЛАУ
 */
public interface Solver {

    /**
     * решает СЛАУ
     * @param A матрица коэффициентов
     * @param B результирующий вектор
     * @return решение СЛАУ
     */
    double[] solve(double[][] A, double[] B);

}
