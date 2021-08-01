//https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
//On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
//
//A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
//
//Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
//
//
//
//Example 1:
//
//Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//Output: 5
//Explanation: One way to remove 5 stones is as follows:
//1. Remove stone [2,2] because it shares the same row as [2,1].
//2. Remove stone [2,1] because it shares the same column as [0,1].
//3. Remove stone [1,2] because it shares the same row as [1,0].
//4. Remove stone [1,0] because it shares the same column as [0,0].
//5. Remove stone [0,1] because it shares the same row as [0,0].
//Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
//https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/discuss/209369/Java-recursive-DFS-Short-and-easy-to-understand
class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int num_island = 0;

        Set<int[]> visited = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            if (!visited.contains(stones[i])) {
                dfs(stones[i], stones, visited);
                num_island++;
            }
        }

        return n - num_island;
    }

    private void dfs(int[] stone, int[][] stones, Set<int[]> visited) {
        visited.add(stone);
        for (int[] next : stones) {
            if (!visited.contains(next)) {
                if (next[0] == stone[0] || next[1] == stone[1]) {
                    dfs(next, stones, visited);
                }
            }
        }
    }
}

// Union find
class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int num_island = n;

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    if (find(i, parent) != find(j, parent)) {
                        union(i, j, parent);
                        num_island--;
                    }
                }
            }
        }

        return n - num_island;
    }

    private void union(int i, int j, int[] parent) {
        int p_i = find(i, parent);
        int p_j = find(j, parent);
        if (p_i == p_j) {
            return;
        }
        parent[p_i] = p_j;
    }

    private int find(int i, int[] parent) {
        int p = parent[i];
        if (p == i) {
            return p;
        }
        parent[i] = find(p, parent);
        return parent[i];
    }

}