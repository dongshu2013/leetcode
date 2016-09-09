package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum40 {	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, 0, new ArrayList<Integer>(), candidates, target);
        return result;
    }

    private void helper(List<List<Integer>> result, int start,
    		List<Integer> path, int[] candidates, int target) {
        if (target == 0) {
        	for (int i = 0; i < path.size(); i++) {
        		System.out.print(path.get(i));
            	System.out.print(" ");
        	}
        	System.out.print("\n");
            result.add(new ArrayList<Integer>(path));
        }

        if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
            	if (i > start && candidates[i] == candidates[i - 1]) continue;
                if (path.size() == 0 || candidates[i] >= path.get(path.size() - 1)) {
                	path.add(candidates[i]);
                	helper(result, i + 1, path, candidates, target - candidates[i]);
                	path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
    	CombinationSum40 c = new CombinationSum40();
    	int[] nums = {1, 1, 2, 5, 6, 7, 10};
    	c.combinationSum(nums, 8);
    }
}
