//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
//Say you have an array for which the ith element is the price of a given stock on day i.
//
//        Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//        You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//        After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//        Example:
//
//        Input: [1,2,3,0,2]
//        Output: 3
//        Explanation: transactions = [buy, sell, cooldown, buy, sell]

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        //hold数组记录第i 天hold 股票最大收益
        int[] hold = new int[prices.length];
        //unhold数组记录第i 天unhold 股票最大收益
        int[] unhold = new int[prices.length];
        hold[0] = - prices[0];

        for (int i = 1; i < prices.length; i ++) {
            if (i == 1) {
                //第一天hold 股票最大收益
                hold[i] = Math.max(-prices[0], -prices[1]);
            } else {
                hold[i] = Math.max(hold[i-1], unhold[i-2] - prices[i]);
            }
            unhold[i] = Math.max(hold[i-1] + prices[i], unhold[i-1]);
        }

        return unhold[prices.length - 1];
    }
}