//https://leetcode.com/problems/string-transforms-into-another-string/
//Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
//
//        In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
//
//        Return true if and only if you can transform str1 into str2.
//
//
//
//        Example 1:
//
//        Input: str1 = "aabcc", str2 = "ccdee"
//        Output: true
//        Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.

//solution: https://leetcode.com/problems/string-transforms-into-another-string/discuss/355382/JavaC%2B%2BPython-Need-One-Unused-Character
class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        if (str1.equals(str2)) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i =0; i < str1.length(); i++) {
            char cur = str1.charAt(i);
            if(map.containsKey(cur) && map.get(cur) != str2.charAt(i)) {
                return false;
            }
            map.put(cur,str2.charAt(i));
        }

        return new HashSet<Character>(map.values()).size() < 26;
    }
}