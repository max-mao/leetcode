//https://leetcode.com/problems/coin-change/
//You are given coins of different denominations and a total amount of money amount.
// Write a function to compute the fewest number of coins that you need to make up that amount.
// If that amount of money cannot be made up by any combination of the coins, return -1.
//
//        Example 1:
//
//        Input: coins = [1, 2, 5], amount = 11
//        Output: 3
//        Explanation: 11 = 5 + 5 + 1

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : coins) {
            set.add(i);
        }

        // 表示当前index代表的amount的fewest number of coins
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j ++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1) {
                        dp[i] = dp[i - coins[j]] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
        }

        return dp[amount];
    }
}