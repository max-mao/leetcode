//https://leetcode.com/problems/maximum-product-subarray/
//
//Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
//        Example 1:
//
//        Input: [2,3,-2,4]
//        Output: 6
//        Explanation: [2,3] has the largest product 6.

class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //记录min是因为 两个负数相乘会变成正数
        int min = nums[0], max = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            int temp = max;
            max = Math.max(Math.max(max* nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            result = Math.max(max, result);
        }

        return result;
    }
}