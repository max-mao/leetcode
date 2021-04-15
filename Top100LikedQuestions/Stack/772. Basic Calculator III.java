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
            if (index < s.length() && s.charAt(index) == '(') {
                int start = index;
                int left = 1;
                while (left > 0) {
                    index++;
                    if (s.charAt(index) == '(') {
                        left ++;
                    } else if (s.charAt(index) == ')') {
                        left --;
                    }
                }
                num = calculate(s.substring(start+1, index));
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