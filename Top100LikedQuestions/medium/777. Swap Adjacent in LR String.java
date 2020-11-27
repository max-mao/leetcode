//https://leetcode.com/problems/swap-adjacent-in-lr-string/
//In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
//
//
//
//Example 1:
//
//Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
//Output: true
//Explanation: We can transform start to end following these steps:
//RXXLRXRXL ->
//XRXLRXRXL ->
//XRLXRXRXL ->
//XRLXXRRXL ->
//XRLXXRRLX
//Example 2:
//
//Input: start = "X", end = "L"
//Output: false

//solution: https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/217070/Python-using-corresponding-position-
class Solution {
    public boolean canTransform(String start, String end) {
        // XL -> LX : L move to left
        // RX -> XR : R move to right
        if (start.length() != end.length()) {
            return false;
        }
        if (!start.replace("X", "").equals(end.replace("X",""))) {
            return false;
        }
        int startIndex = 0;
        int endIndex = 0;
        while (startIndex < start.length() || endIndex < end.length()) {
            while (startIndex < start.length() && start.charAt(startIndex) == 'X') {
                startIndex++;
            }
            while (endIndex < end.length() && end.charAt(endIndex) == 'X') {
                endIndex++;
            }
            if (startIndex == start.length() || endIndex == end.length()) {
                return startIndex == endIndex;
            }
            if (start.charAt(startIndex) == 'L' && startIndex < endIndex)  {
                return false;
            }
            if (start.charAt(startIndex) == 'R' && startIndex > endIndex)  {
                return false;
            }
            startIndex++;
            endIndex++;
        }

        return true;
    }
}