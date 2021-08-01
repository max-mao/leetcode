//https://leetcode.com/problems/all-paths-from-source-to-target/
//Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.
//
//The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
//
//
//
//Example 1:
//
//
//Input: graph = [[1,2],[3],[3],[]]
//Output: [[0,1,3],[0,2,3]]
//Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        int n = graph.length - 1;
        Map<Integer, Set<Integer>> graph_map = buildGraph(graph);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, n, graph_map, res, list);

        return res;
    }

    private Map<Integer, Set<Integer>> buildGraph(int[][] graph) {
        Map<Integer, Set<Integer>> res = new HashMap<>();
        for (int i =0; i < graph.length; i++) {
            res.put(i, new HashSet<Integer>());
            int[] next_nodes = graph[i];
            for (int node : next_nodes) {
                res.get(i).add(node);
            }
        }
        return res;
    }

    private void dfs(int start, int end, Map<Integer, Set<Integer>> graph, List<List<Integer>> res, List<Integer> list) {
        if (start == end) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        Set<Integer> next_nodes = graph.get(start);
        for (int next : next_nodes) {
            list.add(next);
            dfs(next, end, graph, res, list);
            list.remove(list.size() -1);
        }
    }
}