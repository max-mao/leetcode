//https://leetcode.com/problems/redundant-connection/
//In this problem, a tree is an undirected graph that is connected and has no cycles.
//
//        The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
//
//        The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
//
//        Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
//
//        Example 1:
//        Input: [[1,2], [1,3], [2,3]]
//        Output: [2,3]
//        Explanation: The given undirected graph will be like this:
//        1
//        / \
//        2 - 3

//Union find
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return null;
        }

        Map<Integer, Integer> parents = new HashMap<>();

        int[] result = new int[2];
        for (int i = 0; i < edges.length; i ++) {
            int x = edges[i][0];
            int y = edges[i][1];
            if (find(x, parents) == find(y, parents)) {
                result[0] = x;
                result[1] = y;
            } else {
                union(x, y, parents);
            }
        }

        return result;
    }

    private void union(int x, int y, Map<Integer, Integer> parents) {
        if (!parents.containsKey(x)) {
            parents.put(x,x);
        }
        if (!parents.containsKey(y)) {
            parents.put(y,y);
        }

        int px = find(x, parents);
        int py = find(y, parents);
        parents.put(px, py);
    }

    private int find(int x, Map<Integer, Integer> parents) {
        int p = parents.getOrDefault(x, x);
        if (x != p) {
            int pp = find(p, parents);
            parents.put(x, pp);
        }

        return parents.getOrDefault(x,x );
    }
}