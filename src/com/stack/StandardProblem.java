package com.stack;

import java.util.Arrays;

public class StandardProblem {

    public static void stockSpanProblem(int[] arr, int n, int[] ans) {

        ans[0] = 1;

        for (int i = 1; i <= n; i++) {

            int counter = 1;

            while ((i - counter) >= 0 && arr[i] > arr[i - counter]) {
                counter += ans[i - counter];
            }

            ans[i] = counter;
        }


        for (int e : ans)
            System.out.print(e + " ");

    }

    public static void main(String[] args) {

        int[] arr = {10, 4, 5, 90, 120, 80};

        stockSpanProblem(arr, arr.length - 1, new int[arr.length]);

    }


}
