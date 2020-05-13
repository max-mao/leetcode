//https://leetcode.com/problems/median-of-two-sorted-arrays/
//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//        Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//        You may assume nums1 and nums2 cannot be both empty.
//
//        Example 1:
//
//        nums1 = [1, 3]
//        nums2 = [2]
//
//        The median is 2.0

//Binary search: https://www.youtube.com/watch?v=LPFhl65R7ww&t=1309s
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = nums1.length;

        while (start <= start) {
            int partition1 = (start + end)/2;
            int partition2 = (len1 + len2 +1)/2 - partition1;

            int nums1Left = partition1 == 0? Integer.MIN_VALUE : nums1[partition1-1];
            int nums1Right = partition1 == len1? Integer.MAX_VALUE : nums1[partition1];
            int nums2Left = partition2 == 0? Integer.MIN_VALUE : nums2[partition2-1];
            int nums2Right = partition2 == len2? Integer.MAX_VALUE : nums2[partition2];

            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                if ((len1 + len2) % 2 == 1) {
                    return (double) Math.max(nums1Left, nums2Left);
                } else {
                    return (double) (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) /2;
                }
            } else if (nums1Left > nums2Right) {
                end = partition1 -1;
            } else {
                start = partition1 + 1;
            }
        }

        return 0.0;
    }
}