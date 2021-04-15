//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
//Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
//
//We repeatedly make k duplicate removals on s until we no longer can.
//
//Return the final string after all such duplicate removals have been made.
//
//It is guaranteed that the answer is unique.
//
//
//
//Example 1:
//
//Input: s = "abcd", k = 2
//Output: "abcd"
//Explanation: There's nothing to delete.

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!stack.isEmpty() && stack.peek().val == cur) {
                stack.peek().time++;
            } else {
                stack.push(new Node(cur, 1));
            }
            if (stack.peek().time == k) {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Node n : stack) {
            for (int i = 0; i < n.time; i++) {
                sb.append(n.val);
            }
        }

        return sb.toString();
    }

    class Node {
        char val;
        int time;
        public Node (char val, int time) {
            this.val = val;
            this.time = time;
        }
    }
}