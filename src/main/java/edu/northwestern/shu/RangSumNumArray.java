package edu.northwestern.shu;

public class RangSumNumArray {
  private int[] table;
  public RangSumNumArray(int[] nums) {
    table = new int[nums.length];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      table[i] = sum;
    }
  }

  public int sumRange(int i, int j) {
    if (i == 0) {
      return table[j];
    }
    return table[j] - table[i - 1];
  }
}
