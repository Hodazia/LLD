package LRUCache;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node next;
    Node prev;

    Node(int key, int value)
    {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}


class LRUCache {
    private int capacity;
    private Node head;
    private Node tail;
    private Map<Integer,Node> cacheMap;

    LRUCache(int capacity)
    {
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.cacheMap = new HashMap<>();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    int get(int key)
    {
        // get a value from a key
        if(!cacheMap.containsKey(key))
        {
            // it does not contains the key,
            return -1;
        }
        Node currNode = cacheMap.get(key);
        remove(currNode);
        add(currNode);
        return currNode.value;

    }

    void put(int key, int value)
    {
        if(cacheMap.containsKey(key))
        {
            Node oldNode = cacheMap.get(key);
            remove(oldNode);
        }

        Node node = new Node(key, value);
        cacheMap.put(key, node);
        add(node);
        if(cacheMap.size() > capacity)
        {
            // 1 2 3
            Node beforeLast = tail.prev;
            remove(beforeLast);
            cacheMap.remove(beforeLast.key);
        }

    }

    public void add(Node node)
    {
        // add a node after head 
        Node nextnode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextnode;
        nextnode.prev = node;
    }
    public void remove(Node node)
    {
        // remove the last node, before the tail
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

    }
}

public class solution {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
