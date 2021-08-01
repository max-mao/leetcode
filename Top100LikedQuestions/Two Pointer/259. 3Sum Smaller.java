//https://leetcode.com/problems/3sum-smaller/
//
//Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
//
//
//        Example 1:
//
//        Input: nums = [-2,0,1,3], target = 2
//        Output: 2
//        Explanation: Because there are two triplets which sums are less than 2:
//        [-2,0,1]
//        [-2,0,3]

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i+1;
            int end = nums.length - 1;
            while (start < end) {
                if (nums[i] + nums[start] + nums[end] < target) {
                    res += end - start;
                    start++;

                } else {
                    end --;
                }
            }

        }
        return res;
    }
}