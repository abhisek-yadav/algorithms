package com.miscellaneous;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AbacusProblem {

    private static Random random = new Random();

    private static Map<Integer, Set<Integer>> positiveValidNumbers = new HashMap<>();
    private static Map<Integer, Set<Integer>> negativeValidNumbers = new HashMap<>();

    static {
        positiveValidNumbers.put(0, Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        positiveValidNumbers.put(1, Set.of(1, 2, 3, 5, 6, 7, 8));
        positiveValidNumbers.put(2, Set.of(1, 2, 5, 6, 7));
        positiveValidNumbers.put(3, Set.of(1, 5, 6));
        positiveValidNumbers.put(4, Set.of(5));
        positiveValidNumbers.put(5, Set.of(1, 2, 3, 4));
        positiveValidNumbers.put(6, Set.of(1, 2, 3));
        positiveValidNumbers.put(7, Set.of(1, 2));
        positiveValidNumbers.put(8, Set.of(1));
        positiveValidNumbers.put(9, Set.of(0));

        negativeValidNumbers.put(0, Set.of(0));
        negativeValidNumbers.put(1, Set.of(0, 1));
        negativeValidNumbers.put(2, Set.of(0, 1, 2));
        negativeValidNumbers.put(3, Set.of(0, 1, 2, 3));
        negativeValidNumbers.put(4, Set.of(0, 1, 2, 3, 4));
        negativeValidNumbers.put(5, Set.of(0, 5));
        negativeValidNumbers.put(6, Set.of(0, 1, 5, 6));
        negativeValidNumbers.put(7, Set.of(0, 1, 2, 5, 6, 7));
        negativeValidNumbers.put(8, Set.of(0, 1, 2, 3, 5, 6, 7, 8));
        negativeValidNumbers.put(9, Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    }

    public static char generateOperator() {

        String operator = "+-";

        return operator.charAt(random.nextInt(operator.length()));
    }

    /**
     * Method to generate total number of given 'numbers' of given number of 'digits' appended with operators('+' or '-').
     *
     * @param numbers total generated numbers
     * @param digits  digits of each number
     * @return generated expression
     */
    public static String generate(int numbers, int digits) {

        StringBuilder builder = new StringBuilder();
        AtomicInteger num = new AtomicInteger();

        IntStream stream = random.ints(1, 9);
        stream.limit(digits).forEach(d -> num.set(num.get() * 10 + d));

        builder.append(num);
        AtomicInteger previousNumber = new AtomicInteger(num.get());

        IntStream.range(1, numbers).forEach(i -> {

            Character key = generateOperator();

            builder.append(" ");
            builder.append(key);

            int number = 0;

            if (key.equals('+')) {

                int initialDigits = 0;
                while (num.get() > 0) {
                    int digit = num.get() % 10;

                    int randomDigit;
                    do {
                        randomDigit = random.nextInt(9);
                    } while (!positiveValidNumbers.get(digit).contains(randomDigit));

                    number += randomDigit * Math.pow(10, initialDigits);
                    ++initialDigits;
                    num.set(num.get() / 10);
                }

            }

            if (key.equals('-')) {

                int initialDigits = 0;
                while (num.get() > 0) {
                    int digit = num.get() % 10;

                    int randomDigit;
                    do {
                        randomDigit = random.nextInt(9);
                    } while (!negativeValidNumbers.get(digit).contains(randomDigit));

                    number += randomDigit * Math.pow(10, initialDigits);
                    ++initialDigits;
                    num.set(num.get() / 10);
                }

            }

            builder.append(" ");
            builder.append(number);
            applyPreviousOperation(previousNumber.get(), number, key).ifPresent(num::set);
            previousNumber.set(num.get());

        });

        return String.valueOf(builder);
    }

    public static Optional<Integer> applyPreviousOperation(int previousNumber, int number, char operator) {

        switch (operator) {
            case '+':
                return Optional.of(previousNumber + number);
            case '-':
                return Optional.of(previousNumber - number);
        }

        return Optional.empty();
    }

    public static void main(String[] args) {

//        System.out.println(generate(3, 2));

        IntStream.range(0, 50).forEach(e -> System.out.println(generate(3, 2)));

    }

}
