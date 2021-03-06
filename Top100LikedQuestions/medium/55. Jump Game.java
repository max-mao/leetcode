//https://leetcode.com/problems/jump-game/

//Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//        Each element in the array represents your maximum jump length at that position.
//
//        Determine if you are able to reach the last index.
//
//        Example 1:
//
//        Input: [2,3,1,1,4]
//        Output: true
//        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

//DP: O(N^2)
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i= 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && nums[j] >= i -j) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[dp.length -1];
    }
}

//Greedy: O(N)
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }

        for (int i = nums.length -2; i >=0 ; i--) {
            if (nums[i] == 0) {
                int needSkip = 1;
                while (needSkip > nums[i]) {
                    needSkip ++;
                    i --;
                    if (i < 0) return false;
                }
            }
        }

        return true;
    }
}