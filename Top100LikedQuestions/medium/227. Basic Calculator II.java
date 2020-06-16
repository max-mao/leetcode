//https://leetcode.com/problems/basic-calculator-ii/
//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
//
//        Example 1:
//
//        Input: "3+2*2"
//        Output: 7

class Solution {
    public int calculate(String s) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char lastSign = '+';

        for (int i = 0; i < s.length(); i ++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() -1) {
                if (lastSign == '+') {
                    stack.push(num);
                }
                if (lastSign == '-') {
                    stack.push(-num);
                }
                if (lastSign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (lastSign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                lastSign = s.charAt(i);
            }
        }

        for (int i : stack) {
            sum += i;
        }

        return sum;
    }
}