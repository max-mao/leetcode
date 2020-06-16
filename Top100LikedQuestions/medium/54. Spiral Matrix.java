//https://leetcode.com/problems/spiral-matrix/
//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
//        Example 1:
//
//        Input:
//        [
//        [ 1, 2, 3 ],
//        [ 4, 5, 6 ],
//        [ 7, 8, 9 ]
//        ]
//        Output: [1,2,3,6,9,8,7,4,5]

//solution: https://www.youtube.com/watch?v=siKFOI8PNKM
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length -1;
        int dir = 0;

        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i ++) {
                    result.add(matrix[top][i]);
                }
                top ++;
            } else if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom --;
            } else if (dir == 3) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left ++;
            }
            dir = (dir + 1) %4;
        }

        return result;
    }
}