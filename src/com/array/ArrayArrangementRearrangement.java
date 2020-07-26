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
     * <p>
     * time complexity:  ~ O(n)
     * space complexity:   O(1)
     *
     * @param arr
     */
    public static void arrangePositiveNegativeAlternatePosition(int[] arr) {


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

    /**
     * Move all the 0's to end of the array.
     * <p>
     * Order of elements must be preserved.
     * <p>
     * time complexity:  ~ O(n)
     * space complexity:   O(1)
     *
     * @param arr
     */
    public static void moveZerosToEnd(int[] arr) {

        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0)
                swap(arr, i, j++);
        }

    }

    /**
     * Arrange the number in such that all negative integers appear before all the positive integers.
     * <p>
     * Order of elements must be preserved.
     *
     * @param arr
     */
    public static void arrangeNegativePositiveNumber(int[] arr) {

        int j = 0;
        int i = 0;

        while (i < arr.length) {

            if (arr[i] > 0) {
                j = i;
                while (i < arr.length) {
                    if (arr[i] > 0)
                        ++i;
                    else
                        break;
                }

                // exit from the outer loop
                if (i == arr.length)
                    break;

                rightShift(arr, j, i);
                i = j + 1;

            } else
                ++i;
        }
    }

    private static void rightShift(int[] arr, int x, int y) {

        int e = arr[y];

        while (y > x) {
            arr[y] = arr[y - 1];
            --y;
        }

        arr[y] = e;
    }

    public static void rearrangeWithGivenIndex(int[] arr, int[] index) {

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

    /**
     * arr[i] = j is changed to arr[j] = i.
     * <p>
     * This algorithm works only when there is no cycle.
     *
     * @param arr
     */
    public static void rearrangeBasedOnIndexedElements(int[] arr) {


        int i = arr[0];
        int j = 0;
        int prevIndex = 0;


        while (j < arr.length) {

            int replacedElement = arr[i];
            arr[i] = prevIndex;
            prevIndex = i;
            i = replacedElement;
            ++j;
        }


    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));
    }

    public static void main(String[] args) {

//        int[] arr = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};
//        rearrangeElementsBasedOnIndex(arr);


//        int[] arr = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
//        int[] arr1 = {-1, -2, 3, -4, -5, -6, 7, -8, 9};
//        arrangePositiveNegativeAlternatePosition(arr);


//        int[] arr = {1, 2, 0, 4, 3, 0, 5, 0, 8};
//        int[] arr = {0, 0, 0, 4, 3, 0, 5, 0, 8};
//        moveZerosToEnd(arr);

//        int[] arr = {12, 11, -13, -5, 6, -7, 5, -3, -6};
//        int[] arr = {12, 11, 13, 5, 6, 7, 5, 3, 6};

//        arrangeNegativePositiveNumber(arr);

//        int[] arr = {50, 40, 70, 60, 90};
//        int[] index = {3, 0, 4, 1, 2};
//        rearrangeWithGivenIndex(arr, index);


        int[] arr = {1, 3, 0, 2};

        rearrangeBasedOnIndexedElements(arr);

        printArray(arr);
    }
}
