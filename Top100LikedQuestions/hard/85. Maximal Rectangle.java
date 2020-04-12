//https://leetcode.com/problems/maximal-rectangle/
//Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//
//        Example:
//
//        Input:
//        [
//        ["1","0","1","0","0"],
//        ["1","0","1","1","1"],
//        ["1","1","1","1","1"],
//        ["1","0","0","1","0"]
//        ]
//        Output: 6

//DP: solution: https://www.youtube.com/watch?v=g8bSdXCG-lA
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int result = 0;
        int[] histogram = new int[matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    histogram[j] = 0;
                } else {
                    histogram[j] += 1;
                }
            }
            int cur = findMaxHistogram(histogram);
            result = Math.max(result, cur);
        }

        return result;
    }

    private int findMaxHistogram(int[] histogram) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < histogram.length; i++) {
            while (!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                int height = histogram[stack.pop()];
                int len = stack.isEmpty() ? i : i - stack.peek() -1;
                result = Math.max(result, height* len);
            }
            stack.push(i);
        }

        return result;
    }
}
