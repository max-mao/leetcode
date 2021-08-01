//https://leetcode.com/problems/connecting-cities-with-minimum-cost/
//There are N cities numbered from 1 to N.
//
//You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
//
//Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
//
//
//
//Example 1:
//
//
//
//Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
//Output: 6
//Explanation:
//Choosing any 2 edges will connect all cities so we choose the minimum 2.
//https://leetcode.com/problems/connecting-cities-with-minimum-cost/discuss/344867/Java-Kruskal's-Minimum-Spanning-Tree-Algorithm-with-Union-Find
// video: https://www.youtube.com/watch?v=4ZlRH0eK-qQ
class Solution {
    public int minimumCost(int N, int[][] connections) {
        int[] parent = new int[N+1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        int res = 0;
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        for (int i =0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            if (find(from, parent) != find(to, parent)) {
                union(from, to, parent);
                N--;
                res += connections[i][2];
            }
        }

        return N == 1? res : -1;
    }

    private int find(int a, int[] parent) {
        int p_a = parent[a];
        if (p_a == a) {
            return p_a;
        }
        parent[a] = find(p_a, parent);
        return parent[a];
    }

    private void union(int a, int b, int[] parent) {
        int p_a = find(a, parent);
        int p_b = find(b, parent);
        if (p_a == p_b) {
            return;
        }

        parent[p_a] = p_b;
    }
}