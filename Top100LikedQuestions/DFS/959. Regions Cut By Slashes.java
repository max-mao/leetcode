//https://leetcode.com/problems/regions-cut-by-slashes/
//An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.
//
//Given the grid grid represented as a string array, return the number of regions.
//
//Note that backslash characters are escaped, so a '\' is represented as '\\'.

//https://leetcode.com/problems/regions-cut-by-slashes/discuss/205674/C%2B%2B-with-picture-DFS-on-upscaled-grid

class Solution {
    public int regionsBySlashes(String[] grid) {
        int[][] graph = new int[grid.length * 3][grid.length * 3];
        for (int i = 0; i < grid.length; i++) {
            String cur = grid[i];
            for (int j = 0; j < grid.length; j++) {
                if (cur.charAt(j) == '/') {
                    graph[i*3][j*3+2] = 1;
                    graph[i*3+1][j*3+1] = 1;
                    graph[i*3+2][j*3] = 1;
                } else if (cur.charAt(j) == '\\') {
                    graph[i*3][j*3] = 1;
                    graph[i*3+1][j*3+1] = 1;
                    graph[i*3+2][j*3+2] = 1;
                }
            }
        }

        int res = 0;
        for (int i =0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 0) {
                    makeOne(i, j, graph);
                    res++;
                }
            }
        }

        return res;
    }

    private void makeOne(int i, int j, int[][] graph) {
        if (i < 0 || i >= graph.length || j < 0 || j >= graph[0].length) {
            return;
        }
        if (graph[i][j] == 1) {
            return;
        }
        graph[i][j] = 1;
        makeOne(i+1, j, graph);
        makeOne(i-1, j, graph);
        makeOne(i, j+1, graph);
        makeOne(i, j-1, graph);
    }
}