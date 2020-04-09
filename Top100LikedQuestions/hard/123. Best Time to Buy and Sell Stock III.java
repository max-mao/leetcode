//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
//Say you have an array for which the ith element is the price of a given stock on day i.
//
//        Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//        Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
//        Example 1:
//
//        Input: [3,3,5,0,0,3,1,4]
//        Output: 6
//        Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//        Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 思路，对于一个index i，分别算法从左边最大的profit，和右边最大profit
        // 一个数组记录左边profit，一个记录右边profit，最后对每个index profit相加，选出最大值

        //这个数组用来记录到当前天最大profit
        int[] leftProfit = new int[prices.length];
        int profit = 0;
        int min = prices[0];
        for(int i=0; i < leftProfit.length; i++) {
            if (prices[i] > min) {
                profit = Math.max(profit, prices[i] - min);
            } else {
                min = prices[i];
            }
            leftProfit[i] = profit;
        }

        //这个数组用来记录当前天之后最大profit
        int[] rightProfit = new int[prices.length];
        profit = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 1; i >=0; i --) {
            if (prices[i] < max) {
                profit = Math.max(profit, max - prices[i]);
            } else {
                max = prices[i];
            }
            rightProfit[i] = profit;
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int curProfit = 0;
            if (i == 0) {
                curProfit = rightProfit[i];
            } else {
                curProfit = leftProfit[i-1] + rightProfit[i];
            }
            maxProfit = Math.max(maxProfit, curProfit);
        }

        return maxProfit;
    }
}