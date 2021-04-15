//https://leetcode.com/problems/rotting-oranges/
//In a given grid, each cell can have one of three values:
//
//the value 0 representing an empty cell;
//the value 1 representing a fresh orange;
//the value 2 representing a rotten orange.
//Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
//
//Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
//
//
//
//Example 1:
//
//
//
//Input: [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    int[] start = new int[]{i, j};
                    queue.add(start);
                }  else if (grid[i][j] == 1) {
                    count_fresh++;
                }
            }
        }
        //if count of fresh oranges is zero --> return 0
        if (count_fresh == 0) return 0;
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i =0; i < size; i++) {
                int[] cur = queue.poll();
                List<int[]>  nexts = getNexts(cur, grid);
                queue.addAll(nexts);
            }
            res++;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return res - 1;
    }

    private List<int[]> getNexts(int[] cur, int[][] grid) {
        List<int[]> res = new ArrayList<int[]>();
        int i = cur[0];
        int j = cur[1];
        if (i + 1 < grid.length && grid[i+1][j] == 1) {
            int[] next = new int[] {i+1, j};
            grid[i+1][j] = 2;
            res.add(next);
        }
        if (i - 1 >= 0 && grid[i-1][j] == 1) {
            int[] next = new int[] {i-1, j};
            grid[i-1][j] = 2;
            res.add(next);
        }
        if (j + 1 < grid[0].length && grid[i][j+1] == 1) {
            int[] next = new int[] {i, j+1};
            grid[i][j+1] = 2;
            res.add(next);
        }
        if (j - 1 >= 0 && grid[i][j-1] == 1) {
            int[] next = new int[] {i, j-1};
            grid[i][j-1] = 2;
            res.add(next);
        }
        return res;
    }
}
