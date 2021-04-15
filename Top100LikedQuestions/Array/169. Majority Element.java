//https://leetcode.com/problems/majority-element/
//Given an array nums of size n, return the majority element.
//
//The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
//
//
//
//Example 1:
//
//Input: nums = [3,2,3]
//Output: 3
//Example 2:
//
//Input: nums = [2,2,1,1,1,2,2]
//Output: 2


//algorithm: https://blog.csdn.net/u014248127/article/details/79230221
class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count ++;
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        return major;

    }
}

