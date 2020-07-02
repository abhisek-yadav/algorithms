import java.util.*;

public class Test {


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

    public static long minimumOperations(List<Integer> numbers) {
        // Write your code here

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

    public static void main(String[] args) {

        System.out.println(largestMinimum(343));

        List<Integer> numbers = Arrays.asList(10, 6, 2, 3, 7, 1, 2);

        System.out.println(minimumOperations(numbers));
    }
}
