//https://leetcode.com/problems/range-sum-query-2d-mutable/
//Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
//        Range Sum Query 2D
//        The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
//
//        Example:
//        Given matrix = [
//        [3, 0, 1, 4, 2],
//        [5, 6, 3, 2, 1],
//        [1, 2, 0, 1, 5],
//        [4, 1, 0, 1, 7],
//        [1, 0, 3, 0, 5]
//        ]
//
//        sumRegion(2, 1, 4, 3) -> 8
//        update(3, 2, 2)
//        sumRegion(2, 1, 4, 3) -> 10


//solution: https://leetcode.com/problems/range-sum-query-2d-mutable/discuss/75852/15ms-easy-to-understand-java-solution
class NumMatrix {
    // sumMatrix[i][j] = matrix[0][j] + matrix[1][j] + ... + matrix[i -1][j]
    int[][] sumMatrix;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        this.sumMatrix = new int[m+1][n];
        this.matrix = matrix;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j ++) {
                sumMatrix[i][j] = sumMatrix[i-1][j] + matrix[i-1][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        for (int i = row +1; i < sumMatrix.length; i ++) {
            sumMatrix[i][col] = sumMatrix[i][col] - matrix[row][col] + val;
        }

        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = col1; i <= col2; i++) {
            res += sumMatrix[row2 +1][i] - sumMatrix[row1][i];
        }

        return res;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */