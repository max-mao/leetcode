//https://leetcode.com/problems/evaluate-division/
//Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
//
//        Example:
//        Given a / b = 2.0, b / c = 3.0.
//        queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
//        return [6.0, 0.5, -1.0, 1.0, -1.0 ].
//
//        The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
//
//        According to the example above:
//
//        equations = [ ["a", "b"], ["b", "c"] ],
//        values = [2.0, 3.0],
//        queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


//DFS:
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];

        Map<String, Map<String, Double>> map = new HashMap<>();

        for (int i = 0; i < equations.size(); i ++) {
            List<String> pair = equations.get(i);
            if (map.containsKey(pair.get(0))) {
                map.get(pair.get(0)).put(pair.get(1), values[i]);
            } else {
                Map<String, Double> child = new HashMap<>();
                child.put(pair.get(1), values[i]);
                map.put(pair.get(0), child);
            }

            if (map.containsKey(pair.get(1))) {
                map.get(pair.get(1)).put(pair.get(0), 1 / values[i]);
            } else {
                Map<String, Double> child = new HashMap<>();
                child.put(pair.get(0), 1 / values[i]);
                map.put(pair.get(1), child);
            }
        }

        for (int i = 0; i < queries.size(); i++) {
            List<String> pair = queries.get(i);
            if (!map.containsKey(pair.get(0)) || !map.containsKey(pair.get(1))) {
                result[i] = -1.0;
            } else {
                String start = pair.get(0);
                String end = pair.get(1);
                Set<String> visited = new HashSet<>();
                double val = helper(1.0, start, end, map, visited);
                result[i] = val;
            }
        }

        return result;
    }

    private double helper(double tempResult, String start, String end, Map<String, Map<String, Double>> map, Set<String> visited) {
        if (start.equals(end)) {
            return tempResult;
        }
        visited.add(start);
        Map<String, Double> child = map.get(start);
        for (Map.Entry<String, Double> entry : child.entrySet()) {
            String next = entry.getKey();
            if (visited.contains(next)) {
                continue;
            }
            double val = entry.getValue();
            double temp = helper(tempResult * val, next, end, map, visited);
            if ( temp != -1.0) {
                return temp;
            }
        }

        return - 1.0;
    }
}

//Solution2: Union find

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, String> parents = new HashMap<>();
        Map<String, Double> vals = new HashMap<>();
        double[] result = new double[queries.size()];

        for (int i = 0; i < equations.size(); i ++) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            union(x, y, parents, vals, values[i]);
        }

        for (int i =0; i < queries.size(); i ++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (parents.containsKey(x) && parents.containsKey(y) && find(x, parents, vals) == find(y, parents, vals)) {
                result[i] = vals.get(x) / vals.get(y);
            } else {
                result[i] = -1.0;
            }
        }

        return result;
    }

    private void union(String x, String y, Map<String, String> parents, Map<String, Double> vals, double value) {
        add(x, parents, vals);
        add(y, parents, vals);
        String px = find(x, parents, vals);
        String py = find(y, parents, vals);
        parents.put(px, py);
        vals.put(px, value* vals.get(y)/vals.get(x));
    }

    private void add(String x, Map<String, String> parents, Map<String, Double> vals) {
        if (parents.containsKey(x)) {
            return;
        }
        parents.put(x, x);
        vals.put(x, 1.0);
    }

    private String find(String x, Map<String, String> parents, Map<String, Double> vals) {
        String p = parents.get(x);
        if (x != p) {
            String pp = find(p, parents, vals);
            parents.put(x, pp);
            double n1 = vals.get(x);
            double n2 = vals.get(p);
            vals.put(x, n1 * n2);
        }

        return parents.get(x);
    }
}