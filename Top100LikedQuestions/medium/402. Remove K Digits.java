//https://leetcode.com/problems/remove-k-digits/
//Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
//
//        Note:
//        The length of num is less than 10002 and will be ≥ k.
//        The given num does not contain any leading zero.
//        Example 1:
//
//        Input: num = "1432219", k = 3
//        Output: "1219"
//        Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.


//solution: https://leetcode.com/problems/remove-k-digits/discuss/629860/Java-or-C%2B%2B-or-Python3-or-easy-explanation
class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (k == 0) {
            return num;
        }
        if (k == len) {
            return "0";
        }

        Stack<Integer> stack = new Stack<>();
        int index = 0;
        while (index < num.length()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(index) - '0') {
                stack.pop();
                k --;
            }
            stack.push(num.charAt(index) - '0');
            index ++;
        }

        while (k > 0) {
            stack.pop();
            k --;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}