//https://leetcode.com/problems/possible-bipartition/
//Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
//
//Each person may dislike some other people, and they should not go into the same group.
//
//Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
//
//Return true if and only if it is possible to split everyone into two groups in this way.
//
//
//
//Example 1:
//
//Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
//Output: true
//Explanation: group1 [1,4], group2 [2,3]

//solution: https://leetcode.com/problems/possible-bipartition/discuss/158957/Java-DFS-solution
//DFS:
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] dislike : dislikes) {
            int n1 = dislike[0];
            int n2 = dislike[1];
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        // 1 in a group, -1 in another group
        int[] color_arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            if (color_arr[i] == 0 && !dfs(graph, i, color_arr, 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, int n, int[] color_arr, int color) {
        if (color_arr[n] != 0) {
            return color_arr[n] == color;
        }
        color_arr[n] = color;
        for (int next : graph.get(n)) {
            if (color_arr[next] == color) {
                return false;
            }
            if (color_arr[next] == 0 && !dfs(graph, next, color_arr, -color)) {
                return false;
            }
        }

        return true;
    }
}


// bfs;
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] dislike : dislikes) {
            int n1 = dislike[0];
            int n2 = dislike[1];
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        // 1 in a group, -1 in another group
        int[] color_arr = new int[N+1];

        for (int i = 1; i <= N; i++) {
            if (color_arr[i] == 0) {
                LinkedList<Integer> queue = new LinkedList<>();
                queue.add(i);
                color_arr[i] = 1;
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int cur_cor = color_arr[cur];
                    Set<Integer> nexts = graph.get(cur);
                    for (int next : nexts) {
                        if (color_arr[next] == 0) {
                            queue.add(next);
                            color_arr[next] = -cur_cor;
                        } else if (color_arr[next] == cur_cor) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}
