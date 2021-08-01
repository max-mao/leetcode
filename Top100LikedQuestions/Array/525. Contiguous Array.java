//https://leetcode.com/problems/contiguous-array/
//525. Contiguous Array
//Medium
//
//2976
//
//147
//
//Add to List
//
//Share
//Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
//
//
//
//Example 1:
//
//Input: nums = [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
//Example 2:
//
//Input: nums = [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int max_len = 0;
        int sum = 0;
        for (int i = 0 ; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                max_len = Math.max(max_len, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return max_len;
    }
}