package com.codility;


public class CodilityTest {

    /**
     * Finding out the smallest missing number starting from 1 from a given array.
     * <p>
     * Time Complexity:  O(n)
     * Space Complexity:  O(1)
     *
     * @param A
     * @return
     */
    public static int missingInteger(int[] A) {

        int result = 1;

        for (int i = 0; i < A.length; i++) {

            int num = A[i];

            if (num <= 0 || num >= A.length)
                continue;

            while (num > 0 && num < A.length && A[num - 1] != num) {
                int n = A[num];
                A[num - 1] = num;
                num = n;
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] == i + 1)
                ++result;
            else
                break;
        }

        return result;
    }


    public static void main(String[] args) {

//        int[] arr = {1, 3, 6, 4, 1, 2};
        int[] arr = {1, 2, 3};

        System.out.println(missingInteger(arr));

    }
}




