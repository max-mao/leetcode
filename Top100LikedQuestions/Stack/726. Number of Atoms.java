//https://leetcode.com/problems/number-of-atoms/
//Given a chemical formula (given as a string), return the count of each atom.
//
//The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
//
//One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
//
//Two formulas concatenated together to produce another formula. For example, H2O2He3Mg4 is also a formula.
//
//A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
//
//Given a formula, return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
//
//
//
//
//
//Example 1:
//
//Input: formula = "H2O"
//Output: "H2O"
//Explanation: The count of elements are {'H': 2, 'O': 1}.

class Solution {
    public String countOfAtoms(String formula) {
        if(formula == null || formula.length() == 0) {
            return "";
        }

        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        while (index < formula.length()) {
            if (formula.charAt(index) != '(' && formula.charAt(index) != ')') {
                int str_start = index;
                index++;
                while(index < formula.length() && Character.isLowerCase(formula.charAt(index))) {
                    index++;
                }
                String atom = formula.substring(str_start, index);
                int num = 1;
                int num_start = index;
                while(index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    index++;
                }
                if (num_start != index) {
                    num = Integer.parseInt(formula.substring(num_start, index));
                }
                map.put(atom, map.getOrDefault(atom, 0) + num);
            } else if (formula.charAt(index) == '(') {
                stack.push(map);
                map = new HashMap<>();
                index++;
            } else if (formula.charAt(index) == ')') {
                index++;
                int start = index;
                while(index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    index++;
                }
                int num = 1;
                if (start != index) {
                    num = Integer.parseInt(formula.substring(start, index));
                }
                Map<String, Integer> prev = stack.pop();
                for (String key : map.keySet()) {
                    map.put(key, map.get(key) * num);
                }
                for (String key : prev.keySet()) {
                    map.put(key, map.getOrDefault(key, 0) + prev.get(key));
                }
            }
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String key : list) {
            sb.append(key);
            if (map.get(key) > 1) {
                sb.append(map.get(key));
            }
        }

        return sb.toString();
    }
}