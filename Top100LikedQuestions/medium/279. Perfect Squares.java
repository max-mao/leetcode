//https://leetcode.com/problems/perfect-squares/

//Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
//Example 1:
//
//Input: n = 12
//Output: 3
//Explanation: 12 = 4 + 4 + 4.

//DP
class Solution {
    public int numSquares(int n) {
        int[] result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        result[1] = 1;
        int sqrt = (int)Math.sqrt(n) + 1;

        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j < sqrt; j ++) {
                int power = j * j;
                if (i - power < 0) {
                    break;
                }
                result[i] = Math.min(result[i - power] + 1, result[i]);
            }
        }

        return result[n];
    }
}