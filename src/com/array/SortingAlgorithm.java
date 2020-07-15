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

    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[k])
                    k = j;
            }
            swap(arr, i, k);
        }

    }

    public static void mergeSort(int[] arr, int l, int h) {

        if (l < h) {
            int mid = l + h >>> 1;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, h);
            merge(arr, l, mid, h);
        }

    }

    public static void merge(int[] arr, int l, int mid, int h) {

        int[] arr1 = new int[arr.length];

        int i = l;
        int j = mid + 1;
        int k = l;

        while (i <= mid && j <= h) {

            if (arr[i] < arr[j])
                arr1[k++] = arr[i++];
            else
                arr1[k++] = arr[j++];

        }

        while (i <= mid)
            arr1[k++] = arr[i++];

        while (j <= h)
            arr1[k++] = arr[j++];

        for (int x = l; x <= h; x++) {
            arr[l++] = arr1[x];
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

//        bubbleSort(arr);

//        selectionSort(arr);

        mergeSort(arr, 0, arr.length - 1);

        printArray(arr);
    }

}
