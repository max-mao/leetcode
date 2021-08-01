//https://leetcode.com/problems/path-with-minimum-effort/
//You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
//
//A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
//
//
//Example 1:
//
//
//
//Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//Output: 2
//Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

//solution https://www.youtube.com/watch?v=CPSJ0oFBMHg&t=6s
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int start = 0;
        int end = Integer.MAX_VALUE;

        int m = heights.length;
        int n = heights[0].length;


        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (dfs(0, 0, mid, heights, new boolean[m][n])) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (dfs(0, 0, start, heights, new boolean[m][n])) {
            return start;
        }
        return end;
    }

    private boolean dfs(int x, int y, int target, int[][] heights,  boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;

        if (x == m-1 && y == n-1) {
            return true;
        }
        visited[x][y] = true;
        boolean res = false;
        if (x < m - 1 && Math.abs(heights[x+1][y] - heights[x][y]) <= target && !visited[x+1][y]) {
            res = res || dfs(x+1, y, target, heights, visited);
        }
        if (x > 0 && Math.abs(heights[x-1][y] - heights[x][y]) <= target && !visited[x-1][y]) {
            res = res || dfs(x-1, y, target, heights, visited);
        }
        if (y < n - 1 && Math.abs(heights[x][y+1] - heights[x][y]) <= target && !visited[x][y+1]) {
            res = res || dfs(x, y+1, target, heights, visited);
        }
        if (y > 0 && Math.abs(heights[x][y-1] - heights[x][y]) <= target && !visited[x][y-1]) {
            res = res || dfs(x, y-1, target, heights, visited);
        }

        return res;
    }
}