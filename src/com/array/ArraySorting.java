package com.array;

public class ArraySorting {

    public static int pos(int[] arr, int l, int h) {

        int i = l;
        int pivot = arr[h];

        for (int j = l; j < h; j++) {

            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, h);

        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort(int[] arr, int l, int h) {

        if (l < h) {
            int pos = pos(arr, l, h);
            quickSort(arr, l, pos - 1);
            quickSort(arr, pos + 1, h);
        }
    }

    public static void main(String[] args) {

        int[] arr = {7, 1, 2, 3, 4, 5, 6};
        quickSort(arr, 0, arr.length - 1);

        for (int e : arr) {
            System.out.print(e + ", ");
        }
    }
}
