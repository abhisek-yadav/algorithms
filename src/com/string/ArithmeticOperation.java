package com.string;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticOperation {

    static class CharFrequency {
        char c;
        int frequency;

        CharFrequency(char c, int frequency) {
            this.c = c;
            this.frequency = frequency;
        }
    }


    public static int minStringValue(String s, int k) {

        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++)
            ++freq[s.charAt(i) - 'a'];

        PriorityQueue<CharFrequency> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.frequency < o2.frequency)
                return 1;
            else if (o1.frequency > o2.frequency)
                return -1;
            else
                return 0;
        });

        for (int i = 0; i < freq.length; i++) {

            if (freq[i] > 0)
                queue.add(new CharFrequency((char) (i + 'a'), freq[i]));

        }

        while (k > 0) {
            CharFrequency charFrequency = queue.poll();
            charFrequency.frequency--;
            queue.add(charFrequency);
            --k;
        }

        int res = 0;


        while (!queue.isEmpty()) {

            CharFrequency charFrequency = queue.poll();

            res += charFrequency.frequency * charFrequency.frequency;

        }

        return res;
    }

    public static int replaceDigitInGiven(int number, int from, int to) {

        int result = 0;
        int multiply = 1;

        while (number > 0) {

            int remainder = number % 10;

            if (remainder == from) {
                result = result + to * multiply;
            } else {
                result = result + remainder * multiply;
            }

            multiply *= 10;
            number /= 10;
        }

        return result;
    }

    public static String sumOfTwoNumbers(String num1, String num2) {

        String result = "";
        int carry = 0;

        String revNum1 = new StringBuffer(num1).reverse().toString();
        String revNum2 = new StringBuffer(num2).reverse().toString();


        if (revNum1.length() > revNum2.length()) {
            String t = revNum1;
            revNum1 = revNum2;
            revNum2 = t;
        }

        int n = revNum1.length();
        int m = revNum2.length();

        for (int i = 0; i < n; i++) {

            int sum = (((int) revNum1.charAt(i) - '0') + ((int) revNum2.charAt(i) - '0') + carry);
            result += (char) (sum % 10 + '0');
            carry = sum / 10;
        }


        for (int i = n; i < m; i++) {
            int sum = (((int) revNum2.charAt(i) - '0') + carry);
            result += (char) (sum % 10 + '0');
            carry = sum / 10;
        }

        if (carry > 0) {
            result += (char) (carry + '0');
        }

        return new StringBuffer(result).reverse().toString();
    }

    public static int sumOfNumbersPresentInBetweenGivenString(String s) {

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int num = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                int d = (int) s.charAt(i) - '0';
                num = num * 10 + d;
                ++i;
            }
            result += num;
            num = 0;
        }

        return result;
    }

    public static int maxNumericValueFromGivenString(String s) {

        int max = Integer.MIN_VALUE;

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);

        while (m.find()) {
            int num = Integer.parseInt(m.group());

            if (num > max)
                max = num;

        }

        return max;
    }

    public static int maxNumberUsingADDMULOperator(String s) {

        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(s);

        int result = 0;

        while (m.find()) {

            int digit = Integer.parseInt(m.group());

            if (digit == 1 || digit == 0 || result < 2) {
                result += digit;
            } else
                result *= digit;
        }

        return result;
    }

    public static int findMaxSegment(String number, int k) {

        int max = Integer.MIN_VALUE;
        int maxNumberDigitCount = number.length() - k;
        int num = 0;

        for (int i = 0; i < number.length(); i++) {
            int count = maxNumberDigitCount;
            for (int j = i; count > 0 && j < number.length(); j++) {
                num = num * 10 + (int) number.charAt(j) - '0';
                if (num > max) {
                    max = num;
                }
                --count;
            }
            num = 0;
        }

        return max;
    }

    public static int noOfSubstringsDivisibleBy8Not3(String number) {

        int count = 0;

        for (int i = 0; i < number.length(); i++) {
            int sumOfDigitsSoFar = 0;
            int num = 0;

            for (int j = i; j < number.length(); j++) {

                int digit = (int) number.charAt(j) - '0';

                if (i == j && digit % 8 == 0 && digit % 3 != 0)
                    ++count;

                num = num * 10 + digit;
                sumOfDigitsSoFar += digit;

                if (i != j) {
                    if (num % 8 == 0 && sumOfDigitsSoFar % 3 != 0)
                        ++count;
                }
            }
            num = 0;
            sumOfDigitsSoFar = 0;
        }

        return count;
    }


    public static int remainderWithGivenNumber(String num, int n) {

        int carry = 0;

        for (int i = 0; i < num.length(); i++) {
            int numSoFar = carry * 10 + (int) num.charAt(i) - '0';
            carry = numSoFar % n;
        }

        return carry;
    }


    public static void main(String[] args) {

//        System.out.println(minStringValue("abccc", 1));


//        System.out.println(replaceDigitInGiven(645, 5, 6));


//        System.out.println(sumOfTwoNumbers("12", "198111"));

//        System.out.println(sumOfNumbersPresentInBetweenGivenString("1abc2x30yz67"));


//        System.out.println(maxNumericValueFromGivenString("1abc2x30yz67"));

//        System.out.println(maxNumberUsingADDMULOperator("891"));


//        System.out.println(findMaxSegment("122854", 2));

//        System.out.println(noOfSubstringsDivisibleBy8Not3("6564525600"));

        System.out.println(remainderWithGivenNumber("3435346456547566345436457867978", 11));
    }

}
