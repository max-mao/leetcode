//https://leetcode.com/problems/edit-distance/
//Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
//
//        You have the following 3 operations permitted on a word:
//
//        Insert a character
//        Delete a character
//        Replace a character
//        Example 1:
//
//        Input: word1 = "horse", word2 = "ros"
//        Output: 3
//        Explanation:
//        horse -> rorse (replace 'h' with 'r')
//        rorse -> rose (remove 'r')
//        rose -> ros (remove 'e')


class Solution {
    public int minDistance(String word1, String word2) {
        //dp[i][j] 表示word1前i个字符变成word2前j个字符需要的min distance
        int[][] dp = new int[word1.length()+1][word2.length()+1];

        for(int i=0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for(int i=0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j] +1),  dp[i][j-1] +1);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1] +1, dp[i-1][j] +1),  dp[i][j-1] +1);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}