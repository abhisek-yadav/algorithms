package com.queue;

import java.util.*;

public class StandardProblemQueue {

    public static int pageFault(int[] input, int slot) {

        int pageFault = 0;
        Queue queue = new Queue();
        Set<Integer> hash = new HashSet<>();

        for (int i = 0; i < input.length; i++) {
            if (!hash.contains(input[i])) {
                if (queue.size() == slot) {
                    int element = queue.dequeue();
                    hash.remove(element);
                }

                ++pageFault;
                queue.enqueue(input[i]);
                hash.add(input[i]);
            }
        }

        return pageFault;
    }

    public static void generateNumberUsing0And1(int n) {

        java.util.Queue<String> queue = new LinkedList<String>();

        queue.add("1");

        while (n-- > 0) {

            String s1 = queue.poll();

            System.out.println(s1);

            String s2 = s1;

            s1 = s1 + "0";
            s2 = s2 + "1";

            queue.add(s1);
            queue.add(s2);
        }
    }

    public static void slidingWindow(int[] input, int k) {

        int j, max;

        for (int i = 0; i <= input.length - k; i++) {

            max = input[i];

            for (j = 1; j < k; j++) {
                if (input[i + j] > max)
                    max = input[i + j];
            }
            System.out.print(max + " ");
        }

    }


    static class Student {
        String name;
        float gpa;

        Student(String name, float gpa) {
            this.name = name;
            this.gpa = gpa;
        }


        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", gpa=" + gpa +
                    '}';
        }

    }

    public static void main(String[] args) {

//        int[] input = {0, 2, 1, 6, 4, 0, 1, 3, 2};

//        System.out.println(pageFault(input, 3));

        PriorityQueue<Student> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.gpa < o2.gpa)
                return 1;
            else if (o1.gpa == o2.gpa)
                return 0;
            else
                return -1;
        });
        queue.add(new Student("Sam", 6.7f));
        queue.add(new Student("Ajay", 7.7f));
        queue.add(new Student("Ajit", 7.8f));
        queue.add(new Student("Zuni", 6.8f));
        queue.add(new Student("Kaljit", 8.8f));
        queue.add(new Student("Abhi", 8.8f));


//        while (!queue.isEmpty())
//            System.out.println(queue.poll());


//        generateNumberUsing0And1(50);

        int[] input = {8, 2, 3, 7, 1, 3, 5, 9};

        slidingWindow(input, 3);


    }
}
