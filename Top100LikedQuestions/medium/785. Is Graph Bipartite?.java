//https://leetcode.com/problems/is-graph-bipartite/
//Given an undirected graph, return true if and only if it is bipartite.
//
//        Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
//
//        The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
//
//        Example 1:
//        Input: [[1,3], [0,2], [1,3], [0,2]]
//        Output: true
//        Explanation:
//        The graph looks like this:
//        0----1
//        |    |
//        |    |
//        3----2
//        We can divide the vertices into two groups: {0, 2} and {1, 3}.

//solution: https://www.youtube.com/watch?v=670Gn4e89B8
class Solution {
    public boolean isBipartite(int[][] graph) {
        //0 not visited, 1 : blue, -1 : red
        int[] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            colors[i] = 1;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int[] neighbors = graph[cur];
                for (int neighbor : neighbors) {
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = - colors[cur];
                        queue.add(neighbor);
                    } else if (colors[neighbor] != - colors[cur]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}