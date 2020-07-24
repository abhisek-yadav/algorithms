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

    /**
     * Arrange the elements(positive/negative) of a given array at alternate positions.
     *
     * time complexity:  ~ O(n)
     * space complexity:   O(1)
     *
     * @param arr
     */
    public static void arragePositiveNegativeAlternatePosition(int[] arr) {


        int i = arr.length - 1;

        for (int j = 0; j < arr.length; j++) {

            if (j % 2 == 0) {
                if (arr[j] < 0) {
                    while (i > j) {
                        if (arr[i] > 0) {
                            swap(arr, i, j);
                            i = arr.length - 1;
                            break;
                        } else
                            --i;
                    }
                }
            } else {
                if (arr[j] > 0) {
                    while (i > j) {
                        if (arr[i] < 0) {
                            swap(arr, i, j);
                            i = arr.length - 1;
                            break;
                        } else
                            --i;
                    }
                }
            }

            if (j == i)
                break;
        }

    }

    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

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

//        int[] arr = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};

//        rearrangeElementsBasedOnIndex(arr);


//        int[] arr = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
        int[] arr = {-1, -2, 3, -4, -5, -6, 7, -8, 9};

        arragePositiveNegativeAlternatePosition(arr);

//        int[] arr = {50, 40, 70, 60, 90};
//        int[] index = {3, 0, 4, 1, 2};

//        reorderWithGivenIndex(arr, index);

        printArray(arr);

    }
}
