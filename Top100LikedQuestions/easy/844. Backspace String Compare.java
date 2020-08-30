//https://leetcode.com/problems/backspace-string-compare/
//Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
//
//        Note that after backspacing an empty text, the text will continue empty.
//
//        Example 1:
//
//        Input: S = "ab#c", T = "ad#c"
//        Output: true
//        Explanation: Both S and T become "ac".

//string builder
class Solution {
    public boolean backspaceCompare(String S, String T) {
        StringBuilder sb_s = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != '#') {
                sb_s.append(S.charAt(i));
            } else if (S.charAt(i) == '#') {
                if (sb_s.length() > 0) {
                    sb_s.deleteCharAt(sb_s.length() -1);
                }
            }
        }

        String new_s =sb_s.toString();

        StringBuilder sb_t = new StringBuilder();
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) != '#') {
                sb_t.append(T.charAt(i));
            } else if (T.charAt(i) == '#') {
                if (sb_t.length() > 0) {
                    sb_t.deleteCharAt(sb_t.length() -1);
                }
            }
        }

        String new_t =sb_t.toString();

        return new_s.equals(new_t);
    }
}
//solution 2: 2 pointer
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int S_pointer = S.length() - 1;
        int T_pointer = T.length() - 1;

        int S_skip = 0;
        int T_skip = 0;

        while (S_pointer >= 0 || T_pointer >= 0) {

            while (S_pointer >= 0) {
                if (S.charAt(S_pointer) == '#') {
                    S_skip ++;
                    S_pointer --;
                } else if (S_skip > 0) {
                    S_skip --;
                    S_pointer --;
                } else {
                    break;
                }
            }

            while (T_pointer >= 0) {
                if (T.charAt(T_pointer) == '#') {
                    T_skip ++;
                    T_pointer --;
                } else if (T_skip > 0) {
                    T_skip --;
                    T_pointer --;
                } else {
                    break;
                }
            }

            if (S_pointer >= 0 && T_pointer >= 0 && S.charAt(S_pointer) != T.charAt(T_pointer)) {
                return false;
            }

            if ((S_pointer >= 0) != (T_pointer >= 0)) {
                return false;
            }

            S_pointer --;
            T_pointer --;
        }

        return true;
    }
}