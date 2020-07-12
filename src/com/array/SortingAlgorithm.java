package com.array;

import java.util.Arrays;

public class SortingAlgorithm {


    public static void quickSort(int[] arr, int l, int h) {

        if (l < h) {
            int pivot = pivotEnd(arr, l, h);
            quickSort(arr, l, pivot - 1);
            quickSort(arr, pivot + 1, h);
        }
    }

    public static int pivotEnd(int[] arr, int l, int h) {

        int pos = h;
        int pivot = arr[h];
        int i = l;
        int j = l;

        while (j < pos) {

            if (arr[j] < pivot) {
                swap(arr, i, j);
                ++i;
            }
            ++j;
        }

        swap(arr, i, pos);

        return i;
    }

    public static void quickSort1(int[] arr, int l, int h) {

        if (l < h) {
            int pivot = pivotMiddle(arr, l, h);
            quickSort1(arr, l, pivot - 1);
            quickSort1(arr, pivot + 1, h);
        }
    }

    public static int pivotMiddle(int[] arr, int l, int h) {

        int mid = l + h >>> 1;

        int low = l;
        int pivot = arr[mid];
        swap(arr, l, mid);

        l = l + 1;

        while (l < h) {

            while (arr[l] < pivot && l < h)
                ++l;

            while (arr[h] > pivot)
                --h;

            if (l < h) {
                swap(arr, l, h);
                ++l;
                --h;
            }
        }

        swap(arr, low, h);

        return h;
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int x = arr[i];

            while (j >= 0 && arr[j] > x) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = x;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void printArray(int[] arr) {

        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));

    }

    public static void main(String[] args) {

        int[] arr = {8, 9, 7, 6, 2, 4, 5, 10, 1, 3};

//        quickSort(arr, 0, arr.length - 1);

//        quickSort1(arr, 0, arr.length - 1);

//        insertionSort(arr);

        bubbleSort(arr);
        printArray(arr);
    }

}
