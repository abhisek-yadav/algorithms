package com.codility;


import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

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
        Set<Integer> hash = Arrays.stream(A).filter(e -> e > 0).boxed().collect(toSet());

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

    public static int[] cyclicRotate(int[] A, int K) {

        if (A.length == 0)
            return A;

        int rotations = K % A.length;

        for (int i = 1; i <= rotations; i++) {
            int e = A[A.length - 1];

            for (int j = A.length - 2; j >= 0; j--)
                A[j + 1] = A[j];
            A[0] = e;
        }
        return A;
    }

    public static int oddOccurrencesInArray(int[] A) {

        Map<Integer, Integer> occurrences = new HashMap<>();

        Arrays.stream(A).forEach(e -> {
            if (occurrences.containsKey(e))
                occurrences.put(e, occurrences.get(e) + 1);
            else occurrences.put(e, 1);

        });

        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            if (entry.getValue() % 2 != 0)
                return entry.getKey();
        }

        return 0;
    }

    public static int frogMinimumJumps(int X, int Y, int D) {

        if (X == Y)
            return 0;

        int remainingDistance = Y - X;

        if (remainingDistance <= D)
            return 1;

        int distanceCount = remainingDistance / D;
        int leftOverDistance = remainingDistance % D;

        if (leftOverDistance > 0)
            distanceCount += 1;

        return distanceCount;
    }

    public static int permMissingElement(int[] A) {

        AtomicInteger totalSum = new AtomicInteger(0);

        Arrays.stream(A).forEach(e -> totalSum.set(totalSum.get() + e));

        int requiredSum = ((A.length + 1) * (A.length + 2)) >>> 1;

        return requiredSum - totalSum.get();
    }

    public static int tapeEquilibrium(int[] A) {

        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);

        AtomicInteger rightSum = new AtomicInteger(0);
        AtomicInteger leftSum = new AtomicInteger(0);

        Arrays.stream(A).forEach(e -> rightSum.set(rightSum.get() + e));

        Arrays.stream(A).limit((long) (A.length - 1)).forEach(e -> {

            rightSum.set(rightSum.get() - e);
            leftSum.set(leftSum.get() + e);

            int minSum = Math.min(rightSum.get(), leftSum.get());
            int maxSum = Math.max(rightSum.get(), leftSum.get());

            int diff = Math.abs(maxSum - minSum);

            if (diff < min.get())
                min.set(diff);

        });

        return min.get();
    }

    public static int frogRiverOne(int[] A, int X) {

        Map<Integer, Integer> visited = new HashMap<>();
        AtomicInteger position = new AtomicInteger();
        AtomicBoolean flag = new AtomicBoolean(false);

        IntStream.range(0, A.length).takeWhile(i -> {
            if (visited.size() < X) {
                position.set(i);
            } else {
                int e = X;
                while (visited.containsKey(e))
                    --e;
                if (e != 0) {
                    position.set(i);
                    return true;
                }
                flag.set(true);
                return false;
            }
            return true;
        }).forEach(i -> visited.put(A[i], 1));

        if (flag.get() || visited.size() == X)
            return position.get();

        return -1;
    }

    public static int frogRiverOneUsingArray(int[] A, int X) {

        int[] visited = new int[X + 1];

        for (int i = 0; i < A.length; i++) {
            visited[A[i]] = 1;
            boolean flag = true;
            for (int x = X; x > 0; x--) {
                if (visited[x] != 1) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return i;
        }
        return -1;
    }

    /**
     * Utility method to traverse a stream in reverse order.
     *
     * @param from
     * @param to
     * @return
     */
    private static IntStream reverseRange(int from, int to) {
        return IntStream.range(from, to).map(i -> to - i + from - 1);
    }

    /**
     * Not an optimized solution.
     *
     * @param N
     * @param A
     * @return
     */
    public static int[] maxCounter(int N, int[] A) {

        Map<Integer, Integer> count = new HashMap<>();
        IntStream.rangeClosed(1, N + 1).forEach(i -> count.put(i, 0));

        AtomicInteger maxCountBeforeLargestNumber = new AtomicInteger();

        IntStream.range(0, A.length).forEach(i -> {
            if (A[i] <= N) {
                count.put(A[i], count.get(A[i]) + 1);
                if (count.get(A[i]) > maxCountBeforeLargestNumber.get())
                    maxCountBeforeLargestNumber.set(count.get(A[i]));
            } else {
                IntStream.range(1, N + 1).forEach(j -> count.put(j, maxCountBeforeLargestNumber.get()));
            }
        });

        int[] actualResult = new int[N];

        for (int i = 1; i <= N; i++)
            actualResult[i - 1] = count.get(i);


        return actualResult;
    }

    public static int[] maxCounter1(int N, int[] A) {

        Map<Integer, Integer> count = new HashMap<>();

        AtomicInteger maxCount = new AtomicInteger();
        TreeMap<Integer, Integer> occurrence = new TreeMap<>();
        IntStream.range(0, A.length).forEach(i -> {
            if (A[i] <= N) {
                if (count.containsKey(A[i]))
                    count.put(A[i], count.get(A[i]) + 1);
                else
                    count.put(A[i], 1);
                if (count.get(A[i]) > maxCount.get())
                    maxCount.set(count.get(A[i]));
            } else {
                occurrence.put(i, maxCount.get());
                count.clear();
                maxCount.set(0);
            }
        });

        AtomicInteger index = new AtomicInteger();
        AtomicInteger totalOccurrence = new AtomicInteger();
        if (!occurrence.isEmpty()) {
            index.set(occurrence.lastKey());
            totalOccurrence.set(occurrence.values().stream().mapToInt(e -> e).sum());
        }

        IntStream.rangeClosed(1, N + 1).forEach(i -> count.put(i, totalOccurrence.get()));

        IntStream.range(index.get(), A.length).forEach(i -> count.put(A[i], count.get(A[i]) + 1));

        int[] actualResult = new int[N];

        for (int i = 1; i <= N; i++)
            actualResult[i - 1] = count.get(i);

        return actualResult;
    }

    /**
     * Calculate the number of elements between A and B which are divisible by K.
     *
     * @param A
     * @param B
     * @param K
     * @return count of elements
     */
    public static int countDiv(int A, int B, int K) {

        if (A == 0 && B == 0)
            return 1;

        int S;
        if (A % K == 0)
            S = A;
        else
            S = A + (K - A % K);

        int X = B - S;

        if (X < 0)
            return 0;

        if (X == 0) {
            if (A % K == 0)
                return 1;
            return 0;
        }

        return X / K + 1;
    }

    /**
     * Find out the minimum average of a slice.
     * <p>
     * Minimum length of a slice is 2.
     *
     * @param A
     * @return
     */
    public static int minAvgTwoSlice(int[] A) {

        double average = Double.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < A.length - 1; i++) {

            double currentAverage;
            int sum = A[i];

            for (int j = i + 1; j < A.length && j < i + 3; j++) {
                sum += A[j];
                currentAverage = (sum) / (j - i + 1d);

                if (currentAverage < average) {
                    average = currentAverage;
                    index = i;
                }
            }
        }

        return index;
    }

    public static int passingCars(int[] A) {

        AtomicLong totalSum = new AtomicLong(Arrays.stream(A).sum());

        AtomicLong totalPairs = new AtomicLong();

        Arrays.stream(A).forEach(e -> {
            if (e == 0)
                totalPairs.set(totalPairs.get() + totalSum.get());
            else
                totalSum.decrementAndGet();
        });


        if (totalPairs.get() > 1000000000) {
            return -1;
        }

        return (int) totalPairs.get();
    }

    public static int[] genomicRangeQuery(String S, int[] P, int[] Q) {

        int[] result = new int[P.length];

        IntStream.range(0, P.length).forEach(i -> {

            String sub = S.substring(P[i], Q[i] + 1);

            if (sub.matches(".*[A].*"))
                result[i] = 1;
            else if (sub.matches(".*[C].*"))
                result[i] = 2;
            else if (sub.matches(".*[G].*"))
                result[i] = 3;
            else if (sub.matches(".*[T].*"))
                result[i] = 4;

        });

        return result;
    }

    public static int distinctNumbers(int[] A) {

        Set<Integer> distinctNumbers = Arrays.stream(A).boxed().collect(toSet());

        return distinctNumbers.size();
    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));
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
//        int[] arr = {4, -1, 0, 3};
//        int[] arr1 = {5, -1, 0, 4};
//        System.out.println(countIndexesForEqualLeftRightSum(arr, arr1));

//        System.out.println(permCheck(new int[]{4, 3, 2, 1}));

//        System.out.println(binaryGap(32));

//        int[] arr = {3, 8, 9, 7, 6};
//        cyclicRotate(arr, 5);
//        printArray(arr);

//        int[] arr = {9, 3, 9, 3, 9, 7, 9};
//        System.out.println(oddOccurrencesInArray(arr));


//        System.out.println(frogMinimumJumps(5, 45, 20));

//        int[] arr = {2, 3, 1, 5};
//        System.out.println(permMissingElement(arr));

//        int[] arr = {3, 1, 2, 4, 3};
//        int[] arr = {-10, -20, -30, -40, 100};
//        int[] arr = {-1000, 1000};
//        System.out.println(tapeEquilibrium(arr));


//        int[] arr = {1, 3, 1, 4, 2, 3, 5, 4};
//        System.out.println(frogRiverOne(arr, 3));

//        int[] arr = {5, 4, 3, 2, 4, 8, 3, 2, 3, 1};
//        System.out.println(frogRiverOneUsingArray(arr, 1));


//        int[] arr = {3, 4, 4, 6, 1, 4, 4};
//        int[] arr = {1, 2, 3, 4, 5};
//        int[] arr = {3, 4, 4, 6, 1, 2, 2, 6, 4, 5};

//        printArray(maxCounter(5, arr));
//        printArray(maxCounter1(5, arr));

//        System.out.println(countDiv(11, 19, 5));

//        int[] arr = {-1, -5, -8, -9, -11, -11};

//        System.out.println(minAvgTwoSlice(arr));

//        int[] arr = {0, 1, 0, 1, 1};
//        System.out.println(passingCars(arr));

//        int[] p = {2, 5, 0};
//        int[] q = {4, 5, 6};

//        String s = "CAGCCTA";

//        printArray(genomicRangeQuery(s, p, q));

        int[] arr = {2, 1, 1, 2, 3, 1};

        System.out.println(distinctNumbers(arr));
    }
}




