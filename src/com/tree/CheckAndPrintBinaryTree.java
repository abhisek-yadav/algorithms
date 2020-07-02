package com.tree;

import java.util.*;

public class CheckAndPrintBinaryTree {

    public static boolean checkForChildrenSumPropertyForBinaryTree(TreeNode root) {

        if (root == null || (root.leftChild == null && root.rightChild == null))
            return true;
        else {
            if (root.data == (root.leftChild != null ? root.leftChild.data : 0) + (root.rightChild != null ? root.rightChild.data : 0) && checkForChildrenSumPropertyForBinaryTree(root.leftChild) && checkForChildrenSumPropertyForBinaryTree(root.rightChild))
                return true;

            return false;
        }
    }

    public static int levelOfGivenNodeInBinaryTree(TreeNode root, int data, int level) {

        if (root == null)
            return 0;

        if (root.data == data)
            return level;

        int l = levelOfGivenNodeInBinaryTree(root.leftChild, data, level + 1);
        if (l != 0)
            return l;
        return levelOfGivenNodeInBinaryTree(root.rightChild, data, level + 1);

    }

    static int l = 0;

    public static boolean checkAllLeavesAtSameLevel(TreeNode root, int level) {

        if (root == null)
            return true;

        if (root.leftChild == null && root.rightChild == null) {
            if (l == 0) {
                l = level;
                return true;
            }
            return l == level;
        }
        return checkAllLeavesAtSameLevel(root.leftChild, level + 1) && checkAllLeavesAtSameLevel(root.rightChild, level + 1);
    }


    public static boolean isMinHeapWithGivenLevelOrder(int[] level) {

        int n = level.length;

        int start = n % 2 == 0 ? n / 2 - 1 : (n - 1) / 2 - 1;

        for (int i = start; i >= 0; i--) {

            if (level[i] > level[2 * i + 1])
                return false;

            if (2 * i + 2 < n) {
                if (level[i] > level[2 * i + 2])
                    return false;
            }
        }

        return true;

    }

    private static int depth(TreeNode root) {

        int d = 1;
        while (root.leftChild != null) {
            d++;
            root = root.leftChild;
        }
        return d;
    }

    public static boolean isPerfectBinaryTree(TreeNode root, int level, int depth) {

        if (root == null)
            return true;

        if (root.leftChild == null && root.rightChild == null) {
            if (depth == level)
                return true;
            return false;
        }

        if (root.leftChild == null || root.rightChild == null)
            return false;

        return isPerfectBinaryTree(root.leftChild, level + 1, depth) && isPerfectBinaryTree(root.rightChild, level + 1, depth);
    }

    public static boolean isFullBinaryTree(TreeNode root) {

        if (root == null)
            return true;
        if (root.leftChild == null && root.rightChild == null)
            return true;

        if (root.leftChild == null || root.rightChild == null)
            return false;

        return isFullBinaryTree(root.leftChild) && isFullBinaryTree(root.rightChild);
    }

    public static boolean isCompleteBinaryTree(TreeNode root) {

        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            if (node.leftChild == null) {
                if (node.rightChild != null)
                    return false;
            }

            if (node.leftChild != null)
                queue.add(node.leftChild);
            if (node.rightChild != null)
                queue.add(node.rightChild);
        }

        return true;
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;

        if (t1 == null || t2 == null)
            return false;

        if (t1.data != t2.data)
            return false;


        return isMirror(t1.leftChild, t2.rightChild) && isMirror(t1.rightChild, t2.leftChild);

    }

    public static boolean isMirrorIterative(TreeNode t1, TreeNode t2) {

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        if (t1 == null && t2 == null)
            return true;

        if (t1 == null || t2 == null)
            return false;

        while ((!stack1.isEmpty() || t1 != null) && (!stack2.isEmpty() || t2 != null)) {

            if (t1 != null && t2 != null) {

                if (t1.data != t2.data)
                    return false;

                stack1.push(t1);
                t1 = t1.leftChild;

                stack2.push(t2);
                t2 = t2.rightChild;

            } else if (t1 == null && t2 == null) {
                TreeNode t11 = stack1.pop();
                TreeNode t22 = stack2.pop();

                if (t11.data != t22.data)
                    return false;

                t1 = t11.rightChild;
                t2 = t22.leftChild;

            } else
                return false;

        }
        return true;
    }

    public static void printCousinsOfGiveNode(TreeNode root, TreeNode node, int level) {

        if (root == null || level < 2)
            return;

        if (level == 2) {
            if (root.leftChild == node || root.rightChild == node)
                return;
            if (root.leftChild != null)
                System.out.print(root.leftChild.data + " ");
            if (root.rightChild != null)
                System.out.println(root.rightChild.data + " ");

        } else if (level > 2) {
            printCousinsOfGiveNode(root.leftChild, node, level - 1);
            printCousinsOfGiveNode(root.rightChild, node, level - 1);
        }

    }

    public static boolean printPathFromRootToGivenNode(TreeNode root, List arr, int data) {

        if (root == null)
            return false;

        arr.add(root.data);

        if (root.data == data)
            return true;

        if (printPathFromRootToGivenNode(root.leftChild, arr, data) || printPathFromRootToGivenNode(root.rightChild, arr, data))
            return true;

        arr.remove(arr.size() - 1);
        return false;

    }

    private static int getLevel(TreeNode root, int node, int level) {
        if (root == null)
            return 0;
        if (root.data == node)
            return level;

        int l = getLevel(root.leftChild, node, level + 1);
        if (l != 0)
            return l;
        return getLevel(root.rightChild, node, level + 1);

    }

    public static void main(String[] args) {

//        TreeNode root = new TreeNode(10);
//        root.leftChild = new TreeNode(8);
//        root.rightChild = new TreeNode(2);
//        root.leftChild.leftChild = new TreeNode(3);
//        root.leftChild.rightChild = new TreeNode(5);
//        root.rightChild.leftChild = new TreeNode(2);


//        System.out.println(checkForChildrenSumPropertyForBinaryTree(root));

//        System.out.println(levelOfGivenNodeInBinaryTree(root, 5, 1));

//        System.out.println(checkAllLeavesAtSameLevel(root, 0));


//        int[] level = {10, 15, 14, 25, 30, 35};

//        System.out.println(isMinHeapWithGivenLevelOrder(level));

//        TreeNode root1 = new TreeNode(10);
//        root1.leftChild = new TreeNode(20);
//        root1.rightChild = new TreeNode(30);
//
//        root1.leftChild.leftChild = new TreeNode(40);
//        root1.leftChild.rightChild = new TreeNode(50);
//        root1.rightChild.leftChild = new TreeNode(60);
//        root1.rightChild.rightChild = new TreeNode(70);
//
//        int depth = depth(root1);
//
//        System.out.println(isPerfectBinaryTree(root1, 1, depth));


//        TreeNode root = new TreeNode(10);
//        root.leftChild = new TreeNode(20);
//        root.rightChild = new TreeNode(30);
//        root.leftChild.rightChild = new TreeNode(40);
//        root.leftChild.leftChild = new TreeNode(50);
//        root.rightChild.leftChild = new TreeNode(60);
//        root.leftChild.leftChild.leftChild = new TreeNode(80);
//        root.rightChild.rightChild = new TreeNode(70);
//        root.leftChild.leftChild.rightChild = new TreeNode(90);
//        root.leftChild.rightChild.leftChild = new TreeNode(80);
//        root.leftChild.rightChild.rightChild = new TreeNode(90);
//        root.rightChild.leftChild.leftChild = new TreeNode(80);
//        root.rightChild.leftChild.rightChild = new TreeNode(90);
//        root.rightChild.rightChild.leftChild = new TreeNode(80);
//        root.rightChild.rightChild.rightChild = new TreeNode(90);
//
//        System.out.println(isFullBinaryTree(root));

//        TreeNode root = new TreeNode(1);
//        root.leftChild = new TreeNode(2);
//        root.rightChild = new TreeNode(3);
//        root.leftChild.leftChild = new TreeNode(4);
//        root.leftChild.rightChild = new TreeNode(5);
//        root.rightChild.leftChild = new TreeNode(6);
//
//        System.out.println(isCompleteBinaryTree(root));

//        TreeNode a = new TreeNode(1);
//        TreeNode b = new TreeNode(1);
//        a.leftChild = new TreeNode(2);
//        a.rightChild = new TreeNode(3);
//        a.leftChild.leftChild = new TreeNode(4);
//        a.leftChild.rightChild = new TreeNode(5);
//
//        b.leftChild = new TreeNode(3);
//        b.rightChild = new TreeNode(2);
//        b.rightChild.leftChild = new TreeNode(5);
//        b.rightChild.rightChild = new TreeNode(4);
//
////        System.out.println(isMirror(a, b));
//
//
//        System.out.println(isMirrorIterative(a, b));

        TreeNode root = new TreeNode(1);
        root.leftChild = new TreeNode(2);
        root.rightChild = new TreeNode(3);
        root.leftChild.leftChild = new TreeNode(4);
        root.leftChild.rightChild = new TreeNode(5);
        root.rightChild.leftChild = new TreeNode(6);
        root.rightChild.rightChild = new TreeNode(7);

//        printCousinsOfGiveNode(root, root.leftChild.rightChild, getLevel(root, 5, 1));

//        List<Integer> arr = new ArrayList<>();
//        printPathFromRootToGivenNode(root, arr, 3);
//
//        for (int e : arr)
//            System.out.print(e + " ");

    }
}
