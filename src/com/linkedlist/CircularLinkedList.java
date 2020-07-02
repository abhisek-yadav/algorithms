package com.linkedlist;

public class CircularLinkedList {

    static Node splitHead1;
    static Node splitHead2;


    public static void splitCircularLinkedListInTwo(Node head) {

        Node node = head;

        if (node == null)
            return;

        Node slowPointer = node;
        Node fastPointer = node;

        do {
            fastPointer = fastPointer.next;
            if (fastPointer.next != head) {
                fastPointer = fastPointer.next;
                slowPointer = slowPointer.next;
            }
        } while (fastPointer.next != head);

        Node slowPointerNext = slowPointer.next;
        splitHead2 = slowPointerNext;
        fastPointer.next = splitHead2;

        slowPointer.next = head;

        splitHead1 = head;
    }


    public static void printCircularLinkedList(Node head) {

        Node node = head;

        if (node == null)
            return;

        do {
            System.out.print(node.data + " ");
            node = node.next;
        } while (node != head);
    }

    public static void printCircularLinkedListII(Node head) {

        Node node = head;

        while (node.next != head) {
            System.out.print(node.data + " ");
            node = node.next;
        }

        System.out.print(node.data);
    }

    public static void main(String[] args) {

        Node head = new Node(2);
        head.next = new Node(5);
        head.next.next = new Node(8);
        head.next.next.next = new Node(9);
        head.next.next.next.next = new Node(11);
        head.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next = head;
//        head.next.next.next.next.next.next = new Node(15);
//        head.next.next.next.next.next.next.next = head;

//        printCircularLinkedList(head);

//        splitCircularLinkedListInTwo(head);

//        printCircularLinkedList(splitHead1);
//        System.out.println();
//        printCircularLinkedList(splitHead2);



        printCircularLinkedListII(head);

    }

}
