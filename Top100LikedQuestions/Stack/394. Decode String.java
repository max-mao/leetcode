//https://leetcode.com/problems/decode-string/
//Given an encoded string, return its decoded string.
//
//        The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
//        You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
//        Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
//
//        Examples:
//
//        s = "3[a]2[bc]", return "aaabcbc".
//        s = "3[a2[c]]", return "accaccacc".
//        s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

class Solution {
    public String decodeString(String s) {
        if (s == null) {
            return null;
        }
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int index = 0;
        String cur = "";

        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int start = index;
                int end = index;
                while (Character.isDigit(s.charAt(end))) {
                    end ++;
                }
                int num = Integer.parseInt(s.substring(start, end));
                numStack.push(num);
                index = end;
            } else if (s.charAt(index) == '[') {
                strStack.push(cur);
                cur = "";
                index ++;
            } else if (s.charAt(index) == ']') {
                StringBuilder sb = new StringBuilder();
                sb.append(strStack.pop());
                int times = numStack.pop();
                for (int i =0; i < times; i ++) {
                    sb.append(cur);
                }
                cur = sb.toString();
                index ++;
            } else {
                cur += s.charAt(index);
                index ++;
            }
        }
        return cur;
    }
}