//https://leetcode.com/problems/accounts-merge/
//Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
//
//
//
//Example 1:
//
//Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//Explanation:
//The first and third John's are the same person as they have the common email "johnsmith@mail.com".
//The second John and Mary are different people as none of their email addresses are used by other accounts.
//We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
//['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

//solution: https://www.youtube.com/watch?v=J5YnIxbG3hw
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> email2Name = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String cur_email = account.get(i);
                email2Name.put(cur_email, name);
                graph.putIfAbsent(cur_email, new HashSet<>());

                if (i == 1) {
                    continue;
                }
                String prev = account.get(i-1);
                graph.get(cur_email).add(prev);
                graph.get(prev).add(cur_email);
            }
        }

        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String key : email2Name.keySet()) {
            List<String> list = new ArrayList<>();
            if (visited.contains(key)) {
                continue;
            }
            dfs(list, graph, visited, key);
            String name = email2Name.get(key);
            Collections.sort(list);
            list.add(0, name);
            res.add(list);
        }

        return res;
    }

    private void dfs(List<String> list, Map<String, Set<String>> graph, Set<String> visited, String key) {
        list.add(key);
        visited.add(key);
        for (String next : graph.get(key)) {
            if (visited.contains(next)) {
                continue;
            }
            dfs(list, graph, visited, next);
        }
    }
}