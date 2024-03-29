//https://leetcode.com/problems/max-area-of-island/
//
//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
//        Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
//
//        Example 1:
//
//        [[0,0,1,0,0,0,0,1,0,0,0,0,0],
//        [0,0,0,0,0,0,0,1,1,1,0,0,0],
//        [0,1,1,0,1,0,0,0,0,0,0,0,0],
//        [0,1,0,0,1,1,0,0,1,0,1,0,0],
//        [0,1,0,0,1,1,0,0,1,1,1,0,0],
//        [0,0,0,0,0,0,0,0,0,0,1,0,0],
//        [0,0,0,0,0,0,0,1,1,1,0,0,0],
//        [0,0,0,0,0,0,0,1,1,0,0,0,0]]
//        Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
//        Example 2:
//
//        [[0,0,0,0,0,0,0,0]]

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, compute(grid, i, j));
                }
            }
        }

        return res;
    }

    private int compute(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length) {
            return 0;
        }
        if (j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        res += compute(grid, i+1, j);
        res += compute(grid, i-1, j);
        res += compute(grid, i, j+1);
        res += compute(grid, i, j-1);

        return res;
    }
}