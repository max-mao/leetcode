//https://leetcode.com/problems/sort-colors/
//Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
//        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
//        Note: You are not suppose to use the library's sort function for this problem.
//
//        Example:
//
//        Input: [2,0,2,1,1,0]
//        Output: [0,0,1,1,2,2]

// solution 1: quick sort
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = nums[(start + end) / 2];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left ++;
            }
            while (left <= right && nums[right] > pivot) {
                right --;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left ++;
                right --;
            }
        }

        quickSort (nums, start, right);
        quickSort(nums, left, end);
    }
}

// 3 个指针
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int left = 0;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] == 0) {
                int temp = nums[left];
                nums[left] = nums[start];
                nums[start] = temp;
                start ++;
                left ++;
            } else if (nums[start] == 2) {
                int temp = nums[end];
                nums[end] = nums[start];
                nums[start] = temp;
                end --;
            } else {
                start ++;
            }
        }
    }
}