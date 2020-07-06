//https://leetcode.com/problems/k-closest-points-to-origin/
//We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
//
//        (Here, the distance between two points on a plane is the Euclidean distance.)
//
//        You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
//
//
//
//        Example 1:
//
//        Input: points = [[1,3],[-2,2]], K = 1
//        Output: [[-2,2]]
//        Explanation:
//        The distance between (1, 3) and the origin is sqrt(10).
//        The distance between (-2, 2) and the origin is sqrt(8).
//        Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//        We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<>() {
            public int compare(int[] p1, int[] p2) {
                return (p1[0]*p1[0] + p1[1]*p1[1]) - (p2[0]*p2[0] + p2[1]*p2[1]);
            }
        });

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i ++) {
            for (int j = 0; j < 2; j ++) {
                res[i][j] = points[i][j];
            }
        }

        return res;
    }
}

// heap:
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<int[]> heap = new PriorityQueue<>(K, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2) {
                return (p1[0]*p1[0] + p1[1]*p1[1]) - (p2[0]*p2[0] + p2[1]*p2[1]);
            }
        });

        for (int i =0; i < points.length; i ++) {
            heap.add(points[i]);
        }

        for (int i =0; i < K; i++) {
            res[i] = heap.poll();
        }

        return res;
    }
}