package search;

import interfaces.MathFunction;
import interfaces.Strategy;

public class BrentSearch extends AbstractSearch {

    protected static final double K = (3- Math.sqrt(5))/2;
    protected final double epsilon;

    public BrentSearch(MathFunction function, double leftBorder, double rightBorder, double epsilon) {
        super(function, leftBorder, rightBorder);
        this.epsilon = epsilon;


    }
    @Override
    public double searchMinimum() {
        double a = leftBorder;
        double c = rightBorder;
        double x = a + K*(c-a);
        double w = x;
        double v = x;
        double fx = function.run(x);
        double fw = fx;
        double fv = fx;

        double d = (c - a);
        double e = d;

        double g = e;
        double tol = 0;

        while (true) {

            g = e;
            e = d;
            double u = 0;
            boolean uIsGood = false;

            tol = epsilon * Math.abs(x) + epsilon / 10.0;
            if(Math.abs(x - (a+c)/2) + (c-a)/2 <= 2*tol){
                break;
            }
            if(x != w && x != v && w != v && fx != fv && fx != fw && fv != fw){
                //TODO: find u (min of par on v,x,w)

                if( a <= u && u <= c && Math.abs(u - x) < g/2){
                    //accept u
                    uIsGood = true;
                    if( u - a < 2*tol || c - u < 2*tol){
                        u = x - Math.signum(x - (a + c)/2)*tol;
                    }
                }
            }
            if(!uIsGood){
                if(x < (a + c)/2){
                    u = x + K*(c - x);
                    e = c - x;
                } else {
                    u = x - K*(x - a);
                    e = x - a;
                }
            }
            if(Math.abs(u - x) < tol){
                u = x + Math.signum(u-x)*tol;
            }
            d = Math.abs(u - x);

            double fu = function.run(u);

            if(fu <= fx){
                if(u >= x){
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
            } else{
                if(u >= x){
                    c = u;
                } else {
                    a = u;
                }
                if(fu <= fw || w == x){
                    v = w;
                    w = u;
                    fv = fw;
                    fw = fu;
                } else if(fu <= fv || v == x || v == w){
                    v = u;
                    fv = fu;
                }

            }

        }

        return x;


    }


    // K=(3− 5)/2,x=w=v=a+K(c−a),fx =fw =fv =f(x);

}