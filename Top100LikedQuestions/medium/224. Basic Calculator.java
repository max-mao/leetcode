//https://leetcode.com/problems/basic-calculator/
//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
//        Example 1:
//
//        Input: "1 + 1"
//        Output: 2
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int sum = 0;

        for (int i =0; i < s.length(); i ++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                sum += sign * num;
            }
            if (s.charAt(i) == '+') {
                sign = 1;
            }
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            if (s.charAt(i) == '(') {
                stack.push(sum);
                stack.push(sign);
                sum = 0;
                sign = 1;
            }
            if (s.charAt(i) == ')') {
                sum = stack.pop() * sum + stack.pop();
            }
        }

        return sum;
    }
}