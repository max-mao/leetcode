//https://leetcode.com/problems/longest-valid-parentheses/
//Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
//
//        Example 1:
//
//        Input: "(()"
//        Output: 2
//        Explanation: The longest valid parentheses substring is "()"

//Stack: https://www.youtube.com/watch?v=r0-zx5ejdq0
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int result = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.peek() != -1 && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                    result = Math.max(result, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }

        return result;
    }
}

//solution 2: O(1)空间，很巧妙
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0;
        int left = 0, right = 0;
        int start = 0;
        while (start < s.length()) {
            if (s.charAt(start) == '(') {
                left ++;
            } else {
                right ++;
            }
            if (left == right) {
                result = Math.max(result, left * 2);
            }
            if(right > left) {
                left = 0;
                right = 0;
            }
            start ++;
        }

        start = s.length() -1;
        left = 0;
        right = 0;
        while (start >= 0) {
            if (s.charAt(start) == '(') {
                left ++;
            } else {
                right ++;
            }
            if (left == right) {
                result = Math.max(result, left *2);
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
            start --;
        }

        return result;
    }

}

