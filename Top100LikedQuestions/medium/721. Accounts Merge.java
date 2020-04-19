//https://leetcode.com/problems/accounts-merge/
//Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//        Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//        After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
//
//        Example 1:
//        Input:
//        accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
//        Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
//        Explanation:
//        The first and third John's are the same person as they have the common email "johnsmith@mail.com".
//        The second John and Mary are different people as none of their email addresses are used by other accounts.
//        We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
//        ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

// solution : https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC%2B%2B-Union-Find
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owners = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> union = new HashMap<>();

        //Initial
        for (List<String> list : accounts) {
            for (int i =1; i < list.size(); i++) {
                parents.put(list.get(i), list.get(i));
                owners.put(list.get(i), list.get(0));
            }
        }

        //Union
        for (List<String> list : accounts) {
            String p = find(list.get(1), parents);
            for (int i =2; i < list.size(); i++) {
                parents.put(find(list.get(i), parents), p);
            }
        }

        // Put the same group into a Map, which is key by the parent
        for (List<String> list : accounts) {
            String p = find(list.get(1), parents);
            if (!union.containsKey(p)) {
                union.put(p, new TreeSet<String>());
            }
            for (int i =1; i < list.size(); i ++) {
                union.get(p).add(list.get(i));
            }
        }

        //Add the group into List
        List<List<String>> result = new ArrayList<>();
        for (String key : union.keySet()) {
            List<String> cur = new ArrayList<>(union.get(key));
            cur.add(0, owners.get(key));
            result.add(cur);
        }

        return result;
    }

    private String find(String s, Map<String, String> parents) {
        String p = parents.get(s);
        if (s != p) {
            String pp = find(p, parents);
            parents.put(s, pp);
        }

        return parents.get(s);
    }
}