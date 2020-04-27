//https://leetcode.com/problems/count-primes/
//Count the number of prime numbers less than a non-negative number, n.
//
//        Example:
//
//        Input: 10
//        Output: 4
//        Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
//

//solution : https://www.youtube.com/watch?v=eKp56OLhoQs&feature=emb_err_watch_on_yt
class Solution {
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }

        boolean[] nonPrime = new boolean[n];
        nonPrime[0] = true;
        nonPrime[1] = true;

        for (int i = 2; i < Math.sqrt(n); i ++) {
            if (!nonPrime[i]) {
                for (int j = 2; j * i < n; j ++) {
                    nonPrime[i * j] = true;
                }
            }
        }

        int result = 0;
        for (int i = 2; i < n; i ++) {
            if (!nonPrime[i]) {
                result ++;
            }
        }

        return result;

    }

}