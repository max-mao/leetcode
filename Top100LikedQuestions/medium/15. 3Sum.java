//https://leetcode.com/problems/3sum/

//Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
//        Note:
//
//        The solution set must not contain duplicate triplets.
//
//        Example:
//
//        Given array nums = [-1, 0, 1, 2, -1, -4],
//
//        A solution set is:
//        [
//        [-1, 0, 1],
//        [-1, -1, 2]
//        ]

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i< nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            findTwoSum(-nums[i], i+1, nums, result);
        }

        return result;
    }

    private void findTwoSum(int target, int start, int[] nums, List<List<Integer>> result) {
        int left = start;
        int right = nums.length -1;

        while (left < right) {
            if (nums[left] + nums[right] == target) {
                List<Integer> list = new ArrayList<>();
                list.add(-target);
                list.add(nums[left]);
                list.add(nums[right]);
                result.add(list);
                left ++;
                right --;
                while (left < right && nums[left] == nums[left-1]) {
                    left ++;
                }
                while (left < right && nums[right] == nums[right+1]) {
                    right --;
                }

            } else if (nums[left] + nums[right] > target) {
                right --;
            } else {
                left ++;
            }
        }
    }
}