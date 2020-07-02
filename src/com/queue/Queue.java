package com.queue;


public class Queue {

    private static final int MAX_SIZE = 10;
    private int[] arr;
    private int front;
    private int rear;
    private int size;

    Queue() {
        arr = new int[MAX_SIZE];
        front = -1;
        rear = -1;
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;

        return false;
    }

    public boolean isFull() {
        if (size == MAX_SIZE)
            return true;
        return false;
    }

    public int size() {
        return size;
    }

    public boolean enqueue(int element) {
        if (isFull())
            throw new IllegalArgumentException("queue is full");

        arr[++rear % MAX_SIZE] = element;
        ++size;

        return true;
    }

    public int dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("queue is empty");

        --size;

        return arr[++front % MAX_SIZE];
    }

    public int peek() {

        if (isEmpty())
            throw new IllegalArgumentException("queue is empty");

        return arr[(front + 1) % MAX_SIZE];
    }


    public void printQueue() {
        int f = front;

        while (f < rear)
            System.out.print(arr[++f % MAX_SIZE] + " ");
    }

    public static void main(String[] args) {

        Queue queue = new Queue();

        System.out.println(queue.isEmpty());

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println(queue.dequeue());

        queue.enqueue(50);

        queue.printQueue();


        System.out.println();

        System.out.println(queue.size());

        System.out.println(queue.peek());

        System.out.println(queue.size());

        queue.printQueue();

    }


}
