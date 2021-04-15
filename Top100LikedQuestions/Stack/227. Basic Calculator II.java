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
        s = s.replaceAll(" ", "");
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        char sign = '+';
        int num = 0;

        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int start = index;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    index ++;
                }
                num = Integer.parseInt(s.substring(start, index));
            }

            if (sign == '+') {
                stack.push(num);
            } else if (sign == '-') {
                stack.push(-num);
            } else if (sign == '*') {
                int prev = stack.pop();
                stack.push(prev*num);
            } else if (sign == '/') {
                int prev = stack.pop();
                stack.push(prev / num);
            }
            if (index < s.length()) {
                sign = s.charAt(index);
                index++;
            }
        }

        int sum = 0;
        for (int i : stack) {
            sum += i;
        }

        return sum;
    }
}