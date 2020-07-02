package com.stack;

public class Stack {

    private static final int MAX_SIZE = 10;

    private int[] a = new int[MAX_SIZE];
    private int top = -1;

    public boolean isEmpty() {
        return (top < 0);
    }

    public boolean push(int element) {

        if (top >= (MAX_SIZE - 1))
            throw new IllegalArgumentException("stack overflow");

        a[++top] = element;

        return true;
    }

    public int pop() {

        if (top < 0)
            throw new IllegalArgumentException("stack underflow");

        return a[top--];

    }

    public int peek() {

        if (top < 0)
            throw new IllegalArgumentException("stack is empty");

        return a[top];
    }

    public int search(int element) {

        for (int i = top, count = 1; i >= 0; i--, count++) {
            if (element == a[i])
                return count;
        }

        return -1;
    }

    public void printStack() {

        for (int i = top; i >= 0; i--)
            System.out.print(a[i] + " ");

    }

    public static void main(String[] args) {

        Stack stack = new Stack();

        System.out.println(stack.isEmpty());

        stack.push(2);
        stack.push(3);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(9);

        System.out.println(stack.pop());

        System.out.println(stack.peek());

        System.out.println(stack.isEmpty());


        stack.printStack();

        System.out.println();

        System.out.println(stack.search(5));
    }

}
