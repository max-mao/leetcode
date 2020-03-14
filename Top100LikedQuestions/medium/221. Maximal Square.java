//https://leetcode.com/problems/maximal-square/
//Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
//
//        Example:
//
//        Input:
//
//        1 0 1 0 0
//        1 0 1 1 1
//        1 1 1 1 1
//        1 0 0 1 0
//
//        Output: 4

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int max_len = 0;
        for (int i =0; i < matrix.length; i ++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max_len = 1;
            } else {
                dp[i][0] = 0;
            }
        }

        for (int i =0; i < matrix[0].length; i ++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                max_len = 1;
            } else {
                dp[0][i] = 0;
            }
        }

        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int min = Math.min(dp[i-1][j-1], dp[i-1][j]);
                    min = Math.min(min, dp[i][j-1]);
                    dp[i][j] = min+1;
                    max_len = Math.max(min+1, max_len);
                }
            }
        }

        return max_len * max_len;

    }
}