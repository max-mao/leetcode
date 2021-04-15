class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int fast_num = nums[nums[0]];
        int slow_num = nums[0];

        // part 1: find the intersection
        while (fast_num != slow_num) {
            slow_num = nums[slow_num];
            fast_num = nums[nums[fast_num]];
        }


        // find the circle entry
        int new_start = 0;
        while (new_start != slow_num) {
            new_start = nums[new_start];
            slow_num = nums[slow_num];
        }

        return new_start;

    }
}