//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
//Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
//
//
//
//        Example 1:
//
//        Input: nums = [8,2,4,7], limit = 4
//        Output: 2
//        Explanation: All subarrays are:
//        [8] with maximum absolute diff |8-8| = 0 <= 4.
//        [8,2] with maximum absolute diff |8-2| = 6 > 4.
//        [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//        [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//        [2] with maximum absolute diff |2-2| = 0 <= 4.
//        [2,4] with maximum absolute diff |2-4| = 2 <= 4.
//        [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//        [4] with maximum absolute diff |4-4| = 0 <= 4.
//        [4,7] with maximum absolute diff |4-7| = 3 <= 4.
//        [7] with maximum absolute diff |7-7| = 0 <= 4.
//        Therefore, the size of the longest subarray is 2.

// solution: https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609743/Java-Detailed-Explanation-Sliding-Window-Deque-O(N)
// video: https://www.youtube.com/watch?v=LDFZm4iB7tA
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxQ = new LinkedList<>();
        Deque<Integer> minQ = new LinkedList<>();
        int left = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            while(!maxQ.isEmpty() && maxQ.peekLast() < nums[i]) {
                maxQ.removeLast();
            }
            maxQ.addLast(nums[i]);

            while (!minQ.isEmpty() && minQ.peekLast() > nums[i]) {
                minQ.removeLast();
            }
            minQ.addLast(nums[i]);

            while (maxQ.peekFirst() - minQ.peekFirst() > limit) {
                if (maxQ.peekFirst() == nums[left]) {
                    maxQ.removeFirst();
                }
                if (minQ.peekFirst() == nums[left]) {
                    minQ.removeFirst();
                }
                left++;
            }

            result = Math.max(result, i - left +1);
        }

        return result;
    }
}