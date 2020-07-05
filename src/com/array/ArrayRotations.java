package com.array;

import java.util.Arrays;

public class ArrayRotations {


    public static void leftRotateByReversing(int[] arr, int d) {

        reverse(arr, 0, d - 1);
        reverse(arr, d, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }

    }

    public static int searchElementInRotatedSortedArray(int[] arr, int l, int h, int key) {

        if (l <= h) {
            int mid = l + h >> 1;

            if (arr[mid] == key)
                return mid;

            if (arr[l] <= arr[mid]) {
                if (key >= arr[l] && key <= arr[mid])
                    return searchElementInRotatedSortedArray(arr, l, mid - 1, key);
                return searchElementInRotatedSortedArray(arr, mid + 1, h, key);
            } else {
                if (key >= arr[mid] && key <= arr[h])
                    return searchElementInRotatedSortedArray(arr, mid + 1, h, key);
                return searchElementInRotatedSortedArray(arr, l, mid - 1, key);
            }
        }

        return -1;
    }

    public static void printArray(int[] arr) {

        Arrays.stream(arr).forEach(x -> System.out.print(x + ", "));

    }

    public static void main(String[] args) {

//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        leftRotateByReversing(arr, 4);
//        printArray(arr);

        int arr[] = {4, 5, 6, 7, 8, 9, 10, 1, 2, 3};

        System.out.println(searchElementInRotatedSortedArray(arr, 0, arr.length - 1, 3));
    }
}
