//https://leetcode.com/problems/reorganize-string/
//Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
//
//        If possible, output any possible result.  If not possible, return the empty string.
//
//        Example 1:
//
//        Input: S = "aab"
//        Output: "aba"
//        Example 2:
//
//        Input: S = "aaab"
//        Output: ""


//solution: https://www.youtube.com/watch?v=zaM_GLLvysw
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int times = map.getOrDefault(c, 0) +1;
            if ((S.length() + 1) / 2 < times) {
                return "";
            }
            map.put(c, times);
        }

        PriorityQueue<Character> heap = new PriorityQueue<>((a, b) -> (map.get(b) - map.get(a)));
        heap.addAll(map.keySet());
        StringBuilder sb = new StringBuilder();

        while (heap.size() > 1) {
            char first = heap.poll();
            char second = heap.poll();
            sb.append(first);
            sb.append(second);
            map.put(first, map.get(first) -1);
            map.put(second, map.get(second) -1);
            if (map.get(first) > 0) {
                heap.add(first);
            }
            if (map.get(second) > 0) {
                heap.add(second);
            }
        }

        if (!heap.isEmpty()) {
            if (map.get(heap.peek()) > 1) {
                return "";
            }
            sb.append(heap.poll());
        }

        return sb.toString();
    }
}