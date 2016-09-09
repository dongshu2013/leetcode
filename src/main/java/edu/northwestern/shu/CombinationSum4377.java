package edu.northwestern.shu;

import java.util.Arrays;

public class CombinationSum4377 {
	private int[] dp;
	
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i + nums[j] <= target) {
                    dp[i + nums[j]] += dp[i];
                }
            }
        }

        return dp[target];
    }

	public int combinationSum42(int[] nums, int target) {
		dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return helper(nums, target);
	}

	public int helper(int[] nums, int target) {
		if (dp[target] >= 0) {
			return dp[target];
		}

		dp[target] = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target - nums[i] >= 0) {
				dp[target] += helper(nums, target - nums[i]);
			}
		}

		return dp[target];
	}

	public int combinationSum43(int[] nums, int target) {
		int count = 0;
		if (target <= 0) {
			count += (target == 0 ? 1 : 0);
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (target >= nums[i]) {
					count += combinationSum4(nums, target - nums[i]);
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		CombinationSum4377 c = new CombinationSum4377();
		int[] nums = {1,2,3};
		System.out.println(c.combinationSum4(nums, 32));
	}
}
