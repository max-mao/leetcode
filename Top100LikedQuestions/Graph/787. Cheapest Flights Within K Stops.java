//https://leetcode.com/problems/cheapest-flights-within-k-stops/
//There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
//
//You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
//
//
//
//Example 1:
//
//
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//Output: 200
//Explanation: The graph is shown.
//The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k+2][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        for (int i = 0; i < k+2; i++) {
            dp[i][src] = 0;
        }

        for (int i = 1; i <= k+1; i++) {
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];

                if (dp[i-1][from] != Integer.MAX_VALUE) {
                    dp[i][to] = Math.min(dp[i][to], dp[i-1][from] + price);
                }
            }
        }

        return dp[k+1][dst] == Integer.MAX_VALUE ? -1 : dp[k+1][dst];
    }
}

//BFS + PQ
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            if (!graph.containsKey(from)) {
                graph.put(from, new HashMap<Integer, Integer>());
            }
            graph.get(from).put(to, price);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, src, k+1});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cur_price = cur[0];
            int cur_node = cur[1];
            int stop_left = cur[2];

            if (cur_node == dst) {
                return cur_price;
            }
            if (stop_left > 0) {
                Map<Integer, Integer> nexts = graph.getOrDefault(cur_node, new HashMap<>());
                for (int next : nexts.keySet()) {
                    pq.add(new int[]{cur_price + nexts.get(next), next, stop_left-1});
                }
            }
        }

        return -1;
    }
}