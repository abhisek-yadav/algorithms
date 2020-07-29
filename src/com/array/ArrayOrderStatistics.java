package com.array;

import java.util.Arrays;

public class ArrayOrderStatistics {

    /**
     * Find the Kth smallest element in an unsorted array.
     *
     * @param arr
     * @param l
     * @param h
     * @param k
     * @return Kth smallest element
     */
    public static int quickSelect(int[] arr, int l, int h, int k) {

        if (k >= l && l <= h) {

            int pivot = pivot(arr, l, h);

            if (k - 1 == pivot)
                return arr[pivot];

            if (k - 1 < pivot)
                return quickSelect(arr, l, pivot - 1, k);

            return quickSelect(arr, pivot + 1, h, k);
        }

        return -1;
    }

    private static int pivot(int[] arr, int l, int h) {

        int pivot = arr[h];
        int i = l;
        int j = l;

        while (j < h) {

            if (arr[j] < pivot) {
                swap(arr, i, j);
                ++i;
            }
            ++j;
        }
        swap(arr, i, h);

        return i;
    }

    private static void swap(int[] arr, int x, int y) {

        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;

    }


    /**
     * Find k smallest elements in an given unsorted array.
     * <p>
     * This algorithm uses Bubble Sort k times.
     * <p>
     * time complexity: O(n*k)
     * <p>
     * Worst case complexity will be:  O(n*n) when k is equal to the length of an array.
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] kSmallestElements(int[] arr, int k) {

        if (k > arr.length)
            throw new IllegalArgumentException("k can not be greater than array length");

        if (k == arr.length) {
            Arrays.sort(arr);
            return arr;
        }

        int[] elements = new int[k];

        for (int i = 0; i < k; i++) {
            for (int j = 1; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }

        int e = 0;

        for (int x = arr.length - 1; x >= arr.length - k; x--)
            elements[e++] = arr[x];

        return elements;
    }

    public static int[] largestThreeElements(int[] arr) {

        int x = 0;
        int y = 0;
        int z = 0;

        for (int value : arr) {

            if (value > x) {
                z = y;
                y = x;
                x = value;
            } else if (value > y) {
                z = y;
                y = value;
            } else if (value > z) {
                z = value;
            }
        }

        return new int[]{x, y, z};
    }


    public static void insertionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int j = i;
            int element = arr[j];

            while (j > 0 && element < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = element;
        }

        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));
    }

    // Kadane's Algorithm
    public static void largestSumContigiousSubArray(int[] arr) {

        int maxSoFar = 0;
        int maxTillHere = 0;
        int startIndex = 0;
        int endIndex = 0;
        int s = 0;

        for (int i = 0; i < arr.length; i++) {

            maxTillHere += arr[i];
            if (maxTillHere < 0) {
                maxTillHere = 0;
                s = i + 1;

            }

            if (maxSoFar < maxTillHere) {
                maxSoFar = maxTillHere;
                startIndex = s;
                endIndex = i;
            }
        }

        System.out.println("max sum: " + maxSoFar + ", start index: " + startIndex + ", end index: " + endIndex);

    }

    public static void largestSumContigiousSubArrayWithNegative(int[] arr) {
        int currentMax = arr[0];
        int maxSoFar = arr[0];
        int startIndex = 0;
        int endIndex = 0;
        int s = 0;


        for (int i = 1; i < arr.length; i++) {

            int currentSum = currentMax + arr[i];
            if (currentSum > arr[i]) {
                currentMax = currentSum;
            } else {
                currentMax = arr[i];
                s = i;
            }

            if (maxSoFar < currentMax) {
                maxSoFar = currentMax;
                startIndex = s;
                endIndex = i;
            }
        }

        System.out.println("max sum: " + maxSoFar + ", start index: " + startIndex + ", end index: " + endIndex);

    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));
    }

    public static void main(String[] args) {

//        int[] arr = new int[]{-5, -3, 4, 1, -2, 1, -5, 3};

//        largestSumContigiousSubArray(arr);

//        largestSumContigiousSubArrayWithNegative(arr);

//        insertionSort(arr);

//        int[] arr = {7, 10, 4, 3, 20, 15, 1, 2, 5, 6, 8, 9};
//        System.out.println(quickSelect(arr, 0, arr.length - 1, 2));

//        int[] a = kSmallestElements(arr, 12);
//        printArray(a);


        int[] arr = {17, 10, 4, 13, 20, 15, 1, 2, 5, 6, 8, 19};

        int[] a = largestThreeElements(arr);

        printArray(a);

        printArray(arr);
    }
}
