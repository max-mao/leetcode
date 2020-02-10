//https://leetcode.com/problems/product-of-array-except-self/
//Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
//
//        Example:
//
//        Input:  [1,2,3,4]
//        Output: [24,12,8,6]
//        Note: Please solve it without division and in O(n).

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];

        leftProduct[0] = 1;
        for (int i=1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }

        int rightProduct = 1;
        for (int i=nums.length - 2; i >= 0; i--) {
            rightProduct = rightProduct * nums[i + 1];
            leftProduct[i] = rightProduct * leftProduct[i];
        }

        return leftProduct;
    }
}