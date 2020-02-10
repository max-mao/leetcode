//https://leetcode.com/problems/group-anagrams/
//Given an array of strings, group anagrams together.
//
//        Example:
//
//        Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//        Output:
//        [
//        ["ate","eat","tea"],
//        ["nat","tan"],
//        ["bat"]
//        ]

//solution 1:
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        int[][] map = new int[strs.length][26];

        for (int i = 0; i < strs.length; i ++) {
            String cur = strs[i];
            for (int j = 0; j < cur.length(); j++) {
                map[i][cur.charAt(j) - 'a'] ++;
            }
        }

        boolean[] visited = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            List<String> list = getAnagrams(strs, map, i, visited);
            if (list != null) {
                result.add(list);
            }
        }

        return result;
    }

    private List<String> getAnagrams(String[] strs, int[][] map, int index, boolean[] visited) {
        if(visited[index]) {
            return null;
        }
        List<String> result = new ArrayList<>();
        visited[index] = true;
        result.add(strs[index]);

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (compareSame(map, i, index)) {
                result.add(strs[i]);
                visited[i] = true;
            }
        }

        return result;
    }

    private boolean compareSame(int[][] map, int index1, int index2) {
        for (int i = 0; i < 26; i ++) {
            if (map[index1][i] != map[index2][i]) {
                return false;
            }
        }

        return true;
    }
}

// solution 2：
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i ++) {
            String cur = strs[i];
            char[] arr = cur.toCharArray();
            Arrays.sort(arr);
            String new_string = String.valueOf(arr);

            if (map.containsKey(new_string)) {
                map.get(new_string).add(cur);
            } else {
                List<String> new_list = new ArrayList<String>();
                new_list.add(cur);
                map.put(new_string, new_list);
            }
        }

        for(List<String> list : map.values()) {
            result.add(list);
        }

        return result;
    }
}