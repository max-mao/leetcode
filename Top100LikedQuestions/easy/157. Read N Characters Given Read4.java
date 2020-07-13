//https://leetcode.com/problems/read-n-characters-given-read4/
//Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
//
//
//
//        Method read4:
//
//        The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
//
//        The return value is the number of actual characters read.
//
//        Note that read4() has its own file pointer, much like FILE *fp in C.
//
//        Definition of read4:
//
//        Parameter:  char[] buf4
//        Returns:    int
//
//        Note: buf4[] is destination not source, the results from read4 will be copied to buf4[]
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        boolean isEnd = false;
        int curIndex = 0;
        char[] buf4 = new char[4];

        while (!isEnd && curIndex < n) {

            int curCount = read4(buf4);
            if (curCount != 4) {
                isEnd = true;
            }

            int len = Math.min(n - curIndex, curCount);
            for (int i = 0; i < len; i ++) {
                buf[curIndex + i] = buf4[i];
            }
            curIndex += len;
        }

        return curIndex;
    }
}