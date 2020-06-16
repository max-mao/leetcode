//https://leetcode.com/problems/largest-number/
//Given a list of non negative integers, arrange them such that they form the largest number.
//
//        Example 1:
//
//        Input: [10,2]
//        Output: "210"

class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        Comparator<String> myComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String one = s1 + s2;
                String second = s2 + s1;
                return second.compareTo(one);
            }
        };

        String[] sorted =  new String[nums.length];
        for (int i =0; i < nums.length; i++) {
            sorted[i] = Integer.toString(nums[i]);
        }


        Arrays.sort(sorted, myComparator);
        StringBuilder sb = new StringBuilder();
        for (String i : sorted) {
            sb.append(i);
        }

        if (sb.toString().charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}