//https://leetcode.com/problems/sequence-reconstruction/
//Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
//
//
//        Example 1:
//
//        Input: org = [1,2,3], seqs = [[1,2],[1,3]]
//        Output: false
//        Explanation: [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

// solution : https://www.youtube.com/watch?v=Bqhf7zPMdaU&ab_channel=RenZhang

class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Set<Integer>> graph = buildGraph(seqs, indegree);
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 1) {
                return false;
            }
            int cur = queue.poll();
            if(index == org.length || cur != org[index++]) return false;
            Set<Integer> nexts = graph.get(cur);
            if (nexts == null) {
                continue;
            }
            for (int next : nexts) {
                int new_indegree = indegree.get(next) - 1;
                indegree.put(next, new_indegree);
                if (new_indegree == 0) {
                    queue.add(next);
                }
            }
        }

        return index == org.length && index == graph.size();

    }

    private Map<Integer, Set<Integer>> buildGraph(List<List<Integer>> seqs, Map<Integer, Integer> indegree) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (List<Integer> list : seqs) {
            if (list.size() == 1) {
                if (!map.containsKey(list.get(0))) {
                    map.put(list.get(0), new HashSet<Integer>());
                    indegree.put(list.get(0), 0);
                }

            } else {
                for (int i =0; i < list.size()-1; i++) {
                    if (!map.containsKey(list.get(i))) {
                        map.put(list.get(i), new HashSet<Integer>());
                        indegree.put(list.get(i), 0);
                    }
                    if (!map.containsKey(list.get(i+1))) {
                        map.put(list.get(i+1), new HashSet<Integer>());
                        indegree.put(list.get(i+1), 0);
                    }

                    if (map.get(list.get(i)).add(list.get(i+1))) {
                        indegree.put(list.get(i+1), indegree.get(list.get(i+1)) +1);
                    }
                }
            }

        }

        return map;
    }
}