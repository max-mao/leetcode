//https://leetcode.com/problems/longest-consecutive-sequence/

//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
//        Your algorithm should run in O(n) complexity.
//
//        Example:
//
//        Input: [100, 4, 200, 1, 3, 2]
//        Output: 4
//        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

// solution 1: sort:
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1] + 1) {
                max++;
            } else if (nums[i] == nums[i-1]) {
                continue;
            } else {
                max = 1;
            }
            res = Math.max(res, max);
        }

        return res;
    }
}

/// solution2:
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int result = 1;
        int cur_num = nums[0];
        for (int i = 0; i< nums.length; i++) {

            if (!set.contains(nums[i] - 1)) {
                int len = 1;
                cur_num = nums[i];
                while (set.contains(cur_num + 1)) {
                    len ++;
                    cur_num = cur_num + 1;
                }
                result = Math.max(result, len);
            }
        }

        return result;
    }
}