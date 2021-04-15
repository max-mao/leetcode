//https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
//Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
//
//Formally, a parentheses string is valid if and only if:
//
//It is the empty string, or
//It can be written as AB (A concatenated with B), where A and B are valid strings, or
//It can be written as (A), where A is a valid string.
//Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
//
//
//
//Example 1:
//
//Input: "())"
//Output: 1
class Solution {
    public int minAddToMakeValid(String S) {
        int res = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                left++;
            } else if (S.charAt(i) == ')') {
                right++;
            }
            if (right > left) {
                res += right -left;
                right = 0;
                left = 0;
            }
        }
        if (left > right) {
            res += left - right;
        }

        return res;
    }

}
