//https://leetcode.com/problems/k-diff-pairs-in-an-array/
//Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
//
//A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
//
//0 <= i < j < nums.length
//|nums[i] - nums[j]| == k
//Notice that |val| denotes the absolute value of val.
//
//
//
//Example 1:
//
//Input: nums = [3,1,4,1,5], k = 2
//Output: 2
//Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
//Although we have two 1s in the input, we should only return the number of unique pairs.

class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) {
                    res++;
                }
            } else if (map.containsKey(entry.getKey() + k)) {
                res++;
            }
        }

        return res;
    }
}