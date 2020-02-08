//https://leetcode.com/problems/palindromic-substrings/
//Given a string, your task is to count how many palindromic substrings in this string.
//
//        The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
//
//        Example 1:
//
//        Input: "abc"
//        Output: 3
//        Explanation: Three palindromic strings: "a", "b", "c".
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i=0; i < s.length(); i ++) {
            count += helper(s, i, i);
            count += helper(s, i, i+1);
        }

        return count;
    }

    private int helper(String s, int left, int right) {
        int result = 0;
        if (left < 0 || right > s.length() -1) {
            return result;
        }

        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                result ++;
                left --;
                right++;
            } else {
                break;
            }
        }
        return result;
    }
}