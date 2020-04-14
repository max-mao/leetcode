//https://leetcode.com/problems/wildcard-matching/
//Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
//
//        '?' Matches any single character.
//        '*' Matches any sequence of characters (including the empty sequence).
//        The matching should cover the entire input string (not partial).
//
//        Note:
//
//        s could be empty and contains only lowercase letters a-z.
//        p could be empty and contains only lowercase letters a-z, and characters like ? or *.

//DP: https://www.youtube.com/watch?v=3ZDZ-N0EPV0
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;

        // 去除x****y 中多余的*， 使之变成x*y，例如： a****b**c -> a*b*c
        while (index < p.length()) {
            if (p.charAt(index) != '*') {
                sb.append(p.charAt(index));
                index ++;
            } else {
                sb.append(p.charAt(index));
                index ++;
                while (index < p.length() && p.charAt(index) == '*') {
                    index ++;
                }
            }
        }

        p = sb.toString();

        //dp[i][j] 表示string 前i个字符匹配pattern 前j个字符
        boolean[][] dp = new boolean[s.length() +1][p.length() +1];
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = false;
        }

        // 当pattern第一个字符是*， 空字符和 * 匹配。
        for (int i = 1; i < dp[0].length; i++) {
            if (i == 1 && p.charAt(i-1) == '*') {
                dp[0][i] = true;
            } else {
                dp[0][i] = false;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //当string 第i和 pattern第j 字符匹配时候，看dp[i-1][j-1]
                if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if ( p.charAt(j-1) == '*') {
                    //当pattern 第j字符是* 时候，*可以不匹配，即dp[i][j-1], 或者匹配一或者多个字符，即dp[i-1][j]
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}