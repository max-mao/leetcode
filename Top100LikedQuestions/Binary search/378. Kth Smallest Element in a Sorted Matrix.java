//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
//Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
//
//        Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//        Example:
//
//        matrix = [
//        [ 1,  5,  9],
//        [10, 11, 13],
//        [12, 13, 15]
//        ],
//        k = 8,
//
//        return 13.

//Solution: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
//Heap:
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        int n = matrix[0].length;

        for (int i = 0; i < n; i ++) {
            Tuple t = new Tuple(0, i, matrix[0][i]);
            pq.add(t);
        }

        for (int i = 0; i < k -1; i ++) {
            Tuple cur = pq.poll();
            int row = cur.x;
            int col = cur.y;
            if (row +1 >= matrix.length) continue;
            Tuple t = new Tuple(row+1, col, matrix[row+1][col]);
            pq.add(t);
        }

        return pq.poll().val;
    }
}

class Tuple implements Comparable<Tuple> {
    int x;
    int y;
    int val;

    public Tuple(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Tuple t) {
        return this.val - t.val;
    }
}

//Binary search: https://www.youtube.com/watch?v=PNJMEoIJMKQ
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int low = matrix[0][0];
        int high = matrix[matrix.length-1][matrix[0].length-1] ;

        while (low < high) {
            int mid = low + (high - low)/2;
            int count = 0;
            int j = matrix[0].length -1;
            for (int i = 0; i < matrix.length; i ++) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j --;
                }
                count += (j +1);
            }
            if (count < k) {
                low = mid +1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}