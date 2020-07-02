package com.stack;

public class StackOperation {


    private static void insertElementAtBottomOfStack(Stack stack, int element) {

        if (stack.isEmpty())
            stack.push(element);

        else {
            int e = stack.pop();
            insertElementAtBottomOfStack(stack, element);
            stack.push(e);
        }

    }

    public static void reverse(Stack stack) {

        if (stack.isEmpty())
            return;

        int element = stack.pop();

        reverse(stack);

        insertElementAtBottomOfStack(stack, element);

    }

    public static void insertInSortingOrder(Stack stack, int element) {

        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
        } else {
            int e = stack.pop();
            insertInSortingOrder(stack, element);
            stack.push(e);
        }
    }

    public static void sortStack(Stack stack) {

        if (stack.isEmpty())
            return;

        int element = stack.pop();

        sortStack(stack);

        insertInSortingOrder(stack, element);

    }

    public static int[] nextGreaterElement(Stack stack, int[] arr, int[] res) {

        stack.push(arr[0]);
        int j = 0;

        for (int i = 1; i < arr.length; i++) {

            int next = arr[i];

            while (!stack.isEmpty()) {
                int element = stack.pop();
                if (element < next) {
                    res[j++] = i;
                } else {
                    stack.push(element);
                    break;
                }
            }
            stack.push(next);
        }

        while (!stack.isEmpty()) {
            stack.pop();
            res[j++] = -1;
        }

        return res;

    }

    public static void towerOfHanoi(int n, char from, char aux, char to) {

        if (n == 1) {
            System.out.println("Disk " + n + " from " + from + " to " + to);
            return;
        }

        towerOfHanoi(n - 1, from, to, aux);
        System.out.println("Disk " + n + " from " + from + " to " + to);
        towerOfHanoi(n - 1, aux, from, to);

    }

    public static void printStack(Stack stack) {
        stack.printStack();
    }

    public static void main(String[] args) {

        Stack stack = new Stack();
        stack.push(4);
        stack.push(5);
        stack.push(2);
        stack.push(3);
        stack.push(1);

//        reverse(stack);

//        sortStack(stack);

//        printStack(stack);

        int[] res = nextGreaterElement(new Stack(), new int[]{3, 4, 2, 7, 5, 8, 10, 6}, new int[8]);

        for (int e : res)
            System.out.print(e + " ");
    }

}
