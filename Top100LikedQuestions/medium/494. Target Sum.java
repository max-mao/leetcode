//https://leetcode.com/problems/target-sum/
//You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
//
//        Find out how many ways to assign symbols to make sum of integers equal to target S.
//
//        Example 1:
//        Input: nums is [1, 1, 1, 1, 1], S is 3.
//        Output: 5
//        Explanation:
//
//        -1+1+1+1+1 = 3
//        +1-1+1+1+1 = 3
//        +1+1-1+1+1 = 3
//        +1+1+1-1+1 = 3
//        +1+1+1+1-1 = 3

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[][] memo = new int[nums.length][2000];
        for (int i = 0 ; i < memo.length; i ++) {
            Arrays.fill(memo[i], -1);
        }

        return helper(nums, S, 0, 0, memo);
    }

    private int helper(int[] nums, int S, int curSum, int index, int[][] memo) {
        if (index == nums.length && curSum == S) {
            return 1;
        } else if (index == nums.length) {
            return 0;
        }
        if (memo[index][curSum +1000] != -1) {
            return memo[index][curSum + 1000];
        }

        int result = 0;
        result += helper(nums, S, curSum + nums[index], index + 1, memo);
        result += helper(nums, S, curSum - nums[index], index + 1, memo);

        memo[index][curSum + 1000] = result;

        return result;
    }
}