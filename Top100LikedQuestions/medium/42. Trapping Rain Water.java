//https://leetcode.com/problems/trapping-rain-water/
//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
//
//        The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
//
//        Example:
//
//        Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//        Output: 6

class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i =0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int floor = height[stack.pop()];
                if (!stack.isEmpty()) {
                    int wall = Math.min(height[i], height[stack.peek()]) - floor;
                    int len = i - stack.peek() - 1;
                    result += wall * len;
                }
            }
            stack.push(i);
        }

        return result;
    }
}