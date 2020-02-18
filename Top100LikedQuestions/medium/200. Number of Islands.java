//https://leetcode.com/problems/number-of-islands/
//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//        Example 1:
//
//        Input:
//        11110
//        11010
//        11000
//        00000
//
//        Output: 1

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count ++;
                    makeZero(grid, i, j);
                }
            }
        }

        return count;
    }

    private void makeZero(char[][] grid, int i, int j) {
        if (i < 0 || i > grid.length -1 || j < 0 || j > grid[0].length -1) {
            return;
        }

        if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        makeZero(grid, i + 1, j);
        makeZero(grid, i - 1, j);
        makeZero(grid, i, j - 1);
        makeZero(grid, i, j + 1);
    }
}