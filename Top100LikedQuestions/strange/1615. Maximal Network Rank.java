//https://leetcode.com/problems/maximal-network-rank/
//There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
//
//The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.
//
//The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
//
//Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
//
//
//
//Example 1:
//
//
//
//Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
//Output: 4
//Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connected = new boolean[n][n];
        int[] indegree = new int[n];
        for (int[] road : roads) {
            int n1 = road[0];
            int n2 = road[1];
            indegree[n1]++;
            indegree[n2]++;
            connected[n1][n2] = true;
            connected[n2][n1] = true;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                res = Math.max(res, indegree[i] + indegree[j] - (connected[i][j] ? 1 : 0));
            }
        }

        return res;
    }
}
