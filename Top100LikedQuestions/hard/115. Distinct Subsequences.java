//https://leetcode.com/problems/distinct-subsequences/
//Given a string S and a string T, count the number of distinct subsequences of S which equals T.
//
//        A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
//        Example 1:
//
//        Input: S = "rabbbit", T = "rabbit"
//        Output: 3
//        Explanation:
//
//        As shown below, there are 3 ways you can generate "rabbit" from S.
//        (The caret symbol ^ means the chosen letters)
//
//        rabbbit
//        ^^^^ ^^
//        rabbbit
//        ^^ ^^^^
//        rabbbit
//        ^^^ ^^^

class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() < t.length()) {
            return 0;
        }

        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i =0; i < dp.length; i++) {
            dp[i][0] = 1;
        }


        for (int i=1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j ++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}