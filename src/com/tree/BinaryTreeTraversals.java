package com.tree;

import sun.tools.jstat.Jstat;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeTraversals {

    public static void insertion(TreeNode root, int element) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode temp = queue.poll();

            if (temp.leftChild == null) {
                temp.leftChild = new TreeNode(element);
                break;
            } else {
                queue.add(temp.leftChild);
            }

            if (temp.rightChild == null) {
                temp.rightChild = new TreeNode(element);
                break;
            } else {
                queue.add(temp.rightChild);
            }
        }

    }

    public static void inOrderWithoutRecursion(TreeNode root) {
        TreeNode temp = root;

        if (temp == null)
            return;

        Stack<TreeNode> stack = new Stack<>();

        while (temp != null || !stack.isEmpty()) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.leftChild;
            } else {
                TreeNode node = stack.pop();
                System.out.print(node.data + ", ");
                temp = node.rightChild;
            }

        }
    }

    static int preIndex = 0;

    public static void printPostOrderGivenInOrderAndPreOrder(int[] in, int[] pre, int start, int end) {

        if (start <= end) {
            int indexInInorder = search(in, pre[preIndex]);
            ++preIndex;

            printPostOrderGivenInOrderAndPreOrder(in, pre, start, indexInInorder - 1);

            printPostOrderGivenInOrderAndPreOrder(in, pre, indexInInorder + 1, end);

            System.out.print(in[indexInInorder] + ", ");
        }
    }

    private static int search(int[] in, int element) {

        for (int i = 0; i < in.length; i++) {
            if (in[i] == element)
                return i;
        }
        return -1;
    }

    public static void levelOrderTraversalWithoutQueue(TreeNode root) {

        int level = height(root);

        for (int i = 1; i <= level; i++) {
            printGivenLevel(root, i);
        }
    }

    private static void printGivenLevel(TreeNode root, int level) {

        if (root != null) {
            if (level == 1) {
                System.out.print(root.data + ", ");
            } else {
                printGivenLevel(root.leftChild, level - 1);
                printGivenLevel(root.rightChild, level - 1);
            }
        }
    }

    public static void printLevelOrderLineByLine(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (true) {

            int nodeCount = queue.size();
            if (nodeCount == 0)
                break;

            while (nodeCount > 0) {

                TreeNode node = queue.poll();
                System.out.print(node.data + ", ");

                if (node.leftChild != null)
                    queue.add(node.leftChild);

                if (node.rightChild != null)
                    queue.add(node.rightChild);

                nodeCount--;
            }
            System.out.println();
        }

    }

    public static int height(TreeNode root) {

        if (root != null) {
            int x = height(root.leftChild);
            int y = height(root.rightChild);

            if (x >= y)
                return x + 1;
            else
                return y + 1;
        }
        return 0;
    }

    public static void preOrderWithOutRecursion(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.data + " ");

            if (node.rightChild != null)
                stack.push(node.rightChild);

            if (node.leftChild != null)
                stack.push(node.leftChild);
        }

    }

    public static void postOrderWithoutRecursion(TreeNode root) {

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {

            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.leftChild != null)
                stack1.push(node.leftChild);

            if (node.rightChild != null)
                stack1.push(node.rightChild);

        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }

    }

    //Morris Traversal
    public static void inOrderWithoutStackAndRecursion(TreeNode root) {
        TreeNode current = root;

        while (current != null) {

            if (current.leftChild == null) {
                System.out.print(current.data + " ");
                current = current.rightChild;
            } else {

                TreeNode predecessor = current.leftChild;
                while (predecessor.rightChild != null && predecessor.rightChild != current) {
                    predecessor = predecessor.rightChild;
                }

                if (predecessor.rightChild == null) {
                    predecessor.rightChild = current;
                    current = current.leftChild;
                } else {
                    predecessor.rightChild = null;
                    System.out.print(current.data + " ");
                    current = current.rightChild;
                }
            }
        }
    }

    //Morris Traversal
    public static void preOderTraversalWithoutRecursionAndStack(TreeNode root) {

        TreeNode current = root;

        while (current != null) {

            if (current.leftChild == null) {
                System.out.print(current.data + " ");
                current = current.rightChild;

            } else {

                TreeNode predecessor = current.leftChild;
                while (predecessor.rightChild != null && predecessor.rightChild != current) {
                    predecessor = predecessor.rightChild;
                }

                if (predecessor.rightChild == null) {
                    System.out.print(current.data + " ");
                    predecessor.rightChild = current;
                    current = current.leftChild;
                } else {
                    predecessor.rightChild = null;
                    current = current.rightChild;
                }
            }
        }
    }

    public static void postOrderUsingOneStack(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        while (true) {

            if (root != null) {
                stack.push(root);
                stack.push(root);
                root = root.leftChild;
            } else {
                if (!stack.isEmpty()) {
                    TreeNode node = stack.pop();

                    if (!stack.isEmpty() && stack.peek() == node) {
                        root = node.rightChild;
                    } else {
                        System.out.print(node.data + " ");
                        root = null;
                    }
                }
            }
        }
    }


    public static void preOrder(TreeNode root) {

        if (root != null) {
            System.out.print(root.data + ", ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }

    }

    static int count = 0;

    public static void inOrder(TreeNode root, int n) {

        if (root != null) {
            inOrder(root.leftChild, n);
            ++count;
            if (count == n) {
                System.out.println(root.data);
            }
            inOrder(root.rightChild, n);
        }
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.leftChild = new TreeNode(11);
        root.leftChild.leftChild = new TreeNode(7);
        root.rightChild = new TreeNode(9);
        root.rightChild.leftChild = new TreeNode(15);
        root.rightChild.rightChild = new TreeNode(8);

//        insertion(root, 12);
//        preOrder(root);

//        inOrderWithoutRecursion(root);

//        int[] in = {4, 2, 5, 1, 3, 6};
//        int[] pre = {1, 2, 4, 5, 3, 6};
//
//        printPostOrderGivenInOrderAndPreOrder(in, pre, 0, in.length - 1);

//        inOrder(root, 4);

//        System.out.println(height(root));

//        levelOrderTraversalWithoutQueue(root);

//        printLevelOrderLineByLine(root);

//        preOrderWithOutRecursion(root);

//        postOrderWithoutRecursion(root);

//        inOrderWithoutStackAndRecursion(root);

//        preOderTraversalWithoutRecursionAndStack(root);

        postOrderUsingOneStack(root);
    }

}
