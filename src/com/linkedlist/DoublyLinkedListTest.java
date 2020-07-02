package com.linkedlist;

import java.util.HashSet;

public class DoublyLinkedListTest {


    public static DoublyNode reverseDoublyLinkedList(DoublyNode head) {

        DoublyNode node = head;

        if (node == null)
            return null;

        while (node.next != null) {

            DoublyNode temp = node;
            node = node.next;

            temp.next = temp.prev;
            temp.prev = node;
        }

        node.next = node.prev;
        node.prev = null;

        head = node;

        return head;
    }


    public static DoublyNode reverseDoublyLinkedListRecursive(DoublyNode head) {

        if (head.next == null) {
            head.next = head.prev;
            head.prev = null;
            return head;
        }

        DoublyNode temp = head;
        head = head.next;

        temp.next = temp.prev;
        temp.prev = head;

        return reverseDoublyLinkedListRecursive(head);
    }

    private static DoublyNode partition(DoublyNode start, DoublyNode end) {

        if (start == null || end == null || start == end)
            return start;

        int pivot = end.data;
        DoublyNode curr = start;

        while (start != end) {

            if (start.data < pivot) {
                int temp = curr.data;
                curr.data = start.data;
                start.data = temp;

                curr = curr.next;

            }
            start = start.next;
        }

        int temp = curr.data;
        curr.data = end.data;
        end.data = temp;

        return curr.prev;

    }

    private static void quickSort(DoublyNode start, DoublyNode end) {

        if (start != end) {

            DoublyNode partitionPrev = partition(start, end);

            quickSort(start, partitionPrev);
            quickSort(partitionPrev.next, end);

        }

    }

    public static void quickSort(DoublyNode head) {

        DoublyNode start = head;
        DoublyNode end = start;

        if (start == null)
            return;

        while (end.next != null)
            end = end.next;

        quickSort(start, end);

    }

    public static DoublyNode deleteInDLLAtGivenPosition(DoublyNode head, int pos) {

        DoublyNode node = head;

        if (node == null)
            return null;

        if (pos == 1) {
            node = node.next;
            if (node != null)
                node.prev = null;

            head = node;

            return head;
        }

        int count = 1;

        while (count < pos && node != null) {
            ++count;
            node = node.next;
        }

        if (pos > count || node == null)
            return head;

        if (pos == count && node.next == null)
            node.prev.next = null;
        else if (pos == count) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        return head;
    }

    private static DoublyNode mergeTwoDoublyLinkedList(DoublyNode a, DoublyNode b) {

        if (a == null)
            return b;

        if (b == null)
            return a;

        if (a.data < b.data) {
            a.next = mergeTwoDoublyLinkedList(a.next, b);
            a.next.prev = a;
            a.prev = null;
            return a;
        } else {
            b.next = mergeTwoDoublyLinkedList(a, b.next);
            b.next.prev = b;
            b.prev = null;
            return b;
        }

    }

    public static DoublyNode deleteDuplicatesFromUnSortedDLL(DoublyNode head) {

        DoublyNode current = head;

        HashSet<Integer> alreadyOccurred = new HashSet<>();

        while (current != null) {

            if (alreadyOccurred.contains(current.data)) {
                head = deleteNode(head, current);
            } else
                alreadyOccurred.add(current.data);

            current = current.next;
        }

        return head;
    }

    private static DoublyNode deleteNode(DoublyNode head, DoublyNode delete) {

        if (head == null || delete == null)
            return head;

        if (head == delete) {
            head = head.next;
        }

        if (delete.next != null) {
            delete.next.prev = delete.prev;
        }

        if (delete.prev != null) {
            delete.prev.next = delete.next;
        }

        return head;

    }

    public static DoublyNode mergeSort(DoublyNode head) {

        if (head == null || head.next == null)
            return head;

        DoublyNode middle = getMiddle(head);

        DoublyNode nextToMiddle = middle.next;

        middle.next = null;

        DoublyNode left = mergeSort(head);
        DoublyNode right = mergeSort(nextToMiddle);

        return mergeTwoDoublyLinkedList(left, right);

    }

    private static DoublyNode getMiddle(DoublyNode node) {

        if (node == null)
            return null;

        DoublyNode slowPointer = node;
        DoublyNode fastPointer = node;

        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        return slowPointer;
    }

    public static DoublyNode rotateDLLByN(DoublyNode head, int n) {

        DoublyNode current = head;

        if (n <= 0)
            return head;

        for (int i = 1; i < n && current != null; i++)
            current = current.next;

        if (current == null || current.next == null)
            return head;

        DoublyNode newHead = current.next;
        DoublyNode last = newHead;

        while (last.next != null)
            last = last.next;

        last.next = head;
        head.prev = last;

        current.next.prev = null;
        current.next = null;

        head = newHead;

        return head;

    }

    public static void printDoublyLinkedList(DoublyNode head) {

        DoublyNode node = head;

        if (node == null)
            return;

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }

    }

    public static void main(String[] args) {

        DoublyNode head = new DoublyNode(3);
        head.next = new DoublyNode(5);
        head.next.prev = head;
        head.next.next = new DoublyNode(7);
        head.next.next.prev = head.next;
        head.next.next.next = new DoublyNode(9);
        head.next.next.next.prev = head.next.next;

//        printDoublyLinkedList(head);

//        printDoublyLinkedList(reverseDoublyLinkedList(head));

//        printDoublyLinkedList(reverseDoublyLinkedListRecursive(head));

//        quickSort(head);

//        printDoublyLinkedList(head);

//        printDoublyLinkedList(deleteInDLLAtGivenPosition(head, 5));

//        printDoublyLinkedList(mergeSort(head));

//        printDoublyLinkedList(deleteDuplicatesFromUnSortedDLL(head));

        printDoublyLinkedList(rotateDLLByN(head, 1));
    }

}
