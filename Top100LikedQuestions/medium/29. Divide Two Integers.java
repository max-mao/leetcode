//https://leetcode.com/problems/divide-two-integers/
//Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
//
//        Return the quotient after dividing dividend by divisor.
//
//        The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
//
//        Example 1:
//
//        Input: dividend = 10, divisor = 3
//        Output: 3
//        Explanation: 10/3 = truncate(3.33333..) = 3.

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = 0;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);

        while (a - b >= 0) {
            int x = 0;
            while (a - (b << 1 << x) >= 0) {
                x ++;
            }
            result += 1 << x;
            a -= b << x;
        }

        return (dividend > 0) == (divisor > 0) ? result : - result;
    }
}