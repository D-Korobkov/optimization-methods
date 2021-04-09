import Functions.QuadraticFunction;

public class Main {
    public static void main(String[] args) {
        double[][] a = new double[][]{
                {8, 6},
                {6, 8}
        };
        double[] b = new double[]{-10, 30};
        double c = 13;
        QuadraticFunction f = new QuadraticFunction(a, b, c);
        System.out.println(f.run(new double[]{0.1, -0.24}));
        /*System.out.println(
                Arrays.toString(
                        new GradientDescentMethod(0.1, 0.00001)
                                .findMinimum(f, new double[]{1, 1})
                )
        );*/
    }
}
