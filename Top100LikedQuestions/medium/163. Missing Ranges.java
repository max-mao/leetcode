//https://leetcode.com/problems/missing-ranges/

//Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//
//        Example:
//
//        Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
//        Output: ["2", "4->49", "51->74", "76->99"]

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                result.add(lower +"");
            } else {
                result.add(lower +"->" + upper);
            }
            return result;
        }

        int first = nums[0];
        if (first != lower) {
            if (lower + 1 < first) {
                String s = lower + "->" + (first - 1);
                result.add(s);
            } else {
                result.add(lower + "");
            }
        }

        for (int i = 0; i < nums.length - 1; i ++) {
            int cur = nums[i];
            int next = nums[i +1];
            if (cur == next || cur +1 == next) {
                continue;
            }
            if (cur + 2 < next) {
                String s = cur +1 + "->" + (next -1);
                result.add(s);
            } else {
                result.add(cur + 1 +"");
            }
        }

        int last = nums[nums.length -1];
        if (last != upper) {
            if (last + 1 < upper) {
                String s = last + 1 + "->" + upper;
                result.add(s);
            } else {
                result.add(upper + "");
            }
        }

        return result;
    }
}