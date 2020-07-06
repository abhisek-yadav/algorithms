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

    public static void twoElementsWithGivenSum(int[] arr, int sum) {

        boolean flag = false;
        int n = arr.length;
        int l = findPivotInRotatedSortedArray(arr, 0, n - 1);
        int r = (n + l - 1) % n;

        while (l != r) {
            if (sum < arr[l] + arr[r]) {
                r = (n + r - 1) % n;
            } else if (sum > arr[l] + arr[r]) {
                l = (l + 1) % n;
            } else {
                System.out.println("Two elements: " + arr[l] + ", " + arr[r]);
                flag = true;
                break;
            }
        }

        if (!flag)
            System.out.println("No elements found");

    }

    public static int findPivotInRotatedSortedArray(int[] arr, int l, int h) {

        if (l <= h) {

            int mid = (l + h) >>> 1;

            if (mid > l && arr[mid] < arr[mid - 1])
                return mid;

            if (mid < h && arr[mid] > arr[mid + 1])
                return mid + 1;

            if (l == mid || mid == h)
                return mid;

            if (arr[h] > arr[mid])
                return findPivotInRotatedSortedArray(arr, l, mid);
            return findPivotInRotatedSortedArray(arr, mid + 1, h);
        }

        return -1;
    }

    public static int maxSum(int[] arr) {

        int resSum = 0;
        int currSum = 0;

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = (i + j) % n;
                currSum += j * arr[index];
            }
            resSum = Math.max(resSum, currSum);
            currSum = 0;
        }

        return resSum;
    }

    // For O(log n) : @findPivotInRotatedSortedArray
    //This is  O(n)
    public static int numberOfRotations(int[] arr) {

        int minIndex = 0;
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > arr[(i + 1) % n])
                minIndex = (i + 1) % n;
        }

        return minIndex;
    }


    public static void printArray(int[] arr) {

        Arrays.stream(arr).forEach(x -> System.out.print(x + ", "));

    }

    public static void main(String[] args) {

//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        leftRotateByReversing(arr, 4);
//        printArray(arr);

//        int arr[] = {4, 5, 6, 7, 8, 9, 10, 1, 2, 3};
//        System.out.println(searchElementInRotatedSortedArray(arr, 0, arr.length - 1, 3));

//        int arr[] = {9, 12, 15, 18, 1, 3, 5, 8};
//        twoElementsWithGivenSum(arr, 18);


//        int[] arr = {4, 1, 2, 3};
//        System.out.println(maxSum(arr));

        int[] arr = {4, 5, 6, 7, 8, 9, 1, 2, 3};

        System.out.println(numberOfRotations(arr));
    }
}
