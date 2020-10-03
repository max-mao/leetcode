//https://leetcode.com/problems/bulls-and-cows/
//You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
//
//        Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
//
//        Please note that both secret number and friend's guess may contain duplicate digits.
//
//        Example 1:
//
//        Input: secret = "1807", guess = "7810"
//
//        Output: "1A3B"
//
//        Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

class Solution {
    public String getHint(String secret, String guess) {
        int A = 0;
        int B = 0;
        Map<Character, Integer> mapA = new HashMap<>();
        Map<Character, Integer> mapB = new HashMap<>();

        for (int i = 0 ; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A ++;
            }
            mapA.put(secret.charAt(i), mapA.getOrDefault(secret.charAt(i), 0)+ 1);
            mapB.put(guess.charAt(i), mapB.getOrDefault(guess.charAt(i), 0)+ 1);
        }

        for (Map.Entry<Character, Integer> entry : mapA.entrySet()) {
            char c = entry.getKey();
            if (mapB.containsKey(c)) {
                B += Math.min(entry.getValue(), mapB.get(c));
            }
        }

        return A + "A" + (B-A) + "B";

    }
}