//https://leetcode.com/problems/maximum-subarray/
//Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
//
//
//Example 1:
//
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max_sum = Integer.MIN_VALUE;
        int cur_sum = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (cur_sum < 0) {
                cur_sum = 0;
            }
            cur_sum = cur_sum + nums[i];
            max_sum = Math.max(max_sum, cur_sum);
        }

        return max_sum;
    }
}