//https://leetcode.com/problems/maximum-swap/
//Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
//
//        Example 1:
//        Input: 2736
//        Output: 7236
//        Explanation: Swap the number 2 and the number 7.
//        Example 2:
//        Input: 9973
//        Output: 9973
//        Explanation: No swap.

class Solution {
    public int maximumSwap(int num) {
        String str = Integer.toString(num);
        int[] nums = new int[str.length()];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = str.charAt(i) - '0';
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int cur = nums[i];
            int max = cur;
            int index = i;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] >= max) {
                    max = nums[j];
                    index = j;
                }
            }
            if (max > cur) {
                int temp = nums[i];
                nums[i] = max;
                nums[index] = temp;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i =0; i < nums.length; i++) {
            sb.append(nums[i]);
        }

        return Integer.parseInt(sb.toString());
    }
}