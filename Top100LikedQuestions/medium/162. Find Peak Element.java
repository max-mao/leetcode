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
        int end = nums.length -1;

        while (start  < end) {
            int mid = (start + end)/2;
            if (nums[mid]  > nums[mid +1] ) {
                end = mid;
            } else {
                start = mid +1;
            }
        }

        return start ;
    }
}