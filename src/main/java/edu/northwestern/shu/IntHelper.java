package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntHelper {
  public int hammingWeight(int n) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      count += (n & 1);
      n >>= 1;
    }
    return count;
  }

  public boolean isPowerOfTwo(int n) {
    return (n > 0 && hammingWeight(n) == 1);
  }

  public boolean isPowerOfThree2(int n) {
    return n > 0 && Math.pow(3, Math.round(Math.log(n) / Math.log(3))) == n;
  }

  public boolean isPowerOfThree(int n) {
    if (n <= 0) {
      return false;
    }
    while (n > 1) {
      if ((n % 3) != 0) {
        return false;
      }
      n /= 3;
    }
    return true;
  }

  public boolean isPowerOfFour(int num) {
    if (num <= 0)
      return false;
    int count = 0;
    int index = 0;
    for (int i = 0; i < 32; i++) {
      if ((num & 1) == 1) {
        count += 1;
        index = i;
      }
      num >>= 1;
    }
    return (count == 1 && index % 2 == 0);
  }

  public boolean isPowerOfFour2(int num) {
    return (num > 0 && (num & 0xAAAAAAAA) == 0 && (num & num - 1) == 0);
  }

  public boolean isHappy(int n) {
    Set<Integer> set = new HashSet<Integer>();
    while (!set.contains(n) && n != 1) {
      set.add(n);
      int sum = 0;
      while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
      }
      n = sum;
    }
    return n == 1;
  }

  public int reverseBits(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
      result <<= 1;
      result += (n & 1);
      n >>= 1;
    }
    return result;
  }

  public boolean isUgly(int num) {
    if (num <= 0)
      return false;
    while (num % 2 == 0)
      num /= 2;
    while (num % 3 == 0)
      num /= 3;
    while (num % 5 == 0)
      num /= 5;
    return num == 1;
  }

  public int climbStairs(int n) {
    int p1 = 1;
    int p2 = 1;
    int p = p2;
    for (int i = 2; i <= n; i++) {
      p = p1 + p2;
      p1 = p2;
      p2 = p;
    }
    return p;
  }

  public int trailingZeroes(int n) {
    int count = 0;
    while (n > 0) {
      n = n / 5;
      count += n;
    }
    return count;
  }

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    long x1 = Math.max(A, E);
    long y1 = Math.min(D, H);
    long x2 = Math.min(C, G);
    long y2 = Math.max(B, F);

    long w = x2 - x1;
    long h = y1 - y2;
    int overlap = 0;
    if (w > 0 && h > 0) {
      overlap = (int) (w * h);
    }
    return (C - A) * (D - B) + (G - E) * (H - F) - overlap;
  }

  public int integerBreak(int n) {
    if (n <= 3) {
      return n - 1;
    }

    if (n % 3 == 2) {
      return (int) Math.pow(3, n / 3) * 2;
    } else if (n % 3 == 1) {
      return (int) Math.pow(3, n / 3 - 1) * 4;
    }
    return (int) Math.pow(3, n / 3);
  }

  public int integerBreak2(int n) {
    int[] dict = new int[n + 1];
    dict[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j < i; j++) {
        dict[i] = Math.max(Math.max(j, dict[j]) * (i - j), dict[i]);
      }
    }
    return dict[n];
  }

  public int bulbSwitch(int n) {
    return (int) Math.sqrt(n);
  }

  public int countNumbersWithUniqueDigits(int n) {
    int count = 1;
    for (int i = 1; i <= n; i++) {
      int c = 9;
      for (int j = 1; j < i; j++) {
        c *= (9 - j + 1);
      }
      count += c;
    }
    return count;
  }

  public int rangeBitwiseAnd(int m, int n) {
    int i = 0;
    while (m != n) {
      m >>= 1;
      n >>= 1;
      i++;
    }
    return m << i;
  }

  public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();

    String[] dict = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX",
        "V", "IV", "I"};
    int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    for (int i = 0; i < 13; i++) {
      if (num >= vals[i]) {
        int count = num / vals[i];
        for (int j = 0; j < count; j++) {
          sb.append(dict[i]);
          num -= vals[i];
        }
      }
    }

    return sb.toString();
  }

  public List<Integer> grayCode(int n) {
    List<Integer> l = new ArrayList<Integer>();
    l.add(0);
    for (int i = 0; i < n; i++) {
        int mask = (1 << i);
        for (int j = l.size() - 1; j >= 0; j--) {
            l.add(l.get(j) | mask);
        }
    }
    return l;
  }

  public int uniquePaths(int m, int n) {
    int[] d = new int[m];
    d[0] = 1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (j > 0) {
                d[j] += d[j-1];
            }
        }
    }
    return d[m-1];
  }
  
  public int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int val = 1;
    
    for (int i = 0; i < n/2; i++) {
        for (int col = i; col < n - i - 1; col++) {
            matrix[i][col] = val++;
        }
        
        for (int row = i; row < n - i - 1; row++) {
            matrix[row][n - i - 1] = val++;
        }
        
        for (int col = n - i - 1; col > i; col--) {
            matrix[n-i-1][col] = val++;
        }
        
        for (int row = n - i - 1; row > i; row--) {
            matrix[row][i] = val++;
        }
    }
    
    if (n % 2 != 0) {
        matrix[n/2][n/2] = val;
    }

    return matrix;
  }

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    processCombine(k, 1, n, new ArrayList<Integer>(), result);
    return result;
  }

  private void processCombine(int k, int s, int n, List<Integer> path, List<List<Integer>> result) {
    if (k == 0) {
        result.add(new ArrayList<Integer>(path));
        return;
    }

    for (int i = s; i <= n; i++) {
        path.add(i);
        processCombine(k-1, i+1, n, path, result);
        path.remove(path.size() - 1);
    }
  }
  
  public int numSquares(int n) {
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
        dp[i] = Integer.MAX_VALUE;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 1; i + j * j <= n; j++) {
            dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
        }
    }

    return dp[n];
  }

  public static void main(String[] args) {
    IntHelper ih = new IntHelper();
    for (int i : ih.grayCode(2)) {
      System.out.println(i);
    }
  }
}
