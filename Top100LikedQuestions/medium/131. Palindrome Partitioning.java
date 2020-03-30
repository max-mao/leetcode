//https://leetcode.com/problems/palindrome-partitioning/
//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//        Return all possible palindrome partitioning of s.
//
//        Example:
//
//        Input: "aab"
//        Output:
//        [
//        ["aa","b"],
//        ["a","a","b"]
//        ]

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        helper (s, 0, result, new ArrayList<String>());
        return result;
    }

    private void helper(String s, int index, List<List<String>> result, List<String> list) {
        if (index == s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i=index; i < s.length(); i++) {
            String cur = s.substring(index, i+1);
            if (isPalindrome(cur)) {
                list.add(cur);
                helper(s, i+1, result, list);
                list.remove(list.size()-1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }

        int left =0, right = str.length()-1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }

        return true;
    }
}