//https://leetcode.com/problems/valid-number/
//Validate if a given string can be interpreted as a decimal number.
//
//        Some examples:
//        "0" => true
//        " 0.1 " => true
//        "abc" => false
//        "1 a" => false
//        "2e10" => true
//        " -90e3   " => true
//        " 1e" => false
//        "e3" => false
//        " 6e-1" => true
//        " 99e2.5 " => false
//        "53.5e93" => true
//        " --6 " => false
//        "-+3" => false
//        "95a54e53" => false

//solution: https://www.youtube.com/watch?v=5gmtCtAooZE
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if (s == null || s.length() == 0) {
            return false;
        }

        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                numSeen = true;
            } else if (cur == '.') {
                if (eSeen || dotSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (cur == 'e') {
                if (!numSeen || eSeen) {
                    return false;
                }
                eSeen = true;
                numSeen = false;
            } else if (cur == '-' || cur == '+') {
                if (i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numSeen;
    }
}