//https://leetcode.com/problems/remove-comments/
//
//Input:
//        source = ["a/*comment", "line", "more_comment*/b"]
//        Output: ["ab"]
//        Explanation: The original source string is "a/*comment\nline\nmore_comment*/b", where we have bolded the newline characters.  After deletion, the implicit newline characters are deleted, leaving the string "ab", which when delimited by newline characters becomes ["ab"].

//solution: https://leetcode.com/problems/remove-comments/discuss/109197/One-pass-solution-in-Java
class Solution {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean isBlock = false;

        for (String s : source) {
            for (int i = 0; i < s.length(); i++) {
                if (isBlock) {
                    if (i + 1 < s.length() && s.charAt(i) == '*' && s.charAt(i+1) == '/') {
                        isBlock = false;
                        i++;
                    }
                } else {
                    if (i + 1 < s.length() && s.charAt(i) == '/' && s.charAt(i+1) == '/') {
                        break;
                    } else if (i + 1 < s.length() && s.charAt(i) == '/' && s.charAt(i+1) == '*') {
                        isBlock = true;
                        i++;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }

            if (!isBlock && sb.length() > 0) {
                result.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        return result;
    }
}