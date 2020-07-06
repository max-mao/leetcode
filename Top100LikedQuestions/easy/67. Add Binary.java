//https://leetcode.com/problems/add-binary/
//Given two binary strings, return their sum (also a binary string).
//
//        The input strings are both non-empty and contains only characters 1 or 0.
//
//        Example 1:
//
//        Input: a = "11", b = "1"
//        Output: "100"
//        Example 2:
//
//        Input: a = "1010", b = "1011"
//        Output: "10101"

class Solution {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }

        StringBuilder sb = new StringBuilder();
        int a_start = a.length() - 1;
        int b_start = b.length() - 1;

        int carry = 0;
        while(a_start >= 0 && b_start >= 0) {
            int cur = a.charAt(a_start) - '0' + b.charAt(b_start) - '0' + carry;
            int digit = cur % 2;
            carry = cur /2;
            sb.insert(0, digit);
            a_start --;
            b_start --;
        }

        while (a_start >= 0) {
            int cur = a.charAt(a_start) - '0' + carry;
            int digit = cur % 2;
            carry = cur /2;
            sb.insert(0, digit);
            a_start --;
        }

        while (b_start >= 0) {
            int cur = b.charAt(b_start) - '0' + carry;
            int digit = cur % 2;
            carry = cur /2;
            sb.insert(0, digit);
            b_start --;
        }

        if (carry != 0) {
            sb.insert(0, carry);
        }

        return sb.toString();
    }
}