package com.linkedlist;


public class SinglyLinkedList {

    public static Node insertAtFirst(Node head, int data) {

        if (head == null)
            return null;

        Node node = new Node(data);

        node.next = head;
        head = node;

        return head;
    }

    public static Node insertAtLast(Node head, int data) {

        Node node = head;

        if (node == null)
            return null;

        Node temp = new Node(data);

        while (node.next != null)
            node = node.next;

        node.next = temp;

        return head;

    }

    public static Node insertAfterGivenIndex(Node head, int data, int index) {

        Node node = head;

        if (node == null)
            return null;

        if (index < 0)
            return node;

        int count = 1;

        while (node.next != null && count < index) {
            node = node.next;
            ++count;
        }

        if (count == index) {
            Node temp = new Node(data);
            temp.next = node.next;
            node.next = temp;
        }

        return head;

    }

    public static Node deleteNodeForGivenKey(Node head, int key) {

        Node node = head;

        if (node == null)
            return null;

        if (node.data == key) {
            node = node.next;

            head = node;
            return head;
        }

        while (node.next != null && node.next.data != key)
            node = node.next;

        if (node.next == null)
            return head;

        node.next = node.next.next;

        return head;
    }

    public static Node deleteNodeAtGivenPosition(Node head, int pos) {

        Node node = head;

        if (node == null)
            return null;

        if (pos == 0) {
            node = node.next;

            head = node;
            return head;
        }

        int count = 0;
        Node prev = null;

        while (node != null) {

            if (count == pos) {
                prev.next = node.next;
            }

            ++count;
            prev = node;
            node = node.next;
        }

        return head;
    }

    public static int countNodeInLinkedList(Node head) {
        if (head == null)
            return 0;

        int count = 0;
        while (head != null) {
            ++count;
            head = head.next;
        }

        return count;
    }

    public static int countNodeInLinkedListRecursive(Node head, int count) {

        if (head == null)
            return count;

        return countNodeInLinkedListRecursive(head.next, count + 1);
    }

    public static boolean searchInLinkedList(Node head, int key) {

        if (head == null)
            return false;

        while (head != null) {
            if (head.data == key)
                return true;
            head = head.next;
        }

        return false;
    }

    public static boolean searchInLinkedListRecursive(Node head, int key) {

        if (head == null)
            return false;

        if (head.data == key)
            return true;

        return searchInLinkedList(head.next, key);

    }

    public static int getNodeInLinkedList(Node head, int index) {

        Node node = head;

        if (node == null)
            return -1;

        int count = 0;

        while (node != null) {
            if (count == index)
                return node.data;

            ++count;
            node = node.next;
        }

        return -1;
    }

    public static int NthNodeFromEnd(Node head, int n) {

        Node node = head;

        if (node == null)
            return -1;

        if (n < 0)
            return -1;

        Node p = node;
        Node q = node;

        int count = 0;
        while (count < n) {
            if (q == null)
                return -1;
            q = q.next;
            ++count;
        }

        while (q != null) {
            q = q.next;
            p = p.next;
        }

        return p.data;
    }


    public static int middleOfLinkedListRecursive(Node p, Node q) {

        if (q == null || q.next == null)
            return p.data;

        p = p.next;
        q = q.next.next;

        return middleOfLinkedListRecursive(p, q);
    }

    public static int getFrequencyForGivenKey(Node head, int key, int frequency) {

        if (head == null)
            return frequency;

        if (head.data == key)
            ++frequency;

        return getFrequencyForGivenKey(head.next, key, frequency);

    }

    public static Node removeDuplicateFromSortedLinkedList(Node head) {

        Node node = head;

        if (node == null)
            return null;

        while (node.next != null) {
            if (node.data == node.next.data) {
                node.next = node.next.next;
                continue;
            }
            node = node.next;
        }

        return head;
    }

    public static void removeDuplicateFromSortedLinkedListRecursive(Node head) {

        if (head == null)
            return;

        if (head.next != null && head.data == head.next.data) {
            head.next = head.next.next;
            removeDuplicateFromSortedLinkedListRecursive(head);
        }

        removeDuplicateFromSortedLinkedListRecursive(head.next);
    }

    public static Node swapGivenNodes(Node head, int x, int y) {

        Node node = head;

        if (node == null)
            return null;


        if (node.data == x || node.data == y) {

            if (node.data == x) {

                Node nodeX = node;
                Node nodeYPrev = node;

                while (nodeYPrev.next != null && nodeYPrev.next.data != y)
                    nodeYPrev = nodeYPrev.next;

                if (nodeYPrev.next == null)
                    return head;

                Node nodeY = nodeYPrev.next;
                nodeYPrev.next = nodeX;

                Node nodeYNext = nodeY.next;
                nodeY.next = nodeX.next;
                nodeX.next = nodeYNext;


                head = nodeY;

                return head;

            } else {

                Node nodeY = node;
                Node nodeXPrev = node;

                while (nodeXPrev.next != null && nodeXPrev.next.data != x)
                    nodeXPrev = nodeXPrev.next;


                Node nodeX = nodeXPrev.next;
                nodeXPrev.next = nodeY;

                Node nodeXNext = nodeX.next;
                nodeX.next = nodeY.next;
                nodeY.next = nodeXNext;

                head = nodeX;

                return head;
            }

        }

        Node prevX = node;
        Node prevY = node;


        while (prevX.next != null && prevX.next.data != x)
            prevX = prevX.next;

        if (prevX.next == null)
            return head;

        while (prevY.next != null && prevY.next.data != y)
            prevY = prevY.next;

        if (prevY.next == null)
            return head;


        Node nodeX = prevX.next;
        Node nodeY = prevY.next;


        prevY.next = nodeX;
        Node nodeYNext = nodeY.next;
        nodeY.next = nodeX.next;

        nodeX.next = nodeYNext;
        prevX.next = nodeY;

        return head;
    }

    static Node third;

    public static Node intersectionOfTwoSortedLinkedLists(Node head1, Node head2) {

        if (head1 == null || head2 == null)
            return third;

        if (head1.data > head2.data)
            intersectionOfTwoSortedLinkedLists(head1, head2.next);

        if (head1.data < head2.data)
            intersectionOfTwoSortedLinkedLists(head1.next, head2);


        if (head1.data == head2.data) {
            if (third == null) {
                Node temp = new Node(head1.data);
                third = temp;
            } else {
                Node node = third;
                while (node.next != null)
                    node = node.next;

                Node temp = new Node(head1.data);
                node.next = temp;
            }

            return intersectionOfTwoSortedLinkedLists(head1.next, head2.next);
        }

        return third;
    }


    private static Node pivot(Node start, Node end) {

        if (start == end || start == null || end == null)
            return start;

        Node pivotPrev = start;

        Node pivot = start;
        Node curr = start;

        while (start != end) {

            if (curr.data < end.data) {
                int temp = pivot.data;
                pivot.data = curr.data;
                curr.data = temp;

                pivotPrev = pivot;
                pivot = pivot.next;

            }

            curr = curr.next;
            start = start.next;
        }

        //swap "pivotPrev" and "end" node

        int temp = pivot.data;
        pivot.data = end.data;
        end.data = temp;

        return pivotPrev;

    }

    private static void quickSort(Node start, Node end) {

        if (start != end) {

            Node pivotPrev = pivot(start, end);

            quickSort(start, pivotPrev);
            quickSort(pivotPrev.next, end);

        }
    }


    public static void quickSort(Node head) {

        if (head == null)
            return;

        Node start = head;
        Node end = start;

        while (end.next != null)
            end = end.next;

        quickSort(start, end);
    }

    public static Node segregateEvenOdd(Node head) {

        Node first = head;

        if (first == null)
            return null;

        Node end = first;
        while (end.next != null)
            end = end.next;

        Node newEnd = end;
        Node prev = null;
        Node curr = first;

        while (curr.data % 2 != 0 && curr != end) {

            newEnd.next = curr;
            newEnd = curr;
            curr = curr.next;

            newEnd.next = null;
        }

        head = curr;

        while (curr != end && curr != null) {

            if (curr.data % 2 == 0) {
                prev = curr;
                curr = curr.next;

            } else {
                Node temp = curr;
                curr = curr.next;
                prev.next = curr;

                newEnd.next = temp;
                newEnd = temp;
                newEnd.next = null;
            }

        }

        if (end.data % 2 != 0 && newEnd != end) {
            prev.next = end.next;
            end.next = null;
            newEnd.next = end;
        }

        return head;
    }

    public static Node reverseLinkedList(Node head) {

        Node node = head;

        if (node == null)
            return null;

        Node prev = null;
        Node curr = node;
        Node next = null;

        while (curr != null) {

            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }

        head = prev;

        return head;

    }


    static Node reverseHead;

    public static void reverseLinkedListRecursive(Node node) {


        if (node == null)
            return;

        Node prev = node;
        node = node.next;

        reverseLinkedListRecursive(node);

        if (prev.next == null) {
            reverseHead = prev;
        } else {
            node.next = prev;
            prev.next = null;
        }
    }

    static Node start;
    static int startCount = 1;

    public static void modifyContentOfLinkedList(Node head, int count) {

        if (head == null)
            return;

        Node prev = head;
        head = head.next;

        modifyContentOfLinkedList(head, count + 1);

        if (start != prev && startCount < count) {
            start.data = start.data - prev.data;
            start = start.next;
            startCount++;
        }

    }


    public static void printLinkedList(Node head) {

        if (head == null)
            return;

        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(7);
        head.next.next.next = new Node(9);
        head.next.next.next.next = new Node(11);
        head.next.next.next.next.next = new Node(13);
        head.next.next.next.next.next.next = new Node(15);
        head.next.next.next.next.next.next.next = new Node(17);
        head.next.next.next.next.next.next.next.next = new Node(19);

//        printLinkedList(head);

//        System.out.println(countNodeInLinkedList(head));

//        printLinkedList(insertAtFirst(head, 1));

//        printLinkedList(insertAtLast(head, 11));

//        printLinkedList(insertAfterGivenIndex(head, 6, 4));

//        printLinkedList(deleteNodeForGivenKey(head, 9));

//        printLinkedList(deleteNodeAtGivenPosition(head, 2));

//        System.out.println(countNodeInLinkedListRecursive(head, 0));

//        System.out.println(searchInLinkedList(head, 11));

//        System.out.println(searchInLinkedListRecursive(head, 9));

//        System.out.println(getNodeInLinkedList(head, 3));

//        System.out.println(NthNodeFromEnd(head, 3));

//        System.out.println(middleOfLinkedListRecursive(head, head));

//        System.out.println(getFrequencyForGivenKey(head, 3, 0));


//        printLinkedList(removeDuplicateFromSortedLinkedList(head));

//        removeDuplicateFromSortedLinkedListRecursive(head);

//        printLinkedList(head);


//        printLinkedList(swapGivenNodes(head, 17, 19));


        Node head1 = new Node(2);
        head1.next = new Node(5);
        head1.next.next = new Node(8);
        head1.next.next.next = new Node(9);
        head1.next.next.next.next = new Node(11);
        head1.next.next.next.next.next = new Node(12);
//        head1.next.next.next.next.next.next = new Node(15);


//        printLinkedList(intersectionOfTwoSortedLinkedLists(head, head1));

//        quickSort(head1);

//        printLinkedList(head1);

//        printLinkedList(segregateEvenOdd(head1));


//        printLinkedList(reverseLinkedList(head1));


//        reverseLinkedListRecursive(head1);
//        printLinkedList(reverseHead);

        start = head1;

        modifyContentOfLinkedList(head1, 1);

        printLinkedList(head1);
    }

}
