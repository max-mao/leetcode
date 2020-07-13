//https://leetcode.com/problems/interval-list-intersections/
//Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
//
//        Return the intersection of these two interval lists.
//
//        (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
//        The intersection of two closed intervals is a set of real numbers that is either empty,
//        or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length > B.length) {
            return intervalIntersection(B, A);
        }
        List<int[]> list = new ArrayList<>();

        int a_cur = 0, b_cur = 0;
        while(a_cur < A.length && b_cur < B.length) {
            int[] A_Now = A[a_cur];
            int[] B_Now = B[b_cur];

            int max_start = Math.max(A_Now[0], B_Now[0]);
            int min_end = Math.min(A_Now[1], B_Now[1]);
            if (max_start <= min_end) {
                list.add(new int[]{max_start, min_end});
            }

            if (A_Now[1] == min_end) a_cur++;
            if (B_Now[1] == min_end) b_cur++;
        }

        int[][] res = new int[list.size()][2];
        for (int i =0; i < list.size(); i ++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}