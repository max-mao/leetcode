//https://leetcode.com/problems/lfu-cache/
//Design and implement a data structure for Least Frequently Used (LFU) cache.
//
//Implement the LFUCache class:
//
//LFUCache(int capacity) Initializes the object with the capacity of the data structure.
//int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
//void put(int key, int value) Sets or inserts the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be evicted.
//Notice that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
//
//Follow up:
//Could you do both operations in O(1) time complexity?


//solution: https://leetcode.com/problems/lfu-cache/discuss/207673/Python-concise-solution-**detailed**-explanation%3A-Two-dict-%2B-Doubly-linked-list

class LFUCache {
    class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoubleLinkedList {
        Node head;
        Node tail;
        int freq;
        public DoubleLinkedList(int freq) {
            this.freq = freq;
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }

        public void add(Node node) {
            Node n = head.next;
            head.next = node;
            node.prev = head;
            node.next = n;
            n.prev = node;
        }

        public void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public Node pop() {
            if (isEmpty()) {
                return null;
            }
            Node last = tail.prev;
            delete(last);

            return last;
        }
    }

    int capacity, size, minFreq;
    Map<Integer, Node> nodeMap;
    Map<Integer, DoubleLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 1;
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        freqMap.put(1, new DoubleLinkedList(1));
    }

    private int update(Node node) {
        int freq = node.freq;
        freqMap.get(freq).delete(node);
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoubleLinkedList(node.freq)).add(node);
        while(freqMap.get(minFreq).isEmpty()) {
            minFreq++;
        }
        return node.value;
    }

    public int get(int key) {
        if(!nodeMap.containsKey(key)) {
            return -1;
        }
        Node node = nodeMap.get(key);
        return update(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            update(node);
            return;
        }

        if (size >= capacity) {
            Node old = freqMap.get(minFreq).pop();
            nodeMap.remove(old.key);
            size--;
        }
        Node node = new Node(key, value);
        freqMap.get(1).add(node);
        nodeMap.put(key, node);
        minFreq = 1;
        size ++;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */