//https://leetcode.com/problems/path-with-maximum-minimum-value/
//Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
//
//The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
//
//A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
//
//
//
//Example 1:
//
//
//
//Input: [[5,4,5],[1,2,6],[7,4,6]]
//Output: 4
//Explanation:
//The path with the maximum score is highlighted in yellow.

class Solution {
    public int maximumMinimumPath(int[][] A) {
        List<Integer> list = new ArrayList<>();
        int m = A.length;
        int n = A[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] <= Math.min(A[0][0], A[m-1][n-1])) {
                    list.add(A[i][j]);
                }
            }
        }

        list.sort((a, b) -> a - b);

        int start = 0;
        int end = list.size()-1;
        while (start + 1 < end) {
            int mid = (start + end)/2;
            if (dfs(0, 0, new boolean[m][n], A, list.get(mid))) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (dfs(0, 0, new boolean[m][n], A, list.get(end))) {
            return list.get(end);
        }

        return list.get(start);
    }

    private boolean dfs(int x, int y, boolean[][] visited, int[][] A, int target) {
        int m = A.length;
        int n = A[0].length;
        if (x == m -1 && y == n-1) {
            return true;
        }
        visited[x][y] = true;
        boolean res = false;
        if (x < m-1 && A[x+1][y] >= target && !visited[x+1][y]) {
            res = res || dfs(x+1, y, visited, A, target);
        }
        if (x > 0 && A[x-1][y] >= target && !visited[x-1][y]) {
            res = res || dfs(x-1, y, visited, A, target);
        }
        if (y < n -1 && A[x][y+1] >= target && !visited[x][y+1]) {
            res = res || dfs(x, y+1, visited, A, target);
        }
        if (y > 0 && A[x][y-1] >= target && !visited[x][y-1]) {
            res = res || dfs(x, y-1, visited, A, target);
        }
        return res;
    }
}