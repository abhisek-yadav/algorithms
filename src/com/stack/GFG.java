package com.stack;

import java.util.Stack;

// Java program to determine
// whether given expression
// is balanced/ parenthesis
// expression or not.
class GFG {
    // Function to check if two
    // brackets are matching or not.

    static int isMatching(char a,
                          char b) {
        if ((a == '{' && b == '}')
                || (a == '[' && b == ']')
                || (a == '(' && b == ')') || a == 'X') {
            return 1;
        }
        return 0;
    }

    // Recursive function to
    // check if given expression
    // is balanced or not.
    static int isBalanced(String s,
                          Stack<Character> ele,
                          int ind) {

        // Base case.
        // If the string is balanced
        // then all the opening brackets
        // had been popped and stack
        // should be empty after string
        // is traversed completely.
        if (ind == s.length()) {
            if (ele.size() == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        // variable to store element
        // at the top of the stack.
        char topEle;

        // variable to store result
        // of recursive call.
        int res;

        // Case 1: When current element
        // is an opening bracket
        // then push that element
        // in the stack.
        if (s.charAt(ind) == '{'
                || s.charAt(ind) == '('
                || s.charAt(ind) == '[') {
            ele.push(s.charAt(ind));
            return isBalanced(s, ele, ind + 1);
        } // Case 2: When current element
        // is a closing bracket then
        // check for matching bracket
        // at the top of the stack.
        else if (s.charAt(ind) == '}'
                || s.charAt(ind) == ')'
                || s.charAt(ind) == ']') {

            // If stack is empty then there
            // is no matching opening bracket
            // for current closing bracket and
            // the expression is not balanced.
            if (ele.size() == 0) {
                return 0;
            }

            topEle = ele.peek();
            ele.pop();

            // Check bracket is
            // matching or not.
            if (isMatching(topEle, s.charAt(ind)) == 0) {
                return 0;
            }

            return isBalanced(s, ele, ind + 1);
        } // Case 3: If current element
        // is 'X' then check for both
        // the cases when 'X' could be
        // opening or closing bracket.
        else if (s.charAt(ind) == 'X') {
            Stack<Character> tmp = new Stack<>();
            //because in java, direct assignment copies only reference and not the whole object
            tmp.addAll(ele);
            tmp.push(s.charAt(ind));
            res = isBalanced(s, tmp, ind + 1);
            if (res == 1) {
                return 1;
            }
            if (ele.size() == 0) {
                return 0;
            }
            ele.pop();
            return isBalanced(s, ele, ind + 1);
        }
        return 1;
    }

    // Driver Code
    public static void main(String[] args) {

        String s = "XX";
        Stack<Character> ele = new Stack<Character>();

        if (isBalanced(s, ele, 0) != 0) {
            System.out.println("Balanced");
        } else {
            System.out.println("Not Balanced");
        }
    }
}

