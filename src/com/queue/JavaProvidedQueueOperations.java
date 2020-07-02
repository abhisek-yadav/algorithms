package com.queue;

import java.util.*;

public class JavaProvidedQueueOperations {

    public static void main(String[] args) {

        Deque queue = new ArrayDeque();

        queue.add(1);
        queue.add(3);
        queue.add(4);
        queue.add(7);
        queue.add(5);
        queue.add(2);
        queue.add(6);

        queue.remove(4);

        Iterator i = queue.descendingIterator();

        while (i.hasNext())
            System.out.print(i.next() + " ");


    }
}
