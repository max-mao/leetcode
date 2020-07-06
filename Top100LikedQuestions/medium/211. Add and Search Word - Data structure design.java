//https://leetcode.com/problems/add-and-search-word-data-structure-design/
//Design a data structure that supports the following two operations:
//
//        void addWord(word)
//        bool search(word)
//        search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
//
//        Example:
//
//        addWord("bad")
//        addWord("dad")
//        addWord("mad")
//        search("pad") -> false
//        search("bad") -> true
//        search(".ad") -> true
//        search("b..") -> true

class WordDictionary {
    class TireNode {
        TireNode[] next;
        boolean isEnd;
        public TireNode() {
            this.next = new TireNode[26];
            this.isEnd = false;
        }
    }

    TireNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TireNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TireNode node = root;
        for (char c : word.toCharArray()) {
            int index = c -'a';
            if (node.next[index] == null) {
                node.next[index] = new TireNode();
            }
            node = node.next[index];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word, 0, root);
    }

    private boolean match(String word, int index, TireNode node) {
        if (node == null || word == null) {
            return false;
        }
        if (index == word.length() ) {
            return node.isEnd;
        }
        if (word.charAt(index) != '.') {
            int cur = word.charAt(index) -'a';
            if (node.next[cur] == null) {
                return false;
            } else {
                return match(word, index +1, node.next[cur]);
            }
        } else {
            for (int i = 0; i < 26; i ++) {
                if (node.next[i] != null) {
                    if (match(word, index +1, node.next[i])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */