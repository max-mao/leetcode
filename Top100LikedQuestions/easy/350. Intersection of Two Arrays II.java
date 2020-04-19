//https://leetcode.com/problems/intersection-of-two-arrays-ii/
//Given two arrays, write a function to compute their intersection.
//
//        Example 1:
//
//        Input: nums1 = [1,2,2,1], nums2 = [2,2]
//        Output: [2,2]
//        Example 2:
//
//        Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//        Output: [4,9]

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) +1);
        }

        List<Integer> result = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                result.add(i);
                map.put(i, map.get(i) -1);
            }
        }

        int[] res = new int[result.size()];
        for (int i =0; i < res.length; i ++) {
            res[i] = result.get(i);
        }

        return res;
    }
}