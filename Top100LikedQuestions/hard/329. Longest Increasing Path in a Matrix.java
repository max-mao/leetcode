//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
//Given an integer matrix, find the length of the longest increasing path.
//
//        From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
//        Example 1:
//
//        Input: nums =
//        [
//        [9,9,4],
//        [6,6,8],
//        [2,1,1]
//        ]
//        Output: 4
//        Explanation: The longest increasing path is [1, 2, 6, 9].

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int result = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                int cur = find(i, j, matrix, dp);
                result = Math.max(result, cur);
            }
        }

        return result;
    }

    private int find(int i, int j, int[][] matrix, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int[][] dirs = new int[][] {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
        int res = 0;
        for (int[] dir : dirs) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];

            if (new_i >= 0 && new_i < matrix.length && new_j >=0 && new_j < matrix[0].length
                    &&  matrix[new_i][new_j] > matrix[i][j]) {
                res = Math.max(res, find(new_i, new_j, matrix, dp));
            }
        }
        res ++;
        dp[i][j] = res;
        return res;
    }
}