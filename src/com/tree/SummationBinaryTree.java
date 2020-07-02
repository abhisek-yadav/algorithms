package com.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SummationBinaryTree {

    public static int sumOfAllNodeInBinaryTree(TreeNode root) {

        if (root == null)
            return 0;

        return root.data + sumOfAllNodeInBinaryTree(root.leftChild) + sumOfAllNodeInBinaryTree(root.rightChild);
    }

    public static int sumOfAllParentNodesForGivenNode(TreeNode root, int data) {

        if (root == null)
            return 0;

        if ((root.leftChild != null && root.leftChild.data == data) || (root.rightChild != null && root.rightChild.data == data))
            return root.data + sumOfAllParentNodesForGivenNode(root.leftChild, data) + sumOfAllParentNodesForGivenNode(root.rightChild, data);

        return sumOfAllParentNodesForGivenNode(root.leftChild, data) + sumOfAllParentNodesForGivenNode(root.rightChild, data);

    }

    static int sumOfLeftLeaves = 0;

    public static void sumOfAllLeftLeaves(TreeNode root) {

        if (root == null)
            return;

        if (root.leftChild != null) {
            if (root.leftChild.leftChild == null && root.leftChild.rightChild == null) {
                sumOfLeftLeaves += root.leftChild.data;
            }
        }

        sumOfAllLeftLeaves(root.leftChild);
        sumOfAllLeftLeaves(root.rightChild);

    }

    private static boolean pairWithSumEqualToRootData(TreeNode node, Set<Integer> traversedNodeData, int rootData) {

        if (node == null)
            return false;

        int diff = rootData - node.data;
        if (traversedNodeData.contains(diff))
            return true;

        traversedNodeData.add(node.data);

        return pairWithSumEqualToRootData(node.leftChild, traversedNodeData, rootData) || pairWithSumEqualToRootData(node.rightChild, traversedNodeData, rootData);

    }

    public static boolean pairWithSumEqualToRootData(TreeNode root) {

        Set<Integer> traversedNodeData = new HashSet<>();

        return pairWithSumEqualToRootData(root.leftChild, traversedNodeData, root.data) || pairWithSumEqualToRootData(root.rightChild, traversedNodeData, root.data);
    }

    static int maxSum;
    static int maxLevel;

    public static void sumOfLongRootToLeafPath(TreeNode root, int level, int sum) {

        if (root == null) {
            if (level > maxLevel) {
                maxSum = sum;
                maxLevel = level;
            } else if (sum > maxSum && level == maxLevel) {
                maxSum = sum;
            }

            return;
        }

        sumOfLongRootToLeafPath(root.leftChild, level + 1, root.data + sum);
        sumOfLongRootToLeafPath(root.rightChild, level + 1, root.data + sum);

    }

    static int maxSumRootToLeaf;
    static TreeNode targetLeafRef;

    private static void maxSumRootToLeafPath(TreeNode root, int sum) {

        if (root == null)
            return;

        if (sum > maxSumRootToLeaf) {
            maxSumRootToLeaf = sum;
            targetLeafRef = root;
        }

        maxSumRootToLeafPath(root.leftChild, root.data + sum);
        maxSumRootToLeafPath(root.rightChild, root.data + sum);

    }

    public static boolean printRootToTargetLeafPath(TreeNode root, TreeNode targetLeaf) {

        if (root == null)
            return false;

        if (targetLeaf.data == root.data || printRootToTargetLeafPath(root.leftChild, targetLeaf) || printRootToTargetLeafPath(root.rightChild, targetLeaf)) {
            System.out.print(root.data + " ");
            return true;
        }

        return false;

    }

    static int sumWithoutAdjacentLevel;

    public static void sumWithoutAdjacentLevels(TreeNode root, boolean flag) {

        if (root == null)
            return;

        if (flag)
            sumWithoutAdjacentLevel += root.data;

        sumWithoutAdjacentLevels(root.leftChild, !flag);
        sumWithoutAdjacentLevels(root.rightChild, !flag);

    }

    static List<Integer> path = new ArrayList<>();

    public static void printPathsEqualToGivenSum(TreeNode root, int sum) {

        if (root == null)
            return;

        path.add(root.data);

        printPathsEqualToGivenSum(root.leftChild, sum);
        printPathsEqualToGivenSum(root.rightChild, sum);

        int s = 0;
        for (int j = path.size() - 1; j >= 0; j--) {

            s += path.get(j);

            if (s == sum)
                printPath(path, j);
        }

        path.remove(path.size() - 1);

    }

    private static void printPath(List<Integer> l, int index) {
        for (int i = l.size() - 1; i >= index; i--)
            System.out.print(l.get(i) + " ");
        System.out.println();
    }


    static int allNodesHeightSum;

    public static int sumOfHeightsOfAllNodesInBinaryTree(TreeNode root) {

        if (root == null)
            return 0;

        int leftHeight = sumOfHeightsOfAllNodesInBinaryTree(root.leftChild);
        int rightHeight = sumOfHeightsOfAllNodesInBinaryTree(root.rightChild);

        int height = Math.max(leftHeight, rightHeight) + 1;

        allNodesHeightSum += height;

        return height;

    }

    public static boolean isGivenSumSubTreeExists(TreeNode root, int currSum, int sum) {

        if (root == null)
            return false;

        currSum += root.data;

        if (currSum == sum)
            return true;

        return isGivenSumSubTreeExists(root.leftChild, currSum, sum) || isGivenSumSubTreeExists(root.rightChild, currSum, sum);

    }


    static int noOfSubTreesCount;

    public static int numberOfSubTreesForGivenSum(TreeNode root, int sum) {

        if (root == null)
            return 0;


        int left = leafNodeEqualToGivenSum(root.leftChild, sum);
        int right = leafNodeEqualToGivenSum(root.rightChild, sum);

        if (left + right + root.data == sum)
            noOfSubTreesCount++;

        return noOfSubTreesCount;

    }

    private static int leafNodeEqualToGivenSum(TreeNode root, int sum) {

        if (root == null)
            return 0;

        int left = leafNodeEqualToGivenSum(root.leftChild, sum);
        int right = leafNodeEqualToGivenSum(root.rightChild, sum);

        if (left + right + root.data == sum)
            noOfSubTreesCount++;

        return root.data;

    }


    public static int sumOfMaxDepthLevelNodes(TreeNode root, int level, int maxDepth) {

        if (root == null)
            return 0;

        if (root.leftChild == null && root.rightChild == null && level == maxDepth)
            return root.data;

        return sumOfMaxDepthLevelNodes(root.leftChild, level + 1, maxDepth) + sumOfMaxDepthLevelNodes(root.rightChild, level + 1, maxDepth);

    }

    public static boolean isPathExistsForGivenSum(TreeNode root, int currSum, int sum) {

        if (root == null)
            return false;

        currSum += root.data;

        if (currSum == sum)
            return true;

        return isPathExistsForGivenSum(root.leftChild, currSum, sum) || isPathExistsForGivenSum(root.rightChild, currSum, sum);

    }

    static List<Integer> formedNumbersFromRootLeafPath = new ArrayList<>();

    private static void formedAllNumbersFromRootToLeafPath(TreeNode root, List<Integer> number) {

        if (root == null)
            return;

        number.add(root.data);

        if (root.leftChild == null && root.rightChild == null) {

            int num = 0;
            for (int i = number.size() - 1, j = 0; i >= 0 && j < number.size(); i--, j++) {
                num += Math.pow(10, j) * number.get(i);
            }

            System.out.println(num);
            formedNumbersFromRootLeafPath.add(num);
        }

        formedAllNumbersFromRootToLeafPath(root.leftChild, number);
        formedAllNumbersFromRootToLeafPath(root.rightChild, number);

        number.remove(number.size() - 1);
    }

    public static void preOrder(TreeNode root) {

        if (root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }


    public static int getDepthOfBinaryTree(TreeNode root) {

        if (root == null)
            return 0;

        return 1 + Math.max(getDepthOfBinaryTree(root.leftChild), getDepthOfBinaryTree(root.rightChild));
    }

    public static void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.leftChild);
        System.out.print(root.data + " ");
        inOrder(root.rightChild);
    }

    public static void main(String[] args) {

//        TreeNode root = new TreeNode(1);
//        root.leftChild = new TreeNode(2);
//        root.rightChild = new TreeNode(3);
//        root.leftChild.leftChild = new TreeNode(4);
//        root.leftChild.rightChild = new TreeNode(5);
//        root.rightChild.leftChild = new TreeNode(6);
//        root.rightChild.rightChild = new TreeNode(7);
//        root.rightChild.leftChild.rightChild = new TreeNode(8);

//        System.out.println(sumOfAllNodeInBinaryTree(root));

//        TreeNode root = new TreeNode(4);
//        root.leftChild = new TreeNode(2);
//        root.rightChild = new TreeNode(5);
//        root.leftChild.leftChild = new TreeNode(7);
//        root.leftChild.rightChild = new TreeNode(2);
//        root.rightChild.leftChild = new TreeNode(2);
//        root.rightChild.rightChild = new TreeNode(3);


//        System.out.println(sumOfAllParentNodesForGivenNode(root, 2));


//        sumOfAllLeftLeaves(root);
//        System.out.println(sumOfLeftLeaves);


//        TreeNode root = new TreeNode(8);
//        root.leftChild = new TreeNode(5);
//        root.rightChild = new TreeNode(4);
//        root.leftChild.leftChild = new TreeNode(9);
//        root.leftChild.rightChild = new TreeNode(7);
//        root.leftChild.rightChild.leftChild = new TreeNode(1);
//        root.leftChild.rightChild.rightChild = new TreeNode(12);
//        root.leftChild.rightChild.rightChild.rightChild = new TreeNode(2);
//        root.rightChild.rightChild = new TreeNode(11);
//        root.rightChild.rightChild.leftChild = new TreeNode(3);

//        System.out.println(pairWithSumEqualToRootData(root));

//        sumOfLongRootToLeafPath(root, 0, 0);

//        System.out.println(maxSum + " " + maxLevel);


//        maxSumRootToLeafPath(root, 0);

//        printRootToTargetLeafPath(root, targetLeafRef);


//        int totalSum = sumOfAllNodeInBinaryTree(root);
//        sumWithoutAdjacentLevels(root, true);

//        System.out.println(Math.max(sumWithoutAdjacentLevel, totalSum - sumWithoutAdjacentLevel));

//        printPathsEqualToGivenSum(root, 12);

//        TreeNode root = new TreeNode(1);
//
//        root.leftChild = new TreeNode(2);
//        root.rightChild = new TreeNode(3);
//        root.leftChild.leftChild = new TreeNode(4);
//        root.leftChild.rightChild = new TreeNode(5);
//
//        sumOfHeightsOfAllNodesInBinaryTree(root);
//
//        System.out.println(allNodesHeightSum);

//        TreeNode root = new TreeNode(8);
//        root.leftChild = new TreeNode(5);
//        root.rightChild = new TreeNode(4);
//        root.leftChild.leftChild = new TreeNode(9);
//        root.leftChild.rightChild = new TreeNode(7);
//        root.leftChild.rightChild.leftChild = new TreeNode(1);
//        root.leftChild.rightChild.rightChild = new TreeNode(12);
//        root.leftChild.rightChild.rightChild.rightChild = new TreeNode(2);
//        root.rightChild.rightChild = new TreeNode(11);
//        root.rightChild.rightChild.leftChild = new TreeNode(3);
//
//
//        System.out.println(isGivenSumSubTreeExists(root, 0, 22));

//        TreeNode root = new TreeNode(5);
//        root.leftChild = new TreeNode(-10);
//        root.rightChild = new TreeNode(3);
//        root.leftChild.leftChild = new TreeNode(9);
//        root.leftChild.rightChild = new TreeNode(8);
//        root.rightChild.leftChild = new TreeNode(-4);
//        root.rightChild.rightChild = new TreeNode(7);


//        System.out.println(numberOfSubTreesForGivenSum(root, 7));

//        TreeNode root = new TreeNode(1);
//        root.leftChild = new TreeNode(2);
//        root.rightChild = new TreeNode(3);
//        root.leftChild.leftChild = new TreeNode(4);
//        root.leftChild.rightChild = new TreeNode(5);
//        root.rightChild.leftChild = new TreeNode(6);
//        root.rightChild.rightChild = new TreeNode(7);
//
//        System.out.println(sumOfMaxDepthLevelNodes(root, 1, getDepthOfBinaryTree(root)));

        TreeNode root = new TreeNode(10);
        root.leftChild = new TreeNode(8);
        root.rightChild = new TreeNode(2);
        root.leftChild.leftChild = new TreeNode(3);
        root.leftChild.rightChild = new TreeNode(5);
        root.rightChild.leftChild = new TreeNode(2);

//        System.out.println(isPathExistsForGivenSum(root, 0, 21));

        formedAllNumbersFromRootToLeafPath(root, new ArrayList<>());

        Integer totalSum = 0;
        for (Integer e : formedNumbersFromRootLeafPath)
            totalSum += e;
        System.out.println(totalSum);
    }
}
