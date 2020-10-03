//https://leetcode.com/problems/stone-game-ii/
//Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
//
//Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
//
//On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
//
//The game continues until all the stones have been taken.
//
//Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
//
//
//
//Example 1:
//
//Input: piles = [2,7,9,4,4]
//Output: 10
//Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.


//solution: https://leetcode.com/problems/stone-game-ii/discuss/345354/Java-DP-with-memorization-easy-to-understand(with-explanation)
class Solution {
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        //sum[i] means the sum of piles[i] + .. piles[len-1]
        int[] sum = new int[len];
        sum[len -1] = piles[len -1];
        for (int i = len -2; i >=0; i--) {
            sum[i] = sum[i+1] + piles[i];
        }
        return helper(sum, 0, dp, 1);
    }

    private int helper(int[] sum, int left, int[][] dp, int M) {
        if (dp[left][M] != 0) {
            return dp[left][M];
        }

        if (2 * M + left >= sum.length) {
            dp[left][M] = sum[left];
            return dp[left][M];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 2*M; i++) {
            min = Math.min(min, helper(sum, left +i, dp, Math.max(M, i)));
        }

        dp[left][M] = sum[left] - min;
        return dp[left][M];

    }
}