//https://leetcode.com/problems/redundant-connection-ii/
//In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
//
//        The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
//
//        The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.
//
//        Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
//
//        Example 1:
//        Input: [[1,2], [1,3], [2,3]]
//        Output: [2,3]
//        Explanation: The given directed graph will be like this:
//        1
//        / \
//        v   v
//        2-->3

//solution: https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C%2B%2BJava-Union-Find-with-explanation-O(n)
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int hasTwoIncoming = -1;
        Map<Integer, Integer> incoming = new HashMap<>();
        for (int[] edge : edges) {
            incoming.put(edge[1], incoming.getOrDefault(edge[1], 0) + 1);
            if (incoming.get(edge[1]) == 2) {
                hasTwoIncoming = edge[1];
            }
        }

        if (hasTwoIncoming == -1) {
            return unionFind(edges, -1);
        } else {
            for (int i = edges.length -1; i >= 0; i--) {
                if (edges[i][1] == hasTwoIncoming) {
                    int[] res = unionFind(edges, i);
                    if (res == null) {
                        return edges[i];
                    }
                }
            }
        }

        return null;
    }

    private int[] unionFind(int[][] edges, int skip) {
        Map<Integer, Integer> parent = new HashMap<>();
        int[] res = null;
        for (int i = 0; i < edges.length; i++) {
            if (i == skip) {
                continue;
            }
            int[] edge = edges[i];
            if (find(edge[0], parent) == find(edge[1], parent)) {
                res = edge;
            } else {
                union(edge[0], edge[1], parent);
            }
        }

        return res;
    }

    private int find(int i, Map<Integer, Integer> parent) {
        if (!parent.containsKey(i)) {
            parent.put(i, i);
            return i;
        }
        int p = parent.get(i);
        if (i == p) {
            return p;
        }
        return find(p, parent);
    }

    private void union(int a, int b, Map<Integer, Integer> parent) {
        int p_a = find(a, parent);
        int p_b = find(b, parent);
        if (p_a == p_b) {
            return;
        } else {
            parent.put(p_a, p_b);
        }
    }
}