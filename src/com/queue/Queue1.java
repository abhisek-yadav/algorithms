package com.queue;

public class Queue1 {

    int size;
    Node front;
    Node rear;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }

    public boolean isEmpty() {
        return rear == null;
    }

    public boolean enqueue(int element) {

        Node node = new Node(element);
        if (rear == null) {
            rear = node;
            front = rear;
        } else {
            rear.next = node;
            rear = node;
        }
        ++size;
        return true;
    }

    public int dequeue() {
        if (front == null)
            throw new IllegalArgumentException("queue is empty");

        --size;

        int data = front.data;
        front = front.next;

        if (front == null)
            rear = null;

        return data;
    }

    public int size() {
        return size;
    }

    public void printQueue() {
        Node f = front;
        while (f != null) {
            System.out.print(f.data + " ");
            f = f.next;
        }
    }


    public static void main(String[] args) {

        Queue1 queue = new Queue1();

        System.out.println(queue.enqueue(2));
        System.out.println(queue.enqueue(5));
        System.out.println(queue.enqueue(6));

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println(queue.enqueue(8));


        System.out.println(queue.dequeue());


    }
}
