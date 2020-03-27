//https://leetcode.com/problems/longest-palindromic-substring/

//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
//        Example 1:
//
//        Input: "babad"
//        Output: "bab"
//        Note: "aba" is also a valid answer.

//中心线枚举法 solution1：
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() ==0) {
            return s;
        }

        int max_even = -1;
        int index_even = -1;
        for (int i =0; i < s.length(); i ++) {
            if (findLen(s, i, i) > max_even) {
                max_even = findLen(s, i, i);
                index_even = i;
            }
        }

        int max_odd = -1;
        int index_odd = -1;
        for (int i =0; i < s.length(); i ++) {
            if (findLen(s, i, i+1) > max_odd) {
                max_odd = findLen(s, i, i+1);
                index_odd = i;
            }
        }

        if (max_even > max_odd) {
            return s.substring(index_even - max_even/2, index_even + max_even/2 +1);
        }

        return s.substring(index_odd - max_odd/2 + 1, index_odd + max_odd/2 + 1);

    }

    private int findLen(String str, int left, int right) {
        if (left < 0 || right > str.length() -1) {
            return 0;
        }
        int result = 0;
        if (left == right) {
            result ++;
            left --;
            right ++;
        }

        while (left >= 0 && right <= str.length()-1) {
            if (str.charAt(left) != str.charAt(right)) {
                return result;
            }
            result += 2;
            left --;
            right ++;
        }

        return result;
    }
}


//DP solution2：