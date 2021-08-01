//https://leetcode.com/problems/network-delay-time/
//You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
//
//We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
//
//
//
//Example 1:
//
//
//Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//Output: 2
// solution: https://leetcode.com/problems/network-delay-time/discuss/210698/Java-Djikstrabfs-Concise-and-very-easy-to-understand
// Dijkstra Algorithm  L  https://www.youtube.com/watch?v=XB4MIexjvY0
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            if (!graph.containsKey(from)) {
                graph.put(from, new HashMap<Integer, Integer>());
            }
            graph.get(from).put(to, weight);
        }

        int res = 0;
        boolean[] visited = new boolean[n+1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cur_node = cur[0];
            int cur_weight = cur[1];
            if (visited[cur_node]) {
                continue;
            }
            visited[cur_node] = true;
            n--;
            res = cur_weight;
            if (graph.containsKey(cur_node)) {
                Map<Integer, Integer> nexts = graph.get(cur_node);
                for (int next : nexts.keySet()) {
                    queue.add(new int[]{next, res + nexts.get(next)});
                }
            }
        }

        return n == 0? res : -1;
    }
}