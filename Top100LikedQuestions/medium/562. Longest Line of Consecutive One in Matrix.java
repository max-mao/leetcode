//Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
//Example:
//Input:
//[[0,1,1,0],
// [0,1,1,0],
// [0,0,0,1]]
//Output: 3

class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }

        int[][][] lens = new int[M.length][M[0].length][4];
        int result = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    for (int k =0; k < 4; k++) {
                        lens[i][j][k] = 1;
                    }
                    // 0 -> horizontal, 1 -> vertical, 2 -> diagonal, 3 -> anti-diagonal.
                    if (j - 1 >= 0) {
                        lens[i][j][0] = lens[i][j-1][0] +1;
                    }
                    if (i - 1 >= 0) {
                        lens[i][j][1] = lens[i-1][j][1] +1;
                    }
                    if (i - 1 >= 0 && j -1 >= 0) {
                        lens[i][j][2] = lens[i-1][j-1][2] +1;
                    }
                    if (i - 1 >= 0 && j + 1 < M[0].length) {
                        lens[i][j][3] = lens[i-1][j+1][3] +1;
                    }
                    result = Math.max(result, Math.max(lens[i][j][0], lens[i][j][1]));
                    result = Math.max(result, Math.max(lens[i][j][2], lens[i][j][3]));
                }

            }
        }

        return result;
    }
}