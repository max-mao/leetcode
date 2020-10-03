//https://leetcode.com/problems/cracking-the-safe/
//There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
//
//        While entering a password, the last n digits entered will automatically be matched against the correct password.
//
//        For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.
//
//        Return any password of minimum length that is guaranteed to open the box at some point of entering it.
//
//
//
//        Example 1:
//
//        Input: n = 1, k = 2
//        Output: "01"
//        Note: "10" will be accepted too.
//        Example 2:
//
//        Input: n = 2, k = 2
//        Output: "00110"
//        Note: "01100", "10011", "11001" will be accepted too.

//solution: https://leetcode.com/problems/cracking-the-safe/discuss/153039/DFS-with-Explanations
//video solution: https://www.youtube.com/watch?v=kRdlLahVZDc

class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i =0; i < n; i++) {
            sb.append("0");
        }
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());
        int num = (int)Math.pow(k, n);
        dfs(sb, visited, num, n, k);

        return sb.toString();
    }

    private boolean dfs(StringBuilder sb, Set<String> visited, int num, int n, int k) {
        if (visited.size() == num) {
            return true;
        }

        String prefix = sb.substring(sb.length() - n +1);
        for (int i = 0; i < k; i++) {
            String next = prefix + i;
            if (visited.contains(next)) {
                continue;
            }
            visited.add(next);
            sb.append(i);
            if (dfs(sb, visited, num, n, k)) {
                return true;
            }
            visited.remove(next);
            sb.deleteCharAt(sb.length() -1);
        }

        return false;
    }
}