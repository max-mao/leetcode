//https://leetcode.com/problems/spiral-matrix-ii/
//Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
//
//
//
//Example 1:
//
//
//Input: n = 3
//Output: [[1,2,3],[8,9,4],[7,6,5]]

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int top = 0;
        int bottom = n -1;
        int left = 0;
        int right = n -1;
        int dir = 0;
        int cur = 1;

        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i++) {
                    res[top][i] = cur;
                    cur++;
                }
                top++;
            }
            if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    res[i][right] = cur;
                    cur++;
                }
                right--;
            }
            if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    res[bottom][i] = cur;
                    cur++;
                }
                bottom--;
            }
            if (dir == 3) {
                for (int i = bottom; i >= top; i--) {
                    res[i][left] = cur;
                    cur++;
                }
                left++;
            }
            dir = (dir+1) % 4;
        }

        return res;
    }
}