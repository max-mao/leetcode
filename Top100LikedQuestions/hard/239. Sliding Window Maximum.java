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

//brute force
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k+1];

        for (int i = 0; i < nums.length - k+1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }
}

//Deque，解释：https://www.youtube.com/watch?v=fbkvdWUS5Ic&t=1s
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[0];
        }
        int[] result = new int[nums.length - k +1];
        Deque<Integer> deque = new LinkedList<>();
        int i = 0;
        while (i < nums.length) {
            if (!deque.isEmpty() && deque.peekFirst() == i -k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
            if (i >= k -1) {
                result[i-k+1] = nums[deque.peekFirst()];
            }
            i++;
        }

        return result;
    }
}