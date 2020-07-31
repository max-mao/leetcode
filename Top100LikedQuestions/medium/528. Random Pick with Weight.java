//https://leetcode.com/problems/random-pick-with-weight/
//Given an array w of positive integers, where w[i] describes the weight of index i(0-indexed), write a function pickIndex which randomly picks an index in proportion to its weight.
//
//        For example, given an input list of values w = [2, 8], when we pick up a number out of it, the chance is that 8 times out of 10 we should pick the number 1 as the answer since it's the second element of the array (w[1] = 8).
//
//
//
//        Example 1:
//
//        Input
//        ["Solution","pickIndex"]
//        [[[1]],[]]
//        Output
//        [null,0]
//
//        Explanation
//        Solution solution = new Solution([1]);
//        solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.

class Solution {
    int[] wSums;
    Random random;

    public Solution(int[] w) {
        this.wSums = new int[w.length];
        wSums[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            wSums[i] += wSums[i-1] + w[i];
        }
        random = new Random();
    }

    public int pickIndex() {
        int ran = random.nextInt(wSums[wSums.length -1]) + 1;
        int left = 0, right = wSums.length -1;

        while (left < right) {
            int mid = left + (right - left)/2;
            if (wSums[mid] == ran) {
                return mid;
            } else if (wSums[mid] > ran) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */