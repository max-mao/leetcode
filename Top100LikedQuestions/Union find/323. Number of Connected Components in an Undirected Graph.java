//https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
//You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
//
//Return the number of connected components in the graph.
//
//
//
//Example 1:
//
//
//Input: n = 5, edges = [[0,1],[1,2],[3,4]]
//Output: 2

class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i =0; i<n; i++) {
            parent[i] = i;
        }

        int res = n;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0];
            int to = edge[1];
            if (find(from, parent) != find(to, parent)) {
                union(from, to, parent);
                res --;
            }
        }

        return res;
    }

    private int find(int i, int[] parent) {
        int p = parent[i];
        if (i == parent[i]) {
            return i;
        }

        parent[i] = find(parent[i], parent);
        return parent[i];
    }

    private void union(int a, int b, int[] parent) {
        int p_a = find(a, parent);
        int p_b = find(b, parent);
        if (p_a == p_b) {
            return;
        } else {
            parent[p_a] = p_b;
        }
    }
}