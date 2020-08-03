package com.codility;


import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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

    /**
     * Finding out the smallest missing number starting from 1 from a given array.
     * <p>
     * Time Complexity:  O(n)
     * Space Complexity:  O(n)
     *
     * @param A
     * @return
     */
    public static int missingIntegerUsingHash(int[] A) {

        int result = 1;
        Set<Integer> hash = new HashSet<>();

        Arrays.stream(A).filter(e -> e > 0).forEach(hash::add);

        while (hash.contains(result))
            ++result;

        return result;
    }

    /**
     * Algorithm to find the larger number than the given number where sum of the digits of new number are same as the given number.
     *
     * @param N
     * @return
     */
    public static int largerThanGivenNumber(int N) {
        // write your code in Java SE 8

        int temp = N;
        int sum = 0;

        while (temp > 0) {
            sum = sum + temp % 10;
            temp = temp / 10;

        }

        if (sum == 1) {
            return N * 10;
        }

        int number;
        while (true) {
            temp = ++N;
            number = temp;
            int s = 0;

            while (temp > 0) {
                s = s + temp % 10;
                temp = temp / 10;
            }
            if (s == sum)
                break;
        }

        return number;
    }

    /**
     * Problem description:
     *
     * Given U : 3  (Sum of upper row)
     * Given L : 2  (Sum of lower  row)
     *
     *          1 0 1 0 1
     *          1 0 0 1 0
     * Given C: 2 0 1 1 1
     *
     * Find out the with the given U and L and C, is it possible to have the above combination?
     *
     * @param U
     * @param L
     * @param C
     * @return
     */
    public static String isPossibleCombination(int U, int L, int[] C) {
        // write your code in Java SE 8

        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();

        for (int i = 0; i < C.length; i++) {

            if (C[i] == 2) {
                if (L > 0 && U > 0) {
                    --L;
                    --U;
                    result1.append("1");
                    result2.append("1");
                } else
                    return "IMPOSSIBLE";
            } else if (C[i] == 0) {
                result1.append("0");
                result2.append("0");
            } else {
                if (U > 0) {
                    --U;
                    result1.append("1");
                    result2.append("0");
                } else if (L > 0) {
                    --L;
                    result1.append("0");
                    result2.append("1");
                } else
                    return "IMPOSSIBLE";
            }

        }

        if (U != 0 || L != 0)
            return "IMPOSSIBLE";

        return result1 + "," + result2;
    }

    /**
     * Sum of A[0..K-1] is equal to A[K...N]
     * <p>
     * Sum of B[0..L-1] is equal to B[L...N]
     * <p>
     * Find out the count of all matching indexes of K and L from the two given arrays.
     * <p>
     * If any of the K and L indexes doesn't match return 0.
     *
     * @param A
     * @param B
     * @return
     */
    public static int countIndexesForEqualLeftRightSum(int[] A, int[] B) {

        List<Integer> countA = check(A);
        List<Integer> countB = check(B);

        if (countA.size() != countB.size())
            return 0;

        int count = 0;

        for (int i = 0; i < countA.size(); i++) {
            if (countA.get(i).equals(countB.get(i)))
                ++count;
            else return 0;
        }


        return count;

    }

    private static List<Integer> check(int[] A) {
        // write your code in Java SE 8

        List<Integer> count = new ArrayList<>();

        int rightSum = 0;

        for (int i = 0; i < A.length; i++) {
            rightSum += A[i];
        }


        int leftSum = 0;

        for (int i = 0; i < A.length; i++) {

            if (rightSum == leftSum)
                count.add(i);
            leftSum += A[i];
            rightSum -= A[i];
        }

        return count;

    }

    /**
     * Given array [4,3,2,1] should return 1.
     * <p>
     * Given array [4,3,1] should return 0, because 2 is missing.
     * <p>
     * If there are duplicate elements, algorithm should return 0.
     *
     * @param A
     * @return
     */
    public static int permCheck(int[] A) {

        AtomicInteger maxValue = new AtomicInteger(0);
        AtomicBoolean doesExists = new AtomicBoolean(false);
        Set<Integer> numbers = new HashSet<>();

        Arrays.stream(A).takeWhile(e -> {
            if (numbers.contains(e)) {
                doesExists.set(true);
                return false;
            }
            return true;
        }).forEach(e -> {
            if (e > maxValue.get())
                maxValue.set(e);
            numbers.add(e);
        });


        if (doesExists.get())
            return 0;

        int max = maxValue.get();

        while (max > 0) {
            if (numbers.contains(max)) {
                --max;
            } else return 0;
        }

        return 1;
    }

    public static int binaryGap(int N) {

        String binary = Integer.toBinaryString(N);
        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger max = new AtomicInteger(0);
        AtomicBoolean isStart = new AtomicBoolean(false);

        Stream.of(binary.split("")).forEach(c -> {

            if (c.equals("1") && !isStart.get())
                isStart.set(true);

            if (c.equals("0") && isStart.get())
                count.getAndAdd(1);

            if (c.equals("1") && isStart.get()) {
                if (count.get() > max.get())
                    max.set(count.get());

                count.set(0);
            }

        });

        return max.get();
    }

    public static void main(String[] args) {

//        int[] arr = {1, 3, 6, 4, 1, 2};
//        int[] arr = {1, 2, 3};

//        System.out.println(missingInteger(arr));

//        System.out.println(missingIntegerUsingHash(arr));

//        System.out.println(largerThanGivenNumber(398));

//        System.out.println(isPossibleCombination(3, 2, new int[]{2, 0, 1, 1, 1}));

//        int[] arr = {-2, 5, 0, 3};
//        int[] arr1 = {-1, 4, 1, 4};
        int[] arr = {4, -1, 0, 3};
        int[] arr1 = {5, -1, 0, 4};

//        System.out.println(countIndexesForEqualLeftRightSum(arr, arr1));

//        System.out.println(permCheck(new int[]{4, 3, 2, 1}));

        System.out.println(binaryGap(32));

    }
}




