//https://leetcode.com/problems/implement-trie-prefix-tree/
//Implement a trie with insert, search, and startsWith methods.
//
//        Example:
//
//        Trie trie = new Trie();
//
//        trie.insert("apple");
//        trie.search("apple");   // returns true
//        trie.search("app");     // returns false
//        trie.startsWith("app"); // returns true
//        trie.insert("app");
//        trie.search("app");     // returns true

class Trie {

    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;

        public TrieNode() {
            this.map = new HashMap<>();
        }
    }

    /** Initialize your data structure here. */
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char curChar = word.charAt(i);
            if (!cur.map.containsKey(curChar)) {
                TrieNode new_node = new TrieNode();
                cur.map.put(curChar, new_node);
            }
            cur = cur.map.get(curChar);
        }
        cur.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i=0; i < word.length(); i ++) {
            char curChar = word.charAt(i);
            if (!cur.map.containsKey(curChar)) {
                return false;
            }
            cur = cur.map.get(curChar);
        }

        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i=0; i < prefix.length(); i ++) {
            char curChar = prefix.charAt(i);
            if (!cur.map.containsKey(curChar)) {
                return false;
            }
            cur = cur.map.get(curChar);
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */