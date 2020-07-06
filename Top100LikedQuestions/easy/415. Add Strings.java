//https://leetcode.com/problems/add-strings/Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
//
//Note:
//
//The length of both num1 and num2 is < 5100.
//Both num1 and num2 contains only digits 0-9.
//Both num1 and num2 does not contain any leading zero.
//You must not use any built-in BigInteger library or convert the inputs to integer directly.
class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }

        StringBuilder sb = new StringBuilder();
        int a_start = num1.length() - 1;
        int b_start = num2.length() - 1;

        int carry = 0;
        while(a_start >= 0 && b_start >= 0) {
            int cur = num1.charAt(a_start) - '0' + num2.charAt(b_start) - '0' + carry;
            int digit = cur % 10;
            carry = cur / 10;
            sb.insert(0, digit);
            a_start --;
            b_start --;
        }

        while (a_start >= 0) {
            int cur = num1.charAt(a_start) - '0' + carry;
            int digit = cur % 10;
            carry = cur / 10;
            sb.insert(0, digit);
            a_start --;
        }

        while (b_start >= 0) {
            int cur = num2.charAt(b_start) - '0' + carry;
            int digit = cur % 10;
            carry = cur / 10;
            sb.insert(0, digit);
            b_start --;
        }

        if (carry != 0) {
            sb.insert(0, carry);
        }

        return sb.toString();
    }
}