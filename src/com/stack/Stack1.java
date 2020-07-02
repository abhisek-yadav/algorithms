package com.stack;

public class Stack1 {

    private Node top;
    private int size;

    class Node {

        private int data;
        private Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }

    public boolean isEmpty() {

        return (top == null);
    }

    public int pop() {

        if (top == null)
            throw new IllegalArgumentException("stack underflow");

        int data = top.data;
        top = top.next;
        --size;

        return data;
    }

    public boolean push(int element) {

        Node node = new Node(element);

        if (top == null)
            top = node;
        else {
            Node temp = top;
            top = node;
            top.next = temp;
        }

        ++size;
        return true;
    }

    public int peek() {

        if (top == null)
            throw new IllegalArgumentException("stack is empty");

        return top.data;
    }

    public int size() {
        return size;
    }

    public int search(int element) {

        int count = 1;
        Node node = top;

        while (node != null) {
            if (node.data == element)
                return count;

            ++count;
            node = node.next;
        }

        return -1;
    }

    public void printStack() {

        Node node = top;

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {

        Stack1 stack = new Stack1();

        System.out.println(stack.isEmpty());

        stack.push(2);
        stack.push(3);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(9);

        System.out.println("size: " + stack.size);

        System.out.println(stack.pop());

        System.out.println(stack.peek());

        System.out.println(stack.isEmpty());

        System.out.println("size: " + stack.size);

        stack.printStack();

        System.out.println();

        System.out.println(stack.search(2));

    }

}
