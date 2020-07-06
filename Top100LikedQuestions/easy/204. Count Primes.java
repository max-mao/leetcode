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

        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i < Math.sqrt(n); i ++) {
            if (isPrime[i]) {
                for (int j =2; j * i < isPrime.length; j ++) {
                    isPrime[i *j] = false;
                }
            }
        }

        int result = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                result ++;
            }
        }

        return result;
    }
}