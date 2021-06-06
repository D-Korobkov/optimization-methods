package TestFunctions;

import interfaces.Function;

import static TestFunctions.Helper.*;

public class Function2_3 implements Function {

    //(x1 + 10 * x2)^2 + 5 * (x3 - x4)^2 + (x2 - 2 * x3)^4 + 10 * (x1 - x4)^4

    @Override
    public double run(double[] x) {
        return sqr(x[0] + 10 * x[1]) + 5 * sqr(x[2] - x[3]) + sqr(sqr(x[1] - 2 * x[2])) + 10 * sqr(sqr(x[0] - x[3]));
    }

    @Override
    public double[] runGradient(double[] x) {
        double[] ans = new double[4];
        ans[0] = findGrad1(x);
        ans[1] = findGrad2(x);
        ans[2] = findGrad3(x);
        ans[3] = findGrad4(x);
        return ans;
    }

    //10 * (-4 * (x1 - x4)^3 + x4 - x3)
    private double findGrad4(double[] x) {
        return 10 * (-4 * cube(x[0] - x[3]) + x[3] - x[2]);
    }

    //10 * (x3 - x4) - 8 * (x2 - 2 * x3)^3
    private double findGrad3(double[] x) {
        return 10 * (x[2] - x[3]) - 8 * cube(x[1] - 2 * x[2]);
    }

    //4 * (5 * (x1 + 10 * x2) + (x2 - 2 * x3)^3)
    private double findGrad2(double[] x) {
        return 4 * (5 * (x[0] + 10 * x[1]) + cube(x[1] - 2 * x[2]));
    }

    //2 * (20 * (x1 - x4)^3 + x1 + 10 * x2)
    private double findGrad1(double[] x) {
        return 2 * (20 * cube(x[0] - x[3]) + x[0] + 10 * x[1]);
    }

    @Override
    public double[] multiply(double[] x) {
        return null;
    }

    @Override
    public double[][] runHessian(double[] x) {
        double[][] H = new double[4][4];

        H[0][0] = findH11(x);
        H[1][1] = findH22(x);
        H[2][2] = findH22(x);
        H[3][3] = findH33(x);

        H[0][1] = findH12(x);
        H[1][0] = findH12(x);

        H[0][2] = findH13(x);
        H[2][0] = findH13(x);

        H[0][3] = findH14(x);
        H[3][0] = findH14(x);

        H[1][2] = findH23(x);
        H[2][1] = findH23(x);

        H[1][3] = findH24(x);
        H[3][1] = findH24(x);
        
        H[2][3] = findH34(x);
        H[3][2] = findH34(x);

        return H;
    }

    //2 * (60 * (x1 - x4)^2 + 1)
    private double findH11(double[] x) {
        return 2 * (60 * sqr(x[0] - x[3]) + 1);
    }

    //20
    private double findH12(double[] x) {
        return 20;
    }

    //0
    private double findH13(double[] x) {
        return 0;
    }

    //-120 * (x1 - x4)^2
    private double findH14(double[] x) {
        return -120 * sqr(x[0] - x[3]);
    }

    //12 * (x2 - 2 * x3)^2 + 200
    private double findH22(double[] x) {
        return 12 * sqr((x[1] - 2 * x[2]) + 200);
    }

    //-24 * (x2 - 2 * x3)^2
    private double findH23(double[] x) {
        return -24 * sqr(x[1] - 2 * x[2]);
    }

    //0
    private double findH24(double[] x) {
        return 0;
    }

    //48 * (x2 - 2 * x3)^2 + 10
    private double findH33(double[] x) {
        return 48 * sqr(x[1] - 2 * x[2]) + 10;
    }

    //-10
    private double findH34(double[] x) {
        return -10;
    }

    //120 * (x1 - x4)^2 + 10
    private double findH44(double[] x) {
        return 120 * sqr(x[0] - x[3]) + 10;
    }

}
