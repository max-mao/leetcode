//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
//Given a string s of '(' , ')' and lowercase English characters.
//
//        Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
//
//        Formally, a parentheses string is valid if and only if:
//
//        It is the empty string, contains only lowercase characters, or
//        It can be written as AB (A concatenated with B), where A and B are valid strings, or
//        It can be written as (A), where A is a valid string.
//
//
//        Example 1:
//
//        Input: s = "lee(t(c)o)de)"
//        Output: "lee(t(c)o)de"
//        Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
//        Example 2:
//
//        Input: s = "a)b(c)d"
//        Output: "ab(c)d"

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        String[] strs = s.split("");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("(")) {
                stack.push(i);
            } else if (strs[i].equals(")")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    strs[i] = "";
                }
            }
        }

        while (!stack.isEmpty()) {
            int left = stack.pop();
            strs[left] = "";
        }

        return String.join("", strs);
    }
}