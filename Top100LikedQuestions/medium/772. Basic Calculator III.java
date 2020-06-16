//https://leetcode.com/problems/basic-calculator-iii/
//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
//        The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
//
//        You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
//
//        Some examples:
//
//        "1 + 1" = 2
//        " 6-4 / 2 " = 4
//        "2*(5+5*2)/3+(6/2+8)" = 21
//        "(2+6* 3+5- (3*14/7+2)*5)+3"=-12

//solution: https://www.youtube.com/watch?v=7rmxDqXf5vQ
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char lastSign = '+';

        for (int i = 0; i < s.length(); i ++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (s.charAt(i) == '(') {
                int left = 1;
                int j = i + 1;
                while (left > 0) {
                    if (s.charAt(j) == '(') {
                        left ++;
                    }
                    if (s.charAt(j) == ')') {
                        left --;
                    }
                    j++;
                }
                num = calculate(s.substring(i+1, j));
                i = j -1;
            }

            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (lastSign == '+') {
                    stack.push(num);
                } else if (lastSign == '-') {
                    stack.push(-num);
                } else if (lastSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (lastSign == '/') {
                    stack.push(stack.pop() / num);
                }
                lastSign = s.charAt(i);
                num = 0;
            }
        }

        int result = 0;
        for (int i: stack) {
            result += i;
        }

        return result;
    }
}