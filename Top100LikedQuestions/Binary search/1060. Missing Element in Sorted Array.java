//https://leetcode.com/problems/missing-element-in-sorted-array/
//Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
//
//
//
//Example 1:
//
//Input: A = [4,7,9,10], K = 1
//Output: 5
//Explanation:
//The first missing number is 5.
//Example 2:
//
//Input: A = [4,7,9,10], K = 3
//Output: 8
//Explanation:
//The missing numbers are [5,6,8,...], hence the third missing number is 8.


//solution: https://www.youtube.com/watch?v=9gG1Aaekq00

class Solution {
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        if (getMissingNum(nums, len-1) < k) {
            return nums[len-1] + k - getMissingNum(nums, len-1);
        }

        int start = 0;
        int end = len-1;
        while (start +1 < end) {
            int mid = start + (end - start) /2;
            if (getMissingNum(nums, mid) < k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return nums[start] + k - getMissingNum(nums, start);
    }

    private int getMissingNum(int[] nums, int index) {
        return nums[index] - nums[0] - index;
    }
}