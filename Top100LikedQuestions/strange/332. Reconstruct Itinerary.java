//https://leetcode.com/problems/reconstruct-itinerary/
//You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
//
//All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
//
//For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
//
//
//
//Example 1:
//
//
//Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//Output: ["JFK","MUC","LHR","SFO","SJC"]

//https://www.youtube.com/watch?v=LKSdX31pXjY

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> result = new LinkedList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<String>());
            }
            graph.get(from).add(to);
        }

        dfs(result, graph, "JFK");
        return result;
    }

    private void dfs(LinkedList<String> result, Map<String, PriorityQueue<String>> graph, String from) {
        PriorityQueue<String> nexts = graph.get(from);

        while (nexts != null && !nexts.isEmpty()) {
            String next = nexts.poll();
            dfs(result, graph, next);
        }

        result.addFirst(from);
    }
}