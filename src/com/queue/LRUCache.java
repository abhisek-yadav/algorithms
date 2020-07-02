package com.queue;

import java.util.*;

public class LRUCache {

    private Deque deque;
    private Set<Integer> hash;
    private int size;


    public LRUCache(int size) {
        this.deque = new ArrayDeque();
        this.hash = new HashSet<>();
        this.size = size;
    }

    public void refer(int element) {

        if (!hash.contains(element)) {
            if (deque.size() == size)
                deque.removeLast();

        } else {
            deque.remove(element);
        }

        deque.push(element);

    }

    public void display() {

        Iterator<Integer> i = deque.iterator();

        while (i.hasNext())
            System.out.print(i.next() + " ");

    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(4);

        cache.refer(1);
        cache.refer(2);
        cache.refer(3);
        cache.refer(4);
        cache.refer(1);
        cache.refer(2);
        cache.refer(5);
        cache.refer(1);
        cache.refer(2);
        cache.refer(3);
        cache.refer(4);
        cache.refer(5);


        cache.display();
    }

}
