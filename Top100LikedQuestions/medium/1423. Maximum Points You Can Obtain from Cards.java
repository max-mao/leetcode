//https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
//There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
//
//        In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
//
//        Your score is the sum of the points of the cards you have taken.
//
//        Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
//
//
//
//        Example 1:
//
//        Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//        Output: 12
//        Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.


// o(k): https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/discuss/597777/JavaPython-3-Sliding-window-O(k)-short-codes-w-brief-comments-and-analysis.
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int start = len - k;
        int res = 0;
        int max = 0;
        for (int i = start; i < len + k; i++) {
            max += cardPoints[i % len];
            if (i - start >= k) {
                max -= cardPoints[(i - k) % len];
            }
            res = Math.max(res, max);
        }

        return res;
    }
}

//o(n)
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        for (int i : cardPoints) {
            sum += i;
        }

        if (k == cardPoints.length) {
            return sum;
        }

        int window_len = cardPoints.length - k;

        int min = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int cur_sum = 0;
        while (right <= cardPoints.length -1) {
            int len = right - left + 1;
            if (len <= window_len) {
                cur_sum += cardPoints[right];
                if (len == window_len) {
                    min = Math.min(min, cur_sum);
                }
            } else {
                cur_sum += cardPoints[right];
                cur_sum -= cardPoints[left];
                min = Math.min(min, cur_sum);
                left++;
            }
            right++;
        }

        return sum - min;
    }
}