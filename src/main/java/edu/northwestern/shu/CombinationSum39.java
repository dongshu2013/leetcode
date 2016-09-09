package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), candidates, target);
        return result;
    }

    private void helper(List<List<Integer>> result, 
    		List<Integer> path, int[] candidates, int target) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(path));
        }
        
        if (target > 0) {
            for (int i = 0; i < candidates.length; i++) {
                if (path.size() == 0 || candidates[i] >= path.get(path.size() - 1)) {
                	path.add(candidates[i]);
                	helper(result, path, candidates, target - candidates[i]);
                	path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
    	CombinationSum39 c = new CombinationSum39();
    	int[] nums = {2, 3, 6, 7};
    	c.combinationSum(nums, 7);
    }
}
