//https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
// Given an array of integers arr and an integer target.
//
//You have to find two non-overlapping sub-arrays of arr each with sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
//
//Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
//
//
//
//Example 1:
//
//Input: arr = [3,2,2,4,3], target = 3
//Output: 2
//Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.


//solution: https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/discuss/685486/JAVA-O(N)-Time-Two-Pass-Solution-using-HashMap.

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            map.put(sum, i);
        }
        int leftLen = Integer.MAX_VALUE, result = Integer.MAX_VALUE;
        int new_sum = 0;
        for (int i = 0; i < arr.length; i++) {
            new_sum += arr[i];
            if (map.containsKey(new_sum - target)) {
                leftLen = Math.min(leftLen, i - map.get(new_sum - target));
            }

            if (map.containsKey(new_sum + target) && leftLen != Integer.MAX_VALUE) {
                result = Math.min(result, map.get(new_sum + target) - i + leftLen);
            }
        }

        return result == Integer.MAX_VALUE? -1 : result;

    }
}