package edu.northwestern.shu;

public class WiggleMaxLength376 {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int count = 1;
        int flag = 0;
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] == nums[i - 1]) continue;
            if (flag == 0) {
                flag = nums[i] - nums[i - 1];
                count += (flag == 0 ? 0 : 1);
                continue;
            }
            if ((nums[i] < nums[i - 1]) ^ (flag < 0)) {
                flag = -flag;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
    	WiggleMaxLength376 w = new WiggleMaxLength376();
    	int[] nums = {1,7,4,9,2,5};
    	System.out.println(w.wiggleMaxLength(nums));
    }
}
