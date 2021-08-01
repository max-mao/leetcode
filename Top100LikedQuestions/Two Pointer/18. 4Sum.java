//https://leetcode.com/problems/4sum/
////
//Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
//
//        0 <= a, b, c, d < n
//a, b, c, and d are distinct.
//        nums[a] + nums[b] + nums[c] + nums[d] == target
//        You may return the answer in any order.
//
//
//
//        Example 1:
//
//        Input: nums = [1,0,-1,0,-2,2], target = 0
//        Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

//solution: https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int index, int k) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (k == 2) {
            int start = index;
            int end = len - 1;
            while (start < end) {
                if (nums[start] + nums[end] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[start]);
                    list.add(nums[end]);
                    res.add(list);
                    while (start +1 < len && nums[start] == nums[start +1]) {
                        start ++;
                    }
                    while (end - 1 >= 0 && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    start ++;
                    end --;
                } else if (nums[start] + nums[end] < target) {
                    start ++;
                } else {
                    end --;
                }
            }
        } else {
            for (int i = index; i < len; i++) {
                int cur = nums[i];
                List<List<Integer>> temp = kSum(nums, target - cur, i+1, k -1);
                if (temp != null) {
                    for (List<Integer> list : temp) {
                        list.add(0, cur);
                        res.add(list);
                    }
                }
                while (i+1 < len && nums[i] == nums[i+1]) {
                    i++;
                }
            }
        }
        return res;
    }
}