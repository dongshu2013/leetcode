package edu.northwestern.shu;

public class PowerX {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            x = 1 / x;
            return x * myPow(x, -(n + 1));
        }

        return n % 2 == 0 ? myPow(x*x, n/2) : x * myPow(x*x, n/2);
    }

    public static void main(String[] args) {
    	PowerX p = new PowerX();
    	System.out.println(p.myPow(2.00000, -2147483648));
    }
}