//https://leetcode.com/problems/remove-invalid-parentheses/

//Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//
//        Note: The input string may contain letters other than the parentheses ( and ).
//
//        Example 1:
//
//        Input: "()())()"
//        Output: ["()()()", "(())()"]

//BFS, explanation: https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        if (s== null) {
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        boolean found = false;

        queue.add(s);
        visited.add(s);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (isValid(cur)) {
                result.add(cur);
                found = true;
            }
            if (found) continue;

            for (int i=0; i < cur.length(); i++) {
                if (cur.charAt(i) != '(' && cur.charAt(i) != ')') {
                    continue;
                }
                String next = cur.substring(0, i) + cur.substring(i+1);
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }

        return result;
    }

    private boolean isValid(String str) {
        if (str == "") {
            return true;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count ++;
            } else if (str.charAt(i) == ')') {
                count --;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}