package com.linkedlist;

public class DoublyNode {

    int data;
    DoublyNode next;
    DoublyNode prev;

    public DoublyNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "" + data;
    }

}
