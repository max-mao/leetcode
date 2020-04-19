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
        if (edges == null || edges.length == 0) {
            return null;
        }

        int nodeHasTwoIncoming = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< edges.length; i++) {
            int node = edges[i][1];
            map.put(node, map.getOrDefault(node, 0)+1);
            if (map.get(node) == 2) {
                nodeHasTwoIncoming = node;
            }
        }

        if (nodeHasTwoIncoming == -1) {
            return unionFind(edges, -1);
        } else {
            for (int i = edges.length -1; i >= 0; i --) {
                if (edges[i][1] == nodeHasTwoIncoming) {
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
        Map<Integer, Integer> parents = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            if (i == skip) {
                continue;
            }
            int x = edges[i][0];
            int y = edges[i][1];
            if (union(x, y, parents)){
                int[] result = new int[2];
                result[0] = x;
                result[1] = y;
                return result;
            }
        }

        return null;
    }

    private boolean union(int x, int y, Map<Integer, Integer> parents) {
        if (!parents.containsKey(x)) {
            parents.put(x,x);
        }

        if (!parents.containsKey(y)) {
            parents.put(y,y);
        }

        int px = find(x, parents);
        int py = find(y, parents);
        if (px == py) return true;
        parents.put(px, py);
        return false;
    }

    private int find(int x, Map<Integer, Integer> parents) {
        int p = parents.getOrDefault(x,x);
        if (x != p) {
            int pp = find(p, parents);
            parents.put(x, pp);
        }

        return parents.getOrDefault(x,x);
    }
}