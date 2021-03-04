package search;

import interfaces.MathFunction;
import interfaces.Strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.KeyPair;

public class BrentSearch extends AbstractSearch {

    protected static final double K = (3 - Math.sqrt(5)) / 2;
    protected final double epsilon;
    private BufferedWriter out = null;

    public BrentSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder);
        this.epsilon = epsilon;
        try {
            this.out = new BufferedWriter(new FileWriter(new File("out.txt")));
        } catch (Exception ignored){

        }

    }

    @Override
    public double searchMinimum() {
        double a = leftBorder;
        double c = rightBorder;
        double x = a + K * (c - a);
        double w = x;
        double v = x;
        double fx = function.run(x);
        double fw = fx;
        double fv = fx;

        double d = (c - a);
        double e = d;

        double g = e;
        double tol = 0;
        double lastLen = c - a;

        log(a, c, x, w, v, -1, fx, fw, fv, -1, lastLen, false);

        while (true) {

            lastLen = c - a;
            g = e;
            e = d;
            double u = 0;
            boolean uIsGood = false;

            tol = epsilon * Math.abs(x) + epsilon / 10.0;
            if (Math.abs(x - (a + c) / 2) + (c - a) / 2 <= 2 * tol) {
                break;
            }
            if (x != w && x != v && w != v && fx != fv && fx != fw && fv != fw) {
                //find u (min of par on v,x,w)

                u = findMinParNoOrder(v, x, w, fv, fx, fw);

                if (a <= u && u <= c && Math.abs(u - x) < g / 2) {
                    //accept u
                    uIsGood = true;
                    if (u - a < 2 * tol || c - u < 2 * tol) {
                        u = x - Math.signum(x - (a + c) / 2) * tol;
                    }
                }
            }
            if (!uIsGood) {
                if (x < (a + c) / 2) {
                    u = x + K * (c - x);
                    e = c - x;
                } else {
                    u = x - K * (x - a);
                    e = x - a;
                }
            }
            if (Math.abs(u - x) < tol) {
                u = x + Math.signum(u - x) * tol;
            }
            d = Math.abs(u - x);

            double fu = function.run(u);

            if (fu <= fx) {
                if (u >= x) {
                    a = x;
                } else {
                    c = x;
                }
                v = w;
                w = x;
                x = u;
                fv = fw;
                fw = fx;
                fx = fu;
            } else {
                if (u >= x) {
                    c = u;
                } else {
                    a = u;
                }
                if (fu <= fw || w == x) {
                    v = w;
                    w = u;
                    fv = fw;
                    fw = fu;
                } else if (fu <= fv || v == x || v == w) {
                    v = u;
                    fv = fu;
                }

            }
            log(a, c, x, w, v, u, fx, fw, fv, fu, lastLen, uIsGood);
        }

        return x;


    }

    //как сделать короче без создания классов - хз, будет так
    private double findMinParNoOrder(double x1, double x2, double x3, double f1, double f2, double f3) {
        if (x1 < x2 && x2 < x3) {
            return findMinPar(x1, x2, x3, f1, f2, f3);
        }
        if (x3 < x2 && x2 < x1) {
            return findMinPar(x3, x2, x1, f3, f2, f1);
        }
        if (x2 < x1 && x1 < x3) {
            return findMinPar(x2, x1, x3, f2, f1, f3);
        }
        if (x3 < x1 && x1 < x2) {
            return findMinPar(x3, x1, x2, f3, f1, f2);
        }
        if (x1 < x3 && x3 < x2) {
            return findMinPar(x1, x3, x2, f1, f3, f2);
        }
        if (x2 < x3 && x3 < x1) {
            return findMinPar(x2, x3, x1, f2, f3, f1);
        }
        return -1;
    }

    //формула из метода парабол
    private double findMinPar(double x1, double x2, double x3, double f1, double f2, double f3) {
        double a1 = (f2 - f1) / (x2 - x1);
        double a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);
        return (x1 + x2 - a1 / a2) / 2;
    }

    private void log(double a, double c, double x, double w, double v, double u, double fx, double fw, double fv, double fu, double lastLen, boolean methodFlag){

        try {
            out.write(String.format("[%f;%f] %f %f;%f %f;%f %f;%f %f;%f %b\n", a, c, (c-a)/lastLen, v, w, fv, fw, x, u, fx, fu, methodFlag));
            out.flush();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}