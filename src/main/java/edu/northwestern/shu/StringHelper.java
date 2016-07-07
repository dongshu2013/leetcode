package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringHelper {
  public String reverseString(String s) {
    boolean hasSurrogate = false;
    char[] cs = s.toCharArray();
    int n = cs.length - 1;

    for (int i = (n - 1) >> 1; i >= 0; i--) {
      char temp1 = cs[i];
      char temp2 = cs[s.length() - i - 1];
      if (!hasSurrogate) {
        hasSurrogate = (temp1 >= Character.MIN_SURROGATE
            && temp1 <= Character.MAX_SURROGATE
            && temp2 >= Character.MIN_SURROGATE && temp2 >= Character.MAX_SURROGATE);
      }
      cs[i] = temp2;
      cs[s.length() - i - 1] = temp1;
    }

    if (hasSurrogate) {
      for (int i = 0; i < n; i++) {
        char temp1 = cs[i];
        if (Character.isLowSurrogate(temp1)) {
          char temp2 = cs[i + 1];
          if (Character.isHighSurrogate(temp2)) {
            cs[i] = temp2;
            cs[i + 1] = temp1;
          }
        }
      }
    }

    return new String(cs);
  }

  public boolean isVowel(char c) {
    return c == 'o' || c == 'e' || c == 'a' || c == 'i' || c == 'u' || c == 'O'
        || c == 'E' || c == 'A' || c == 'I' || c == 'U';
  }

  public String reverseVowels(String s) {
    char[] cs = s.toCharArray();
    int i = 0;
    int j = cs.length - 1;

    while (i < j) {
      while (i < cs.length && !isVowel(cs[i]))
        i++;
      while (j >= 0 && !isVowel(cs[j]))
        j--;
      if (i < j && i < cs.length && j >= 0) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
      }
      i++;
      j--;
    }
    return new String(cs);
  }

  private boolean isLetter(char c) {
    return Character.isAlphabetic(c) || Character.isDigit(c);
  }

  public boolean isPalindrome(String s) {
    if (s.length() == 0) {
      return true;
    }

    char[] cs = s.toCharArray();
    int i = 0;
    int j = cs.length - 1;
    while (i < j) {
      while (i < cs.length && !isLetter(cs[i]))
        i++;
      while (j >= 0 && !isLetter(cs[j]))
        j--;

      System.out.println(i + "," + j);
      if (i < j) {
        if (Character.toLowerCase(cs[i]) != Character.toLowerCase(cs[j])) {
          return false;
        }
        i++;
        j--;
      }
    }
    return true;
  }

  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      sb.insert(0, (char) ((--n) % 26 + 'A'));
      n /= 26;
    }
    return sb.toString();
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  public int myAtoi(String str) {
    int i = 0;
    while (i < str.length() && str.charAt(i) == ' ')
      i++;
    if (i == str.length()) {
      return 0;
    }

    int sign = 1;
    if (str.charAt(i) == '-') {
      sign = -1;
      i++;
    } else if (str.charAt(i) == '+') {
      i++;
    }

    long result = 0;
    while (i < str.length() && isDigit(str.charAt(i))) {
      result = result * 10 + str.charAt(i) - '0';
      if (result * sign < Integer.MIN_VALUE) {
        return Integer.MIN_VALUE;
      } else if (result * sign > Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
      }
      i++;
    }

    result = result * sign;
    return (int) result;
  }

  public int romanToInt(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    Map<Character, Integer> map = new HashMap<Character, Integer>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);

    int num = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      int cur = map.get(s.charAt(i));
      if (i < s.length() - 1 && cur < map.get(s.charAt(i + 1))) {
        num -= cur;
      } else {
        num += cur;
      }
    }
    return num;
  }

  public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
      int sum = carry;
      if (i >= 0) {
        sum += a.charAt(i) - '0';
      }

      if (j >= 0) {
        sum += b.charAt(j) - '0';
      }
      sb.insert(0, sum % 2);
      carry = sum / 2;
    }

    if (carry == 1) {
      sb.insert(0, 1);
    }
    return sb.toString();
  }

  public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length())
      return false;
    Map<Character, Character> s2t = new HashMap<Character, Character>();
    Map<Character, Character> t2s = new HashMap<Character, Character>();
    for (int i = 0; i < s.length(); i++) {
      char ss = s.charAt(i);
      char tt = t.charAt(i);
      if ((s2t.containsKey(ss) && s2t.get(ss) != tt)
          || (t2s.containsKey(tt) && t2s.get(tt) != ss)) {
        return false;
      }
      s2t.put(ss, tt);
      t2s.put(tt, ss);
    }
    return true;
  }

  public String getHint(String secret, String guess) {
    Map<Character, Integer> m = new HashMap<Character, Integer>();
    int A = 0;
    for (int i = 0; i < secret.length(); i++) {
      char s = secret.charAt(i);
      char g = guess.charAt(i);
      if (s == g) {
        A++;
        continue;
      }
      if (m.containsKey(s)) {
        m.put(s, m.get(s) + 1);
      } else {
        m.put(s, 1);
      }
    }

    int B = 0;
    for (int i = 0; i < guess.length(); i++) {
      char s = secret.charAt(i);
      char g = guess.charAt(i);
      if (s != g && m.containsKey(g) && m.get(g) > 0) {
        B++;
        m.put(g, m.get(g) - 1);
      }
    }

    return A + "A" + B + "B";
  }

  public int lengthOfLastWord(String s) {
    String[] ss = s.split(" ");
    if (ss.length == 0)
      return 0;
    return ss[ss.length - 1].length();
  }

  public int compareVersion(String version1, String version2) {
    String[] s1 = version1.split("\\.");
    String[] s2 = version2.split("\\.");

    int i1 = 0;
    int i2 = 0;
    for (int i = 0; i < Math.max(s1.length, s2.length); i++) {
      if (i < s1.length) {
        i1 = Integer.valueOf(s1[i]);
      }

      if (i < s2.length) {
        i2 = Integer.valueOf(s2[i]);
      }

      if (i1 > i2) {
        return 1;
      } else if (i1 < i2) {
        return -1;
      } else {
        i1 = 0;
        i2 = 0;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    StringHelper sr = new StringHelper();
    System.out.println(sr.compareVersion("1", "0"));
  }

  public String countAndSay(int n) {
    if (n == 1)
      return "1";
    String cur = "1";
    for (int i = 2; i <= n; i++) {
      cur = generateNext(cur);
    }
    return cur;
  }

  private String generateNext(String s) {
    String result = "";
    int count = 0;
    int pre = -1;
    for (int i = 0; i < s.length(); i++) {
      int cur = s.charAt(i) - '0';
      if (cur == pre) {
        count++;
      } else {
        if (pre != -1) {
          result += count + "" + pre;
        }
        pre = cur;
        count = 1;
      }
    }
    result += count + "" + pre;
    return result;
  }

  public int strStr(String haystack, String needle) {
    if (needle.length() == 0 && haystack.length() == 0)
      return 0;
    for (int i = 0; i <= haystack.length() - needle.length(); i++) {
      if (haystack.substring(i, i + needle.length()).equals(needle)) {
        return i;
      }
    }
    return -1;
  }

  public String zigZag(String s, int numRows) {
    String[] result = new String[numRows];
    for (int i = 0; i < numRows; i++) {
      result[i] = "";
    }

    int row = 0;
    int flip = 1;
    for (int i = 0; i < s.length(); i++) {
      result[row] += s.charAt(i);
      if (numRows == 1) {
        flip = 0;
      } else if (row == numRows - 1) {
        flip = -1;
      } else if (row == 0) {
        flip = 1;
      }
      row += flip;
    }
    return String.join("", result);
  }
  
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> result = new ArrayList<Integer>();

    boolean onlyNumber = true;
    for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == '*' || c == '+' || c == '-') {
            List<Integer> l1 = diffWaysToCompute(input.substring(0, i));
            List<Integer> l2 = diffWaysToCompute(input.substring(i+1, input.length()));
            for (int ll1 : l1) {
                for (int ll2 : l2) {
                   switch (c) {
                        case '*': result.add(ll1 * ll2); break;
                        case '+': result.add(ll1 + ll2); break;
                        case '-': result.add(ll1 - ll2); break;
                    } 
                }
            }
            onlyNumber = false;
        }
    }
    
    if (onlyNumber) {
        result.add(Integer.parseInt(input));
    }

    return result;
  }
}
