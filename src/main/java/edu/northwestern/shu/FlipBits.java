package edu.northwestern.shu;

public class FlipBits {
  public int flipBits(int[] bits) {
    int[] newBits = new int[bits.length];
    int all = 0;
    for (int i = 0; i < bits.length; i++) {
      newBits[i] = (bits[i] == 0) ? 1 : -1;
      all += newBits[i];
    }
    int x = (bits.length - all) / 2;

    int maxSum = newBits[0];
    int sum = newBits[0];
    
    /***
    int left = 0;
    int maxLeft = left;
    int maxRight = maxLeft;
    ***/

    for (int i = 1; i < newBits.length; i++) {
      if (sum < 0) {
        sum = newBits[i];
        //left = i;
      } else {
        sum += newBits[i];
      }

      if (sum > maxSum) {
        maxSum = sum;
        //maxLeft = left;
        //maxRight = i;
      }
    }

    /***
    System.out.println("New bits: " + Arrays.toString(newBits));
    System.out.println("X: " + x);
    System.out.println("Left: " + maxLeft);
    System.out.println("Right: " + maxRight);
    System.out.println("maxSum: " + maxSum);
    System.out.println("Count: " + (x + maxSum));
    ***/
    return x + maxSum;
  }

  public static void main(String[] args) {
    int[] bits = {1, 0, 1, 0, 0, 1, 0, 1};
    new FlipBits().flipBits(bits);
  }
}
