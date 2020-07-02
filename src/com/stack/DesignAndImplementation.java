package com.stack;

public class DesignAndImplementation {


    static class Queue {
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();


        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        public boolean add(int element) {
            return stack1.push(element);
        }

        public int poll() {
            if (stack1.isEmpty() && stack2.isEmpty())
                throw new IllegalArgumentException("queue is  empty");

            if (stack2.isEmpty()) {
                while (!stack1.isEmpty())
                    stack2.push(stack1.pop());
            }

            return stack2.pop();
        }

    }

    static class Pair<T, U> {

        private T first;
        private U second;


        Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public U getSecond() {
            return second;
        }

        public void setSecond(U second) {
            this.second = second;
        }


        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }

    static class CustomizedStack {

        private static int MAX_SIZE = 10;

        private int top;
        private int min;
        private Pair<Integer, Integer>[] arr;

        CustomizedStack() {
            top = -1;
            min = Integer.MAX_VALUE;
            arr = new Pair[MAX_SIZE];
        }

        public boolean isEmpty() {
            return (top < 0);
        }

        public boolean push(int element) {

            if (top >= MAX_SIZE - 1)
                throw new IllegalArgumentException("stack overflow");

            if (element < min)
                min = element;
            arr[++top] = new Pair<>(element, min);

            return true;
        }

        public int pop() {

            if (top < 0)
                throw new IllegalArgumentException("stack is empty");

            return arr[top--].getFirst();
        }

        public int min() {

            if (top < 0)
                throw new IllegalArgumentException("stack is empty");

            return arr[top].getSecond();
        }

        public int peek() {

            if (top < 0)
                throw new IllegalArgumentException("stack is empty");

            return arr[top].getFirst();
        }

    }

    public static void main(String[] args) {

//        Queue queue = new Queue();
//
//        System.out.println(queue.isEmpty());
//
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        queue.add(5);
//        queue.add(6);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//
//        System.out.println(queue.isEmpty());


        CustomizedStack stack = new CustomizedStack();

        stack.push(3);
        stack.push(8);
        stack.push(2);
        stack.push(1);
        stack.push(5);

        System.out.println("min: " + stack.min());

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println("min: " + stack.min());


        System.out.println(stack.pop());
        System.out.println("min: " + stack.min());
    }
}
