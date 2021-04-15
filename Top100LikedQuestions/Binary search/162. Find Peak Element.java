//https://leetcode.com/problems/find-peak-element/
//A peak element is an element that is greater than its neighbors.
//
//        Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//
//        The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//        You may imagine that nums[-1] = nums[n] = -∞.

class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) /2;
            if (mid + 1 < nums.length
                    && mid - 1 >= 0
                    && nums[mid] > nums[mid+1]
                    && nums[mid] > nums[mid -1]
            ) {
                return mid;
            } else if (nums[mid] < nums[mid+1]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        int n = nums.length;
        if (n == 1) return 0;
        if (nums[n-1] > nums[n-2]) {
            return n-1;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        return start;

    }
}