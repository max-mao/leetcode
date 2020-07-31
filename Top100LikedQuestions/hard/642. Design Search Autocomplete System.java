//https://leetcode.com/problems/design-search-autocomplete-system/
class AutocompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> child;
        Map<String, Integer> counts;
        public TrieNode () {
            this.child = new HashMap<>();
            this.counts = new HashMap<>();
        }
    }

    TrieNode root;
    String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";

        for (int i =0; i < sentences.length; i++) {
            build(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            build(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        } else {
            prefix = prefix + c;
            TrieNode cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (cur.child.get(prefix.charAt(i)) == null) {
                    return new ArrayList<String>();
                }
                cur = cur.child.get(prefix.charAt(i));
            }

            PriorityQueue<Map.Entry<String, Integer>> heap =
                    new PriorityQueue<>((a, b) -> (a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()));

            heap.addAll(cur.counts.entrySet());
            ArrayList<String> result = new ArrayList<>();
            int k = 3;
            while (k >0 && !heap.isEmpty()) {
                result.add(heap.poll().getKey());
                k --;
            }

            return result;
        }
    }


    private void build(String str, int count) {
        TrieNode cur = root;
        for (int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!cur.child.containsKey(c)) {
                cur.child.put(c, new TrieNode());
            }
            cur = cur.child.get(c);
            cur.counts.put(str, cur.counts.getOrDefault(str, 0) + count);
        }
    }


}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */