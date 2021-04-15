//https://leetcode.com/problems/kth-largest-element-in-an-array/
//Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//        Example 1:
//
//        Input: [3,2,1,5,6,4] and k = 2
//        Output: 5
//        Example 2:
//
//        Input: [3,2,3,1,2,4,5,5,6] and k = 4
//        Output: 4

class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int left = start;
        int right = end;
        int mid = nums[start + (end - start)/2];

        while (left <= right) {
            while (left <= right && nums[left] < mid) {
                left++;
            }
            while (left <= right && nums[right] > mid) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        if (right >= k) {
            return quickSelect(nums, start, right, k);
        }
        if (left <= k) {
            return quickSelect(nums, left, end, k);
        }

        return nums[k];
    }
}