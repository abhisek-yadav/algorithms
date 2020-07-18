import java.util.*;
import java.util.stream.IntStream;

public class Test {

    /**
     * Largest minimum of a given number where digits of the output number are in increasing order.
     *
     * @param number
     * @return number
     */
    public static int largestMinimum(int number) {

        int temp = 0;
        boolean flag = false;
        while (number > 0) {
            --number;
            temp = number;

            int prevDigit = 9;
            while (temp > 0) {
                int digit = temp % 10;
                if (prevDigit < digit) {
                    flag = true;
                    break;
                }
                prevDigit = digit;
                temp = temp / 10;
            }

            if (flag) {
                flag = false;
                continue;
            } else break;
        }

        return number;
    }

    /**
     * Return the list of strings having highest frequency.
     *
     * @return list of strings
     */
    public static List<String> wordFrequency() {

        Map<String, Integer> wordFrequency = new HashMap<>();

        wordFrequency.put("abc", 1);
        wordFrequency.put("mno", 1);
        wordFrequency.put("xyz", 3);
        wordFrequency.put("jkl", 2);
        wordFrequency.put("fgh", 1);
        wordFrequency.put("wer", 3);
        wordFrequency.put("cde", 2);


        int maxCount = 0;
        List<String> result = new ArrayList<>();

        for (String key : wordFrequency.keySet()) {

            int count = wordFrequency.get(key);

            if (count > maxCount) {
                result.removeAll(result);
                result.add(key);
                maxCount = count;
            } else if (count == maxCount) {
                result.add(key);
            }

        }

        return result;
    }

    /**
     * Count the number of minimum operations required to insert all the given elements either from left or right.
     *
     * @param numbers
     * @return number of operations
     */
    public static long minimumOperations(List<Integer> numbers) {

        int total = 1;
        for (int i = 1; i < numbers.size(); i++) {
            int countLeft = 0;
            int countRight = 0;
            for (int j = 0; j < i; j++) {
                if (numbers.get(j) < numbers.get(i))
                    ++countLeft;
                else
                    ++countRight;
            }

            if (countLeft < countRight)
                total += (2 * countLeft) + 1;
            else
                total += (2 * countRight) + 1;

        }
        return total;
    }

    /**
     * Count the number of shines.
     * <p>
     * Shine will be incremented when all the previous bulbs/elements are turned on/appeared in the given input.
     *
     * @param arr
     * @return shines count
     */
    public static int countShines(int[] arr) {

        int[] temp = new int[arr.length + 1];
        int shines = 0;

        long millis = System.currentTimeMillis();

        for (int e : arr) {
            temp[e] = 1;

            boolean flag = true;
            for (int i = e - 1; i > 0; i--) {

                if (temp[i] == i)
                    break;

                if (temp[i] == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                temp[e] = e;
                ++shines;
            }
        }

        System.err.println("Total time taken: " + (System.currentTimeMillis() - millis) + " millis");

        return shines;
    }

    public static void main(String[] args) {

//        System.out.println(largestMinimum(343));

//        List<Integer> numbers = Arrays.asList(10, 6, 2, 3, 7, 1, 2);

//        System.out.println(minimumOperations(numbers));


        IntStream stream = IntStream.range(0, 1000000);
        int[] arr = stream.toArray();
        Arrays.stream(arr).forEach(e -> arr[e] = e);

        System.out.println(countShines(arr));
    }
}
