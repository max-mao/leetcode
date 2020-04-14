//https://leetcode.com/problems/first-missing-positive/
//Given an unsorted integer array, find the smallest missing positive integer.
//
//        Example 1:
//
//        Input: [1,2,0]
//        Output: 3
//        Example 2:
//
//        Input: [3,4,-1,1]
//        Output: 2

//solution : https://www.youtube.com/watch?v=2QugZILS_Q8
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        boolean hasOne = false;
        for (int i: nums) {
            if (i == 1) {
                hasOne = true;
            }
        }

        if (!hasOne) return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <=0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }
        int negative = -1;
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (index == nums.length) {
                nums[0] = negative * Math.abs(nums[0]);
            } else {
                nums[index] = negative * Math.abs(nums[index]);
            }

        }


        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        if (nums[0] > 0) return nums.length;

        return nums.length + 1;
    }
}