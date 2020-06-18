//https://leetcode.com/problems/string-to-integer-atoi/
//Implement atoi which converts a string to an integer.
//
//        The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
//
//        The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
//
//        If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
//
//        If no valid conversion could be performed, a zero value is returned.
//
//        Note:
//
//        Only the space character ' ' is considered as whitespace character.
//        Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
//        Example 1:
//
//        Input: "42"
//        Output: 42

class Solution {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        int i = 0;
        int result = 0;
        int sign = 1;
        if (str.charAt(i) == '-') {
            sign = -1;
            i ++;
        }else if (str.charAt(i) == '+') {
            sign = 1;
            i ++;
        }
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int digit = str.charAt(i) - '0';

            // 这里是精髓，检查加上当前数字是不是会超过Integer.MAX_VALUE
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i ++;
        }

        return sign * result;
    }
}