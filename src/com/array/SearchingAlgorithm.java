package com.array;

import java.util.Arrays;
import java.util.HashSet;

public class SearchingAlgorithm {

    public static void twoSmallestInAnArray(int[] arr) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < x) {
                y = x;
                x = arr[i];
            } else if (arr[i] < y) {
                y = arr[i];
            }
        }
        System.out.println("Two smallest elements: " + x + " " + y);
    }

    public static void twoLargestElementInAnArray(int[] arr) {

        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x) {
                y = x;
                x = arr[i];
            } else if (arr[i] > y) {
                y = arr[i];
            }
        }
        System.out.println("Two largest elements: " + x + " " + y);
    }


    public static int searchInRotatedSorted(int[] arr, int x, int l, int h) {

        if (l <= h) {

            int mid = l + h >>> 1;

            if (arr[mid] == x) {
                return mid;
            }

            if (l < mid && arr[l] <= arr[mid - 1]) {
                if (x >= arr[l] && x <= arr[mid - 1]) {
                    return searchInRotatedSorted(arr, x, l, mid - 1);
                } else {
                    return searchInRotatedSorted(arr, x, mid + 1, h);
                }
            } else if (h > mid && arr[mid + 1] <= arr[h]) {
                if (x >= arr[mid + 1] && x <= arr[h]) {
                    return searchInRotatedSorted(arr, x, mid + 1, h);
                } else {
                    return searchInRotatedSorted(arr, x, l, mid - 1);
                }
            } else
                return -1;

        }

        return -1;
    }


    public static int noOfOccurrencesInSortedArray(int[] arr, int x, int l, int h) {
        int count = 0;

        int mid = l + h >>> 1;

        if (l <= h) {

            if (arr[mid] == x) {
                count += 1;
                int i = mid - 1;
                int j = mid + 1;

                while (i >= l && arr[i] == x) {
                    count++;
                    i--;
                }

                while (j <= h && arr[j] == x) {
                    count++;
                    j++;
                }

                return count;

            } else if (arr[mid] > x) {
                return noOfOccurrencesInSortedArray(arr, x, l, mid - 1);
            } else if (arr[mid] < x) {
                return noOfOccurrencesInSortedArray(arr, x, mid + 1, h);
            }
        }

        return count;
    }


    public static int medianOfTwoSortedArrays(int[] arr1, int[] arr2) {

        int i = 0;
        int j = 0;
        int m1 = -1;
        int m2 = -1;
        int m = arr1.length - 1;
        int n = arr2.length - 1;


        if ((m + n) % 2 == 1) {
            for (int x = 0; x <= (arr1.length + arr2.length) / 2; x++) {
                if (i <= m && j <= n) {
                    m1 = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
                } else if (i <= m) {
                    m1 = arr1[i++];
                } else if (j <= n) {
                    m1 = arr2[j++];
                }
            }
            return m1;
        } else {
            for (int x = 0; x <= (arr1.length + arr2.length) / 2; x++) {
                m2 = m1;
                if (i <= m && j <= n) {
                    m1 = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
                } else if (i <= m) {
                    m1 = arr1[i++];
                } else if (j <= n) {
                    m1 = arr2[j++];
                }
            }
            return (m1 + m2) / 2;
        }
    }

    public static int smallestElementInRotatedSortedArray(int[] arr, int l, int h) {

        if (l <= h) {
            int mid = l + h >>> 1;

            if (mid > l && arr[mid] < arr[mid - 1])
                return arr[mid];

            if (mid < h && arr[mid] > arr[mid + 1])
                return arr[mid + 1];

            if (mid == l || mid == h)
                return arr[mid];

            if (arr[h] > arr[mid])
                return smallestElementInRotatedSortedArray(arr, l, mid - 1);
            return smallestElementInRotatedSortedArray(arr, mid + 1, h);

        }
        return -1;
    }

    private static int posInRotatedSortedArray(int[] arr) {

        int l = 0;
        int h = arr.length - 1;

        while (l <= h) {

            int mid = l + h >>> 1;

            if (mid > l && arr[mid - 1] > arr[mid])
                return mid;
            if (mid < h && arr[mid + 1] < arr[mid])
                return mid + 1;

            else if (arr[h] > arr[mid])
                h = mid - 1;

            else
                l = mid + 1;
        }
        return -1;
    }

    public static void pairWithGivenSumInRotatedSorted(int[] arr, int x) {

        int n = arr.length;
        int l = posInRotatedSortedArray(arr);
        int h = (n + l - 1) % n;

        while (l != h) {

            if (x == arr[l] + arr[h]) {
                System.out.println("Pair: " + arr[l] + " " + arr[h]);
                break;
            } else if (x > arr[l] + arr[h])
                l = (l + 1) % n;
            else
                h = (n + h - 1) % n;

        }

    }

    public static int largestSumPair(int[] arr) {
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x) {
                y = x;
                x = arr[i];
            } else if (arr[i] > y)
                y = arr[i];
        }
        return x + y;
    }

    public static int elementWithLeftSmallerRightLargerInUnSortedArray(int[] arr) {

        int[] leftMax = new int[arr.length];
        leftMax[0] = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++)
            leftMax[i] = Math.max(leftMax[i - 1], arr[i - 1]);
//
//        int[] rightMin = new int[arr.length];
//        rightMin[arr.length - 1] = Integer.MAX_VALUE;
//
//        for (int j = arr.length - 2; j >= 0; j--)
//            rightMin[j] = Math.min(rightMin[j + 1], arr[j + 1]);

//        for (int k = arr.length - 1; k >= 0; k--) {
//            if (arr[k] >= leftMax[k] && arr[k] <= rightMin[k])
//                System.out.println("element: " + arr[k]);
//        }

        int rightMin = Integer.MAX_VALUE;
        for (int k = arr.length - 1; k >= 0; k--) {
            if (leftMax[k] <= arr[k] && rightMin >= arr[k])
                return k;

            rightMin = Math.min(arr[k], rightMin);
        }

        return -1;
    }

    public static void sumEqualToNWithConsecutiveNumbers(int n) {

        int start = 1;
        int end = 1;
        int sum = 1;

        while (start <= n / 2) {

            if (sum < n) {
                end += 1;
                sum += end;
            } else if (sum > n) {
                sum -= start;
                start += 1;
            } else if (sum == n) {

                for (int i = start; i <= end; i++)
                    System.out.print(i + ",");

                System.out.println();

                sum -= start;
                start += 1;
            }
        }
    }

    public static void twoElementsSumEqualToRestOfElementsInArray(int[] arr) {

        int sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i];

        if (sum % 2 != 0)
            System.exit(0);

        HashSet<Integer> elements = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (elements.contains((sum / 2) - arr[i]))
                System.out.println(arr[i] + ", " + (sum / 2 - arr[i]));

            elements.add(arr[i]);
        }

    }

    public static void shiftAllOccurrenceToEndInArray(int[] arr, int x) {

        for (int i = 0, j = 0; i < arr.length; i++, j++) {
            if (arr[i] == x) {
                while (i + 1 < arr.length && arr[i] == x)
                    i += 1;
            }
            swap(arr, i, j);
        }

        for (int e : arr)
            System.out.print(e + ", ");

    }

    private static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }


    public static void threeElementsWithMinDifference(int[] a1, int[] a2, int[] a3) {

        int x = -1;
        int y = -1;
        int z = -1;

        Arrays.sort(a1);
        Arrays.sort(a2);
        Arrays.sort(a3);

        int i = 0;
        int j = 0;
        int k = 0;
        int diff = Integer.MAX_VALUE;
        while (i < a1.length && j < a2.length && k < a3.length) {

            int sum = a1[i] + a2[j] + a3[k];
            int max = Math.max(Math.max(a1[i], a2[j]), a3[k]);
            int min = Math.min(Math.min(a1[i], a2[j]), a3[k]);
            int mid = sum - (max + min);

            if (min == a1[i])
                i++;
            else if (min == a2[j])
                j++;
            else k++;

            if (diff > (max - min)) {
                diff = max - min;
                x = max;
                y = min;
                z = sum - (x + y);

            }
        }

        System.out.println(x + ", " + y + ", " + z);

    }


    public static void main(String[] args) {

//        int[] arr = {1};
//        System.out.println(searchInRotatedSorted(arr, 3, 0, arr.length - 1));
//
//        int[] arr1 = {12, 25, 8, 55, 10, 33, 17, 11};
//        twoSmallestInAnArray(arr1);
//
//        int[] arr2 = {12, 25, 8, 55, 10, 33, 17, 11};
//        twoLargestElementInAnArray(arr2);

//        int arr3[] = {1, 2, 2, 2, 2, 2, 3, 4};
//        System.out.println(noOfOccurrencesInSortedArray(arr3, 2, 0, arr3.length - 1));


//        int[] arr1 = {10, 30, 50, 70, 90, 100, 110};
//        int[] arr2 = {20, 40, 44};

//        System.out.println(medianOfTwoSortedArrays(arr1, arr2));


//        int[] arr = {2, 3, 4, 5, 6, 8, 1};
//        System.out.println(smallestElementInRotatedSortedArray(arr, 0, arr.length - 1));

//        int[] arr = {5, 6, 7, 8, 9, 10, 11, 1, 2, 3, 4};

//        pairWithGivenSumInRotatedSorted(arr, 20);

//        int[] arr = {12, 34, 10, 6, 40};
//        System.out.println(largestSumPair(arr));

//        int[] arr = {5, 1, 4, 3, 6, 8, 10, 7, 9};

//        elementWithLeftSmallerRightLargerInUnSortedArray(arr);

//        sumEqualToNWithConsecutiveNumbers(125);

//        int[] arr = {2, 11, 5, 1, 4, 7};
//        twoElementsSumEqualToRestOfElementsInArray(arr);

//        int[] arr = {1, 2, 2, 4, 3, 1, 2, 1};

//        shiftAllOccurrenceToEndInArray(arr, 1);

//
//        int arr1[] = {5, 2, 8};
//        int arr2[] = {10, 7, 12};
//        int arr3[] = {9, 14, 6};
//
//        threeElementsWithMinDifference(arr1, arr2, arr3);

    }
}
