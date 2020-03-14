//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

//
//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
//        Your algorithm's runtime complexity must be in the order of O(log n).
//
//        If the target is not found in the array, return [-1, -1].
//
//        Example 1:
//
//        Input: nums = [5,7,7,8,8,10], target = 8
//        Output: [3,4]
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums == null || nums.length == 0) {
            return result;
        }

        if (target < nums[0] || target > nums[nums.length - 1]) {
            return result;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right)/2;
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (nums[left] == target) {
            result[0] = left;
        }
        else if (nums[right] == target) {
            result[0] = right;
        }

        int left1 = 0;
        int right1 = nums.length - 1;
        while (left1 + 1 < right1) {
            int mid1 = (left1 + right1)/2;
            if (target < nums[mid1]) {
                right1 = mid1;
            } else {
                left1 = mid1;
            }
        }

        if (nums[right1] == target) {
            result[1] = right1;
        }
        else if (nums[left1] == target) {
            result[1] = left1;
        }

        return result;
    }
}