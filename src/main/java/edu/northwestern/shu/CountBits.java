package edu.northwestern.shu;

import java.util.Arrays;

public class CountBits {
  public static int count(int num) {
    int c = 0;
    while (num != 0) {
        c += num & 0x0001;
        num = num >> 1;
    }
    return c;
  }

  public static int[] countBits(int num) {
    int[] bitsNum = new int[num+1];
    for (int i = num; i >= 0; i--) {
        bitsNum[i] = count(i);
    }
    return bitsNum;
  }

  public static int[] countBits2(int num) {
    int[] bitsNum = new int[num+1];
    for (int i = 1; i <= num; i++) {
      if ((i & 0x0001) == 1) {
        bitsNum[i] = bitsNum[i >> 1] + 1;
      } else {
        bitsNum[i] = bitsNum[i >> 1];
      }
    }
    return bitsNum;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(CountBits.countBits2(5)));
  }
}
