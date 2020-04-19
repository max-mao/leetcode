//https://leetcode.com/problems/friend-circles/
//There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
//
//        Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
//
//        Example 1:
//        Input:
//        [[1,1,0],
//        [1,1,0],
//        [0,0,1]]
//        Output: 2
//        Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
//        The 2nd student himself is in a friend circle. So return 2.

//union find
class Solution {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }

        List<int[]> list = new ArrayList<>();
        for (int i =0; i < M.length; i ++) {
            for (int j = i; j < M[0].length; j ++) {
                if (M[i][j] == 1) {
                    int[] cur = new int[2];
                    cur[0] = i;
                    cur[1] = j;
                    list.add(cur);
                }
            }
        }

        int result = 0;
        Map<Integer, Integer> parents = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < list.size(); i ++) {
            int[] cur = list.get(i);
            int x = cur[0];
            int y = cur[1];
            union(x, y, parents);
        }

        for (int i = 0; i < list.size(); i ++) {
            int[] cur = list.get(i);
            int x = cur[0];

            int p = find(x, parents);
            if (!set.contains(p)) {
                set.add(p);
            }
        }


        return set.size();
    }

    private void union(int x, int y, Map<Integer, Integer> parents) {
        // if (!parents.containsKey(x)) {
        //     parents.put(x,x);
        // }
        // if (!parents.containsKey(y)) {
        //     parents.put(y,y);
        // }
        int px = find(x, parents);
        int py = find(y, parents);
        parents.put(px, py);
    }

    private int find(int x, Map<Integer, Integer> parents) {
        int p = parents.getOrDefault(x, x);
        if (x != p) {
            int pp = find(p, parents);
            parents.put(x, pp);
        }

        return parents.getOrDefault(x, x);
    }
}