//https://leetcode.com/problems/powx-n/
//Implement pow(x, n), which calculates x raised to the power n (xn).
//
//        Example 1:
//
//        Input: 2.00000, 10
//        Output: 1024.00000

//solution fast pow:
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        if (n < 0) {
            x = 1 /x;
            n = -n;
        }

        return fastPow(x, n);
    }

    private double fastPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        double half = fastPow(x, n /2);
        if (n % 2 == 0) {
            return half * half;
        }

        return half * half * x;
    }
}