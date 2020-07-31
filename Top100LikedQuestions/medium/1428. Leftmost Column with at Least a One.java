//https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
//(This problem is an interactive problem.)
//
//        A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
//
//        Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.
//
//        You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
//
//        BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
//        BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
//        Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
//
//        For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.


//solution: binary search:

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int m = dimension.get(0), n = dimension.get(1);
        int res = -1;
        int start = 0, end = n -1;
        while (start <= end) {
            int mid = (start + end) /2;
            if (hasOneInCol(binaryMatrix, mid, m)) {
                res = mid;
                end = mid -1;
            } else {
                start = mid +1;
            }
        }

        return res;
    }

    private boolean hasOneInCol(BinaryMatrix binaryMatrix, int mid, int rows) {
        for (int i =0; i < rows; i++) {
            if (binaryMatrix.get(i, mid) == 1) {
                return true;
            }
        }

        return false;
    }
}

//solution 2:
//https://leetcode.com/problems/leftmost-column-with-at-least-a-one/discuss/590828/Java-Binary-Search-and-Linear-Solutions-with-Picture-Explain-Clean-Code
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int m = dimension.get(0),  n = dimension.get(1);

        int row = 0, col = n -1;
        int res = -1;
        while (row < m && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                res = col;
                col --;
            } else {
                row ++;
            }
        }

        return res;
    }
}

