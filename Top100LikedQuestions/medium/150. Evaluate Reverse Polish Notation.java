//https://leetcode.com/problems/evaluate-reverse-polish-notation/
//Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
//Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
//Note:
//
//Division between two integers should truncate toward zero.
//The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
//Example 1:
//
//Input: ["2", "1", "+", "3", "*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int right = 0;

        while (right < tokens.length) {
            if (!isSign(tokens[right])) {
                int num = Integer.parseInt(tokens[right]);
                stack.push(num);
            } else {
                int second = stack.pop();
                int first = stack.pop();

                if (tokens[right].equals("+")) {
                    stack.push(first + second);
                } else if (tokens[right].equals("-")) {
                    stack.push(first - second);
                } else if (tokens[right].equals("*")) {
                    stack.push(first * second);
                } else if (tokens[right].equals("/")) {
                    stack.push(first / second);
                }
            }
            right ++;
        }

        return stack.pop();
    }

    private boolean isSign(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        }

        return false;
    }
}