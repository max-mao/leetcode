//https://leetcode.com/problems/subarray-sum-equals-k/
//Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
//
//        Example 1:
//        Input:nums = [1,1,1], k = 2
//        Output: 2

//solution 1:
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i< nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j ++) {
                sum += nums[j];
                if (sum == k) {
                    count ++;
                }
            }
        }

        return  count;
    }
}

// Solution2: hash map
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> sum_map = new HashMap<>();
        //key是prefix sum，value是这个prefix sum 出现的次数
        sum_map.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum_map.containsKey(sum - k)) {
                count += sum_map.get(sum - k);
            }
            sum_map.put(sum, sum_map.getOrDefault(sum, 0) +1);
        }

        return count;
    }
}