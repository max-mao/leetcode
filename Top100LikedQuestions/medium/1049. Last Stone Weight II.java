////https://leetcode.com/problems/last-stone-weight-ii/
//We have a collection of rocks, each rock has a positive integer weight.
//
//        Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
//
//        If x == y, both stones are totally destroyed;
//        If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
//        At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)

//背包问题，能转化成 1 - 0 背包，解释：https://leetcode.com/problems/last-stone-weight-ii/discuss/295167/Java-beat-100-with-nice-explanation
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }

        boolean[][] dp = new boolean[stones.length + 1][sum /2 +1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        int half = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - stones[i-1] >= 0) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - stones[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }

                if (dp[i][j] == true) {
                    half = Math.max(half, j);
                }
            }
        }

        return sum - 2 *half;
    }
}