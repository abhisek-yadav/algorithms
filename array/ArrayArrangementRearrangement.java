package com.array;

public class ArrayArrangementRearrangement {

    public static void reorderWithGivenIndex(int[] arr, int[] index) {

        for (int i = 0; i < arr.length; i++) {

            while (index[i] != i) {
                int newIndex = index[i];
                int element = arr[i];

                int replacedElement = arr[newIndex];
                int replacedElementNewIndex = index[newIndex];

                arr[newIndex] = arr[i];
                index[newIndex] = newIndex;

                arr[i] = replacedElement;
                index[i] = replacedElementNewIndex;
            }

        }

    }

    public static void main(String[] args) {

        int[] arr = {50, 40, 70, 60, 90};
        int[] index = {3, 0, 4, 1, 2};

        reorderWithGivenIndex(arr, index);

        for (int e : arr)
            System.out.print(e + ", ");
    }
}
