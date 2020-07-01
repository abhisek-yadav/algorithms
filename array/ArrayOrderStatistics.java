package com.array;

public class ArrayOrderStatistics {

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

        for (int e : arr) {
            System.out.print(e + ", ");
        }
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


    public static void main(String[] args) {

        int[] arr = new int[]{-5, -3, 4, 1, -2, 1, -5, 3};

//        largestSumContigiousSubArray(arr);

//        largestSumContigiousSubArrayWithNegative(arr);

        insertionSort(arr);

    }
}
