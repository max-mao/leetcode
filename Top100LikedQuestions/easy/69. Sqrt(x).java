//https://leetcode.com/problems/sqrtx/
//Implement int sqrt(int x).
//
//        Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
//
//        Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
//
//        Example 1:
//
//        Input: 4
//        Output: 2

class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;
        int start = 0;
        int end = x;

        long num;
        while (start <= end) {
            int mid = start + (end - start) /2;
            num = (long) mid * mid;
            if (num > x) {
                end = mid-1;
            } else if (num < x) {
                start = mid+1;
            } else return mid;
        }

        return end;
    }
}