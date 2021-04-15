//https://leetcode.com/problems/the-skyline-problem/
//A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
//
//        Buildings Skyline Contour
//        The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
//
//        For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
//
//        The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
//
//        For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

//solution: https://www.youtube.com/watch?v=GSBLe8cKu0s&feature=youtu.be
class Solution {

    class Point implements Comparable<Point>{
        int x;
        int y;
        boolean isStart;
        public Point(int x, int y, boolean isStart) {
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x != p.x) {
                return this.x - p.x;
            } else {
                return (this.isStart ? -y : y) - (p.isStart ? -p.y : p.y);
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }
        Point[] points = new Point[buildings.length * 2];
        int index = 0;
        for (int i = 0; i < buildings.length; i++) {
            points[index] = new Point(buildings[i][0], buildings[i][2], true);
            index++;
            points[index] = new Point(buildings[i][1], buildings[i][2], false);
            index++;
        }
        Arrays.sort(points);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        int maxHeight = 0;
        pq.add(0);
        for (int i =0; i < points.length; i++) {
            Point cur = points[i];
            int height = cur.y;
            if (cur.isStart) {
                pq.add(height);
            } else {
                pq.remove(height);
            }
            if (pq.peek() != maxHeight) {
                List<Integer> resPoint = new ArrayList<>();
                resPoint.add(cur.x);
                resPoint.add(pq.peek());
                maxHeight = pq.peek();
                res.add(resPoint);
            }
        }
        return res;
    }
}