//https://leetcode.com/problems/divide-chocolate/
//You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
//
//You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
//
//Being generous, you will eat the piece with the minimum total sweetn
// ess and give the other pieces to your friends.
//
//Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
//
//
//
//Example 1:
//
//Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
//Output: 6
//Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

//solution: https://www.youtube.com/watch?v=Ppy9lvyMnuc&ab_channel=MichaelMuinos
class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int start = 1;
        int end = 0;
        for (int i =0; i < sweetness.length; i++) {
            end += sweetness[i];
        }
        end = end / (K +1);

        while (start < end) {
            int mid = (start + end + 1) /2;
            if (canCut(sweetness, K, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private boolean canCut(int[] sweetness, int K, int mid) {
        int piece = 0;
        int sum = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
            if (sum >= mid) {
                piece ++;
                sum = 0;
            }
        }

        return piece >= K+1;
    }
}

