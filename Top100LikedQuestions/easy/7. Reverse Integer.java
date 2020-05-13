//https://leetcode.com/problems/reverse-integer/
//Given a 32-bit signed integer, reverse digits of an integer.
//
//        Example 1:
//
//        Input: 123
//        Output: 321

class Solution {
    public int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -1* x;
        }

        int result = 0;
        while (x != 0) {
            if (Integer.MAX_VALUE /10 < result || Integer.MAX_VALUE - x % 10 < result * 10) {
                return 0;
            }
            result = result * 10;
            int digit = x % 10;
            result += digit;
            x = x / 10;
        }

        if (isNegative) {
            result = -1 * result;
        }

        return result;
    }
}