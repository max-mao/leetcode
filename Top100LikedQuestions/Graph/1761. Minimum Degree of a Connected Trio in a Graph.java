//https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
//You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
//
//A connected trio is a set of three nodes where there is an edge between every pair of them.
//
//The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
//
//Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
//
//
//
//Example 1:
//
//
//Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
//Output: 3
//Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.

class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        boolean[][] isEdge = new boolean[n+1][n+1];
        int[] degree = new int[n+1];

        for (int[] edge : edges) {
            int first = edge[0];
            int second = edge[1];

            degree[first]++;
            degree[second]++;
            isEdge[first][second] = true;
            isEdge[second][first] = true;
        }

        int res = Integer.MAX_VALUE;
        for (int[] edge : edges) {
            int first = edge[0];
            int second = edge[1];
            for (int i = 1; i <= n; i++) {
                if (isEdge[i][first] && isEdge[i][second]) {
                    // subtract 6 because we do not count inner edges of a trio
                    res = Math.min(res, degree[i] + degree[first] + degree[second] - 6);
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}