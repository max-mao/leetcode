//https://leetcode.com/problems/next-permutation/
//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
//        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
//        The replacement must be in-place and use only constant extra memory.
//
//        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//
//        1,2,3 → 1,3,2
//        3,2,1 → 1,2,3
//        1,1,5 → 1,5,1

// video: https://www.youtube.com/watch?v=quAS1iydq7U&t=608s
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int right = nums.length - 1;
        while (right > 0 && nums[right] <= nums[right - 1]) {
            right --;
        }

        if (right == 0) {
            reverse(0, nums.length - 1, nums);
            return;
        }

        int left = right - 1;
        int first_large = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[left]) {
                first_large = i;
                break;
            }
        }
        swap (left, first_large, nums);
        reverse(right, nums.length - 1, nums);
    }

    private void swap(int i1, int i2, int[] nums) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }

    private void reverse(int start, int end, int[]nums) {
        while (start < end) {
            swap(start, end, nums);
            start ++;
            end --;
        }
    }
}