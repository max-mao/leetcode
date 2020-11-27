//https://leetcode.com/problems/cherry-pickup-ii/
//Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
//
//You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
//
//Return the maximum number of cherries collection using both robots  by following the rules below:
//
//From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
//When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
//When both robots stay on the same cell, only one of them takes the cherries.
//Both robots cannot move outside of the grid at any moment.
//Both robots should reach the bottom row in the grid.
//
//
//Example 1:
//
//
//
//Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
//Output: 24
//Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
//Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
//Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
//Total of cherries: 12 + 12 = 24.

class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        for (int i = 0; i < m; i ++) {
            for (int j =0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return helper(grid, m, n, 0, 0, n-1, dp);
    }

    //helper function compute Cherry Pickup sum of
    //the robot1 from r, c1 and robot2 from r, c2 to the last row
    private int helper(int[][] grid, int m, int n, int r, int c1, int c2, int[][][] dp) {
        //reach to the last row
        if (r == m) {
            return 0;
        }
        // out of boundery
        if (c1 < 0 || c1 >= n || c2 < 0 || c2 >= n) {
            return 0;
        }
        if (dp[r][c1][c2] != -1) {
            return dp[r][c1][c2];
        }
        int res = 0;
        for (int i= -1; i <=1; i++) {
            for (int j = -1; j <=1; j++) {
                res = Math.max(res, helper(grid, m, n, r+1, c1+i, c2+j, dp));
            }
        }
        if (c1 == c2) {
            res += grid[r][c1];
        } else {
            res += grid[r][c1] + grid[r][c2];
        }
        dp[r][c1][c2] = res;
        return res;
    }
}
