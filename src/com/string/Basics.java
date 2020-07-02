package com.string;

import java.util.stream.Stream;

public class Basics {

    public static boolean isPangram(String s) {

        int[] charIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                int index = s.charAt(i) - 'a';
                charIndex[index] = 1;

            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                int index = s.charAt(i) - 'A';
                charIndex[index] = 1;
            }
        }

        for (int e : charIndex) {
            if (e != 1)
                return false;
        }

        return true;
    }


    public static void missingCharactersToMakeGivenStringAsPangram(String s) {

        int[] charIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                int index = s.charAt(i) - 'a';
                charIndex[index] = 1;
            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                int index = s.charAt(i) - 'A';
                charIndex[index] = 1;
            }
        }

        for (int i = 0; i < charIndex.length; i++) {
            if (charIndex[i] != 1)
                System.out.print(((char) (i + 97)));

        }

    }

    public static String noSameAdjacentForGivenString(String s) {

        char[] chars = s.toCharArray();
        int l = chars.length;

        for (int i = 0; i < l; i++) {
            int j = i + 1;
            int k = j;
            while (k < l && chars[k] == chars[i])
                ++k;

            if (k < l) {
                swapCharacters(chars, j, k);
            }
        }

        return new String(chars);
    }


    public static boolean isNumber(String s) {

        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (!Character.isDigit(c))
                return false;
        }

        return true;
    }

    private static void swapCharacters(char[] chars, int j, int k) {
        char temp = chars[j];
        chars[j] = chars[k];
        chars[k] = temp;
    }

    public static void main(String[] args) {

        String s = "The quick brown fox jumps over the lazy dog";

//        System.out.println(isPangram(s));

//        missingCharactersToMakeGivenStringAsPangram(s);


//        System.out.println(noSameAdjacentForGivenString(noSameAdjacentForGivenString("aaaaaacccccabbbbcbbccaababbbbb")));


        String s1 = "111.1";

        System.out.println(isNumber(s1));

    }
}
