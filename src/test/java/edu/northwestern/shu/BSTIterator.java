package edu.northwestern.shu;

import java.util.Stack;
import edu.northwestern.shu.helper.TreeNode;

public class BSTIterator {
  private Stack<TreeNode> s = new Stack<TreeNode>();
  public BSTIterator(TreeNode root) {
      TreeNode t = root;
      while(t != null) {
          s.push(t);
          t = t.left;
      }
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
      return s.size() > 0;
  }

  /** @return the next smallest number */
  public int next() {
      TreeNode t = s.pop();
      int val = t.val;
      t = t.right;
      while (t != null) {
          s.push(t);
          t = t.left;
      }
      return val;
  }
}