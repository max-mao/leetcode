//https://leetcode.com/problems/generate-parentheses/
//        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

//        For example, given n = 3, a solution set is:
//
//        [
//        "((()))",
//        "(()())",
//        "(())()",
//        "()(())",
//        "()()()"
//        ]

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) {
            return result;
        }
        helper(n, result, "", 0, 0);
        return result;
    }

    private void helper(int n, List<String> result, String s, int left, int right) {
        if (s.length() == n * 2) {
            result.add(s);
            return;
        }

        if (right < left) {
            helper (n, result, s + ')', left, right + 1);
        }

        if (left < n) {
            helper (n, result, s + '(', left + 1, right);
        }
    }
}