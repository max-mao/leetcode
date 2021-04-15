//https://leetcode.com/problems/sliding-window-maximum/
//Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
//
//        Follow up:
//        Could you solve it in linear time?
//
//        Example:
//
//        Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//        Output: [3,3,5,5,6,7]
//        Explanation:
//
//        Window position                Max
//        ---------------               -----
//        [1  3  -1] -3  5  3  6  7       3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7

// Deque 双端队列 https://www.youtube.com/watch?v=fbkvdWUS5Ic
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k +1];
        Deque<Integer> dp = new LinkedList<>();

        int index = 0;
        while (index < nums.length) {
            if (!dp.isEmpty() && dp.peekFirst() < index - k + 1) {
                dp.pollFirst();
            }
            while (!dp.isEmpty() && nums[dp.peekLast()] < nums[index]) {
                dp.pollLast();
            }
            dp.addLast(index);

            if (index >= k-1) {
                result[index - k+1] = nums[dp.peekFirst()];
            }
            index++;
        }

        return result;
    }
}
