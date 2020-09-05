//https://leetcode.com/problems/split-array-into-consecutive-subsequences/
//Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.
//
//
//
//        Example 1:
//
//        Input: [1,2,3,3,4,5]
//        Output: True
//        Explanation:
//        You can split them into two consecutive subsequences :
//        1, 2, 3
//        3, 4, 5

//solution: https://www.youtube.com/watch?v=uJ8BAQ8lASE
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> possibleMap = new HashMap<>();

        for (int i : nums) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (countMap.get(cur) == 0) continue;
            if (possibleMap.getOrDefault(cur, 0) > 0) {
                countMap.put(cur, countMap.get(cur) -1);
                possibleMap.put(cur, possibleMap.get(cur) -1);
                possibleMap.put(cur+1, possibleMap.getOrDefault(cur+1, 0) +1);
            } else if (countMap.getOrDefault(cur + 1, 0) > 0
                    &&  countMap.getOrDefault(cur + 2, 0) > 0) {
                countMap.put(cur, countMap.get(cur) -1);
                countMap.put(cur +1, countMap.get(cur + 1) -1);
                countMap.put(cur + 2, countMap.get(cur+2) -1);
                possibleMap.put(cur + 3, possibleMap.getOrDefault(cur + 3, 0) +1);
            } else {
                return false;
            }
        }

        return true;
    }
}