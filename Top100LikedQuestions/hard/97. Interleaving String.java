//https://leetcode.com/problems/interleaving-string/

//Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//
//        Example 1:
//
//        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//        Output: true

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        for (int i=0; i < dp.length; i++) {
            dp[i][0] = s1.substring(0,i).equals(s3.substring(0, i));
        }

        for (int i=0; i < dp[0].length; i++) {
            dp[0][i] = s2.substring(0, i).equals(s3.substring(0, i));
        }

        for (int i =1; i < dp.length; i++) {
            for (int j= 1; j < dp[0].length; j++) {
                if (s1.charAt(i-1) == s3.charAt(i+j-1)) {
                    dp[i][j] = dp[i-1][j] || dp[i][j];
                }
                if (s2.charAt(j-1) == s3.charAt(i+j-1)) {
                    dp[i][j] = dp[i][j-1] || dp[i][j];
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}