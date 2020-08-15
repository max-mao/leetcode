//https://leetcode.com/problems/group-shifted-strings/
//Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
//        "abc" -> "bcd" -> ... -> "xyz"
//        Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
//        Example:
//
//        Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
//        Output:
//        [
//        ["abc","bcd","xyz"],
//        ["az","ba"],
//        ["acef"],
//        ["a","z"]
//        ]

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String hash = hashCode(str);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }
            map.get(hash).add(str);
        }
        result.addAll(map.values());
        return result;
    }

    private String hashCode(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i ++) {
            int cur = str.charAt(i) - str.charAt(0);
            cur = cur < 0 ? cur + 26 : cur;
            sb.append(cur);
            sb.append("-");
        }

        return sb.toString();
    }
}