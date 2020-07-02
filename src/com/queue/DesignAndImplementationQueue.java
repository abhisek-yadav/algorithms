package com.queue;

public class DesignAndImplementationQueue {


    static class Stack {
        Queue q1 = new Queue();
        Queue q2 = new Queue();

        public boolean isEmpty() {
            return q1.isEmpty();
        }

        public boolean push(int element) {
            if (q1.isFull())
                throw new IllegalArgumentException("stack overflow");

            q1.enqueue(element);
            return true;
        }

        public int pop() {

            if (isEmpty())
                throw new IllegalArgumentException("stack underflow");

            while (q1.size() != 1)
                q2.enqueue(q1.dequeue());

            int element = q1.dequeue();

            Queue temp = q1;
            q1 = q2;
            q2 = temp;

            return element;
        }

        public int top() {

            if (isEmpty())
                throw new IllegalArgumentException("stack underflow");

            while (q1.size() != 1)
                q2.enqueue(q1.dequeue());

            int element = q1.dequeue();
            q2.enqueue(element);

            Queue temp = q1;
            q1 = q2;
            q2 = temp;

            return element;
        }

        public int size() {
            return q1.size();
        }

    }

    public static void reverseQueue(Queue queue) {

        if (queue.isEmpty())
            return;

        int element = queue.dequeue();
        reverseQueue(queue);

        queue.enqueue(element);

    }

    private static void reverseKElements(Queue queue, int k) {

        if (k > 0) {

            int element = queue.dequeue();
            reverseKElements(queue, k - 1);

            queue.enqueue(element);
        }

    }

    public static void reverseKElementsOfQueue(Queue queue, int k) {

        if (k <= 0 || k > queue.size())
            return;

        reverseKElements(queue, k);

        int i = queue.size() - k;

        while (i > 0) {
            queue.enqueue(queue.dequeue());
            --i;
        }

    }

    public static void main(String[] args) {

//        Stack stack = new Stack();
//
//        stack.push(2);
//        stack.push(3);
//
//        System.out.println(stack.top());
//
//        System.out.println(stack.pop());

        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

//        reverseQueue(queue);

//        queue.printQueue();

        reverseKElementsOfQueue(queue, 4);

        queue.printQueue();

    }
}
