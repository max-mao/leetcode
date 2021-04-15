//https://leetcode.com/problems/expression-add-operators/
//Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
//
//        Example 1:
//
//        Input: num = "123", target = 6
//        Output: ["1+2+3", "1*2*3"]
//        Example 2:
//
//        Input: num = "232", target = 8
//        Output: ["2*3+2", "2+3*2"]

//solution: https://www.youtube.com/watch?v=v05R1OIIg08&t=1491s
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }

        helper(num, "", 0, 0, 0, result, target);
        return result;
    }

    private void helper(String num, String path, int index, long prev, long val, List<String> result, int target) {
        if (index == num.length()) {
            if (val == target) {
                result.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            //0XXX is illegal
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long cur = Long.parseLong(num.substring(index, i+1));
            if (cur > Integer.MAX_VALUE) {
                break;
            }
            if (index == 0) {
                helper(num, cur +"", i+1, cur, cur, result, target);
            } else {
                helper(num, path + "+" + cur, i+1, cur, val + cur, result, target);
                helper(num, path + "-" + cur, i+1, -cur, val - cur, result, target);
                helper(num, path + "*" + cur, i+1, prev * cur, val - prev + prev * cur, result, target);
            }
        }
    }
}