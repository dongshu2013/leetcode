package edu.northwestern.shu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Twitter {

  public static final String EEE = "EEE";
  public static final String N = "None of these";
  public static final String III = "III";
  public static final String YES = "Yes";
  public static final String NO = "No";

  public boolean isValid(String s) {
    char[] stack = new char[s.length()];
    int cursor = -1;

    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack[++cursor] = c;
      }

      if (c == ')' || c == ']' || c == '}') {
        if (cursor == -1 || stack[cursor] != getCounterPart(c)) {
          return false;
        }
        cursor--;
      }
    }

    return cursor == -1;
  }

  public char getCounterPart(char c) {
    if (c == ')') {
      return '(';
    } else if (c == ']') {
      return '[';
    } else if (c == '}') {
      return '{';
    }
    return ' ';
  }

  public static String checkTriangle(String[] nums) {
    int a = Integer.parseInt(nums[0]);
    int b = Integer.parseInt(nums[1]);
    int c = Integer.parseInt(nums[2]);

    if (a + b > c && a + c > b && b + c > a) {
      if (a == b && b == c) {
        return III;
      } else if (a == b || b == c || a == c) {
        return EEE;
      }
    }
    return N;
  }

  public String check(String s) {
    Stack<Character> st = new Stack<Character>();
    int i = 0;
    while (i < s.length()) {
      if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
        st.push(s.charAt(i));
      }

      if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
        if (st.isEmpty()) {
          return NO;
        }

        if (s.charAt(i) == ')') {
          if (st.pop() != '(') {
            return NO;
          }
        }

        if (s.charAt(i) == ']') {
          if (st.pop() != '(') {
            return NO;
          }
        }

        if (s.charAt(i) == '}') {
          if (st.pop() != '{') {
            return NO;
          }
        }
      }
      i++;
    }
    
    if (st.isEmpty()) {
      return YES;
    }

    return NO;
  }

  public List<String> readFromSTDIN() {
    List<String> result = new ArrayList<String>();
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input;

      while ((input = br.readLine()) != null) {
        result.add(input);
      }
    } catch (IOException io) {
      io.printStackTrace();
    }
    return result;
  }

  public static void main(String[] args) {
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for (int i = 0; i < n; i++) {
      String[] numbers = sc.nextLine().split(" ");
      if (numbers.length != 3) {
        System.out.println(N);
      } else {
        System.out.println(checkTriangle(numbers));
      }
    }
  }
}
