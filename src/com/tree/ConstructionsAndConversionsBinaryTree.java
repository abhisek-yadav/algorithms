package com.tree;

public class ConstructionsAndConversionsBinaryTree {


    static int preOrderIndex = 0;

    public static TreeNode constructTreeFromGivenInOrderPreOrderTraversal(int[] inOrder, int[] preOder, int start, int end) {

        if (start > end)
            return null;

        TreeNode node = new TreeNode(preOder[preOrderIndex++]);
        if (start == end) {
            return node;
        }
        int index = search(inOrder, node.data, start, end);

        node.leftChild = constructTreeFromGivenInOrderPreOrderTraversal(inOrder, preOder, start, index - 1);
        node.rightChild = constructTreeFromGivenInOrderPreOrderTraversal(inOrder, preOder, index + 1, end);

        return node;

    }

    public static int search(int[] inOder, int element, int start, int end) {

        int i;
        for (i = start; i < end; i++) {
            if (inOder[i] == element)
                return i;
        }

        return i;
    }

    public static TreeNode constructCompleteBinaryTreeFromGivenArray(int[] arr, int i, TreeNode t) {

        if (i < arr.length - 1) {
            TreeNode node = new TreeNode(arr[i]);

            node.leftChild = constructCompleteBinaryTreeFromGivenArray(arr, (2 * i + 1), node.leftChild);
            node.rightChild = constructCompleteBinaryTreeFromGivenArray(arr, (2 * i + 2), node.rightChild);

            return node;
        }

        return t;

    }

    public static TreeNode constructTreeFromGivenInOrderAndLevelOrder(int[] in, int[] level, int start, int end) {

        if (start > end)
            return null;

        if (start == end)
            return new TreeNode(in[start]);

        boolean ifFound = false;
        int index = 0;
        TreeNode startNode = null;

        if (start < end) {

            for (int i = 0; i < level.length - 1; i++) {
                int data = level[i];
                for (int j = start; j < end; j++) {
                    if (data == in[j]) {
                        index = j;
                        startNode = new TreeNode(data);
                        ifFound = true;
                        break;
                    }
                }
                if (ifFound)
                    break;
            }

            startNode.leftChild = constructTreeFromGivenInOrderAndLevelOrder(in, level, start, index - 1);
            startNode.rightChild = constructTreeFromGivenInOrderAndLevelOrder(in, level, index + 1, end);

        }

        return startNode;
    }

    static int postIndex = 7;

    public static TreeNode constructTreeFromGivenInOrderPostOrder(int[] in, int[] post, int start, int end) {


        if (start > end)
            return null;

        TreeNode node = new TreeNode(post[postIndex--]);
        if (start == end)
            return node;

        int index = search(in, node.data, start, end);

        node.rightChild = constructTreeFromGivenInOrderPostOrder(in, post, index + 1, end);
        node.leftChild = constructTreeFromGivenInOrderPostOrder(in, post, start, index - 1);

        return node;
    }


    public static TreeNode convertBinaryTreeToDoublyLinkedListI(TreeNode root) {

        TreeNode node = binaryTreeToDoublyLinkedList(root);

        while (node.leftChild != null)
            node = node.leftChild;

        return node;
    }

    private static TreeNode binaryTreeToDoublyLinkedList(TreeNode root) {
        if (root == null)
            return null;

        if (root.leftChild != null) {
            TreeNode left = binaryTreeToDoublyLinkedList(root.leftChild);

            while (left.rightChild != null)
                left = left.rightChild;

            left.rightChild = root;
            root.leftChild = left;
        }

        if (root.rightChild != null) {
            TreeNode right = binaryTreeToDoublyLinkedList(root.rightChild);

            while (right.leftChild != null)
                right = right.leftChild;

            right.leftChild = root;
            root.rightChild = right;

        }

        return root;
    }

    static TreeNode head = null;
    static TreeNode prev = null;

    public static void convertBinaryTreeToDoublyLinkedListII(TreeNode root) {

        if (root == null)
            return;

        convertBinaryTreeToDoublyLinkedListII(root.leftChild);

        if (prev == null) {
            head = root;
        } else {
            prev.rightChild = root;
            root.leftChild = prev;
        }
        prev = root;

        convertBinaryTreeToDoublyLinkedListII(root.rightChild);
    }

    public static int convertBinaryTreeToSumTree(TreeNode root) {

        if (root == null)
            return 0;

        int prevVal = root.data;

        root.data = convertBinaryTreeToSumTree(root.leftChild) + convertBinaryTreeToSumTree(root.rightChild);

        return root.data + prevVal;
    }

    public static int convertBinaryTreeToSumOfLeftTree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int prevVal = root.data;

        root.data = prevVal + convertBinaryTreeToSumOfLeftTree(root.leftChild);

        return root.data + convertBinaryTreeToSumOfLeftTree(root.rightChild);

    }

    public static TreeNode convertBinaryTreeToMirrorTree(TreeNode root) {

        if (root == null)
            return null;

        TreeNode left = convertBinaryTreeToMirrorTree(root.leftChild);
        TreeNode right = convertBinaryTreeToMirrorTree(root.rightChild);

        root.leftChild = right;
        root.rightChild = left;

        return root;

    }

    public static TreeNode convertBinaryTreeToFlipTree(TreeNode root) {

        if (root == null)
            return null;
        if (root.leftChild == null && root.rightChild == null)
            return root;

        TreeNode flippedNode = convertBinaryTreeToFlipTree(root.leftChild);

        root.leftChild.leftChild = root.rightChild;
        root.leftChild.rightChild = root;
        root.leftChild = null;
        root.rightChild = null;

        return flippedNode;
    }


    public static void preOrder(TreeNode root) {

        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);

        }
    }

    public static void postOrder(TreeNode root) {

        if (root == null)
            return;

        postOrder(root.leftChild);
        postOrder(root.rightChild);
        System.out.print(root.data + " ");

    }

    public static void printDoublyLinkedList(TreeNode root) {

        if (root == null)
            return;

        while (root != null) {
            System.out.print(root.data + " ");
            root = root.rightChild;
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.leftChild = new TreeNode(11);
        root.leftChild.leftChild = new TreeNode(7);
        root.rightChild = new TreeNode(9);
        root.rightChild.leftChild = new TreeNode(15);
        root.rightChild.rightChild = new TreeNode(8);

//        int[] inOder = {7, 11, 10, 15, 9, 8};
//        int[] preOrder = {10, 11, 7, 9, 15, 8};

//        postOrder(constructTreeFromGivenInOrderPreOrderTraversal(inOder, preOrder, 0, inOder.length - 1));

//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        postOrder(constructCompleteBinaryTreeFromGivenArray(arr, 0, null));


//        int[] in = {4, 8, 10, 12, 14, 20, 22};
//        int[] level = {20, 8, 22, 4, 12, 10, 14};

//        postOrder(constructTreeFromGivenInOrderAndLevelOrder(in, level, 0, level.length - 1));

//        int[] in = {4, 8, 2, 5, 1, 6, 3, 7};
//        int[] post = {8, 4, 5, 2, 6, 7, 3, 1};

//        preOrder(constructTreeFromGivenInOrderPostOrder(in, post, 0, in.length - 1));


//        printDoublyLinkedList(convertBinaryTreeToDoublyLinkedListI(root));

//        convertBinaryTreeToDoublyLinkedListII(root);
//        printDoublyLinkedList(head);

//        convertBinaryTreeToSumTree(root);
//        preOrder(root);

//        convertBinaryTreeToSumOfLeftTree(root);
//        preOrder(root);

//        convertBinaryTreeToMirrorTree(root);
//        preOrder(root);

        preOrder(convertBinaryTreeToFlipTree(root));
    }
}
