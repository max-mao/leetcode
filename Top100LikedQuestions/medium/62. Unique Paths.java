//https://leetcode.com/problems/unique-paths/

//A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//        The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
//        How many possible unique paths are there?

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] board = new int[n][m];

        for (int i = 0; i < m; i ++) {
            board[n-1][i] = 1;
        }

        for (int i = 0; i < n; i ++) {
            board[i][m-1] = 1;
        }

        for (int i = n - 2; i >= 0; i --) {
            for (int j = m - 2; j >= 0; j --) {
                board[i][j] = board[i + 1][j] + board[i][j + 1];
            }
        }

        return board[0][0];
    }
}