//https://leetcode.com/problems/max-points-on-a-line/
//Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
//        Example 1:
//
//        Input: [[1,1],[2,2],[3,3]]
//        Output: 3
//        Explanation:
//        ^
//        |
//        |        o
//        |     o
//        |  o
//        +------------->
//        0  1  2  3  4

//https://www.youtube.com/watch?v=KqZV0XfKQks&t=316s
class Solution {
    public int maxPoints(int[][] points) {
        if (points.length < 1) {
            return 0;
        }
        int max = 1;

        for (int i = 0; i < points.length; i ++) {
            int[] p = points[i];
            int localMax = 1;
            Map<String, Integer> map = new HashMap<>();
            int same = 0;

            for (int j = i+1; j <points.length; j ++) {
                int[] cur = points[j];
                if (p[0] == cur[0] && p[1] == cur[1]) {
                    same ++;
                    continue;
                }
                String slope = getSlope(p, points[j]);
                map.put(slope, map.getOrDefault(slope, 1) +1);
                localMax = Math.max(localMax, map.get(slope));
            }
            localMax += same;
            max = Math.max(max, localMax);
        }

        return max;
    }

    private String getSlope(int[] p1, int[] p2) {
        int dy = p2[1] - p1[1];
        int dx = p2[0] - p1[0];
        int gcd = gcd(dy, dx);
        return dy / gcd + "/" + dx / gcd;
    }

    //求最大公约数
    private int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}