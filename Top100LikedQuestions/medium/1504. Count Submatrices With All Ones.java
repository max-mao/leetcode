//https://leetcode.com/problems/count-submatrices-with-all-ones/
//Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
//
//
//
//Example 1:
//
//Input: mat = [[1,0,1],
//              [1,1,0],
//              [1,1,0]]
//Output: 13
//Explanation:
//There are 6 rectangles of side 1x1.
//There are 2 rectangles of side 1x2.
//There are 3 rectangles of side 2x1.
//There is 1 rectangle of side 2x2.
//There is 1 rectangle of side 3x1.
//Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

//solution: https://www.youtube.com/watch?v=8HYXkNB39KA
//https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720234/Java-solution-O(m*m*n)
class Solution {
    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = n - 2; j >= 0; j--) {
                if (mat[i][j] == 1) {
                    mat[i][j] = mat[i][j+1] + 1;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                res += mat[i][j];
                int min = mat[i][j];
                for (int k = i+1; k < m; k++) {
                    min = Math.min(min, mat[k][j]);
                    res += min;
                }
            }
        }

        return res;
    }
}