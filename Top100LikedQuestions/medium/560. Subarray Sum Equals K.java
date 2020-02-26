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

        int[] prefix_sum = new int[nums.length + 1];
        prefix_sum[0] = 0;
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i-1];
            prefix_sum[i] = sum;
        }

        for (int start = 0; start < nums.length; start++) {
            for (int end = start +1; end <= nums.length; end++) {
                if (prefix_sum[end] - prefix_sum[start] == k) {
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