//https://leetcode.com/problems/hand-of-straights/
//Alice has a hand of cards, given as an array of integers.
//
//        Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
//
//        Return true if and only if she can.
//
//
//
//        Example 1:
//
//        Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
//        Output: true
//        Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].

//solution: https://www.youtube.com/watch?v=K7n_BQihPCM&t=187s&ab_channel=NickWhite

class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i: hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while (map.size() > 0) {
            int first = map.firstKey();
            for (int i = first; i < first + W; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }
                if (map.get(i) == 1) {
                    map.remove(i);
                } else {
                    map.put(i, map.get(i) -1);
                }
            }
        }

        return true;
    }
}