//https://leetcode.com/problems/letter-combinations-of-a-phone-number/

//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
//
//        A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//        Example:
//
//        Input: "23"
//        Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String[] chars = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        dfs(digits, chars, result, 0, "");
        return result;
    }

    private void dfs(String digits, String[] chars,
                     List<String> result, int index,
                     String cur_string) {
        if (index == digits.length()) {
            result.add(cur_string);
            return;
        }
        int number = Character.getNumericValue(digits.charAt(index));

        String char_string = chars[number];

        for (int i = 0; i < char_string.length(); i ++) {
            String next_string = cur_string + char_string.substring(i, i+1);
            dfs(digits, chars, result, index + 1, next_string);
        }
    }
}