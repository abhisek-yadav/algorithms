package com.tree;


public class TreeNode {
    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        TreeNode node = (TreeNode) o;
        if (this.data == node.data)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }

}
