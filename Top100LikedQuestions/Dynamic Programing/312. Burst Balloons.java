//https://leetcode.com/problems/burst-balloons/
//You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
//
//If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
//
//Return the maximum coins you can collect by bursting the balloons wisely.
//
//
//
//Example 1:
//
//Input: nums = [3,1,5,8]
//Output: 167
//Explanation:
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
//Example 2:
//
//Input: nums = [1,5]
//Output: 10

//solution: https://www.youtube.com/watch?v=z3hu2Be92UA
//DP:
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] new_nums = new int[n+2];
        for (int i = 1; i <= n; i++) {
            new_nums[i] = nums[i-1];
        }
        new_nums[0] = 1;
        new_nums[n+1] = 1;

        int[][] dp = new int[n+2][n+2];
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l+1; i++) {
                int j = i + l -1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i][k-1]
                                    + new_nums[i-1] * new_nums[k]* new_nums[j+1]
                                    + dp[k+1][j]);
                }
            }
        }

        return dp[1][n];
    }
}

//DFS & memorization
class Solution {
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        return dfs(nums, dp, 0, nums.length-1);
    }

    private int dfs(int[] nums, int[][] dp, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, dfs(nums, dp, start, i-1) + getNum(nums, start-1) * nums[i] * getNum(nums, end+1) + dfs(nums, dp, i+1, end));
        }

        dp[start][end] = max;

        return max;
    }

    private int getNum(int[] nums, int index) {
        if (index == -1 || index == nums.length) {
            return 1;
        }
        return nums[index];
    }
}
