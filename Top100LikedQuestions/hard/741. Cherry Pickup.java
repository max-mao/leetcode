//https://leetcode.com/problems/cherry-pickup/
//In a N x N grid representing a field of cherries, each cell is one of three possible integers.
//
//
//
//0 means the cell is empty, so you can pass through;
//1 means the cell contains a cherry, that you can pick up and pass through;
//-1 means the cell contains a thorn that blocks your way.
//
//
//Your task is to collect maximum number of cherries possible by following the rules below:
//
//
//
//Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
//After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
//When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
//If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
//

//solution: https://www.youtube.com/watch?v=q3-6jaNvj6c
class Solution {
    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int[][][] dp = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j =0; j < n; j++) {
                for (int k =0; k < n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return Math.max(0, helper(grid, 0, 0, 0, dp));
    }

    private int helper(int[][] grid, int r, int c1, int c2, int[][][] dp) {
        int n = grid.length;
        int r2 = r + c1 - c2;
        if (r == n-1 && c1 == n-1) {
            return grid[n-1][n-1];
        }
        if (r == n || c1 == n || c2 == n || r2 == n || grid[r][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }
        if (dp[r][c1][c2] != -1) {
            return dp[r][c1][c2];
        }
        int res = 0;
        res += grid[r][c1];
        if (c1 != c2) {
            res += grid[r2][c2];
        }
        int temp = Math.max(helper(grid, r +1, c1, c2, dp), helper(grid, r+1, c1, c2+1, dp));
        temp = Math.max(temp, helper(grid, r, c1+1, c2, dp));
        temp = Math.max(temp, helper(grid, r, c1+1, c2+1, dp));
        res += temp;
        dp[r][c1][c2] = res;
        return res;
    }
}