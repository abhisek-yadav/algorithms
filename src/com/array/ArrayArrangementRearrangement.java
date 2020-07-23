package com.array;

import java.util.Arrays;

public class ArrayArrangementRearrangement {


    public static void rearrangeElementsBasedOnIndex(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            int num = arr[i];

            if (num == -1)
                continue;

            arr[i] = -1;

            while (num != -1 && arr[num] != num) {
                int n = arr[num];
                arr[num] = num;
                num = n;
            }
        }
    }

    public static void reorderWithGivenIndex(int[] arr, int[] index) {

        for (int i = 0; i < arr.length; i++) {

            while (index[i] != i) {
                int newIndex = index[i];

                int replacedElement = arr[newIndex];
                int replacedElementNewIndex = index[newIndex];

                arr[newIndex] = arr[i];
                index[newIndex] = newIndex;

                arr[i] = replacedElement;
                index[i] = replacedElementNewIndex;
            }

        }

    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));
    }

    public static void main(String[] args) {

        int[] arr = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};

        rearrangeElementsBasedOnIndex(arr);

//        int[] arr = {50, 40, 70, 60, 90};
//        int[] index = {3, 0, 4, 1, 2};

//        reorderWithGivenIndex(arr, index);

        printArray(arr);

    }
}
