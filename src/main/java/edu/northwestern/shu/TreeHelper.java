package edu.northwestern.shu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import edu.northwestern.shu.helper.TreeNode;

public class TreeHelper {
  public boolean isHeightBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    return Math.abs(depth(root.left) - depth(root.right)) < 2
        && isHeightBalanced(root.left) && isHeightBalanced(root.right);
  }

  private int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(depth(root.left), depth(root.right));
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }

    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
  }

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<String>();
    List<Integer> path = new ArrayList<Integer>();
    if (root != null) {
      traverse(root, paths, path);
    }
    return paths;
  }

  private void traverse(TreeNode root, List<String> paths, List<Integer> path) {
    path.add(root.val);
    if (root.left == null && root.right == null) {
      paths.add(listToString(path));
      path.remove(path.size() - 1);
      return;
    }
    if (root.left != null) {
      traverse(root.left, paths, path);
    }
    if (root.right != null) {
      traverse(root.right, paths, path);
    }
    path.remove(path.size() - 1);
  }

  private String listToString(List<Integer> path) {
    StringBuilder sb = new StringBuilder();
    sb.append(path.get(0));
    for (int i = 1; i < path.size(); i++) {
      sb.append("->");
      sb.append(path.get(i));
    }
    return sb.toString();
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == q) {
      return true;
    }
    return (p != null && q != null) && (p.val == q.val)
        && (isSameTree(p.left, q.left)) && (isSameTree(p.right, q.right));
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root.val == p.val || root.val == q.val
        || (root.val > p.val ^ root.val > q.val)) {
      return root;
    }
    if (root.val > p.val && root.val > q.val) {
      return lowestCommonAncestor(root.left, p, q);
    }
    return lowestCommonAncestor(root.right, p, q);
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> result = new LinkedList<List<Integer>>();
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    List<Integer> row = new ArrayList<Integer>();
    queue.offer(root);
    queue.offer(null);
    while (queue.size() > 1) {
      TreeNode ele = queue.remove();
      if (ele == null) {
        result.add(new ArrayList<Integer>(row));
        row.clear();
        queue.offer(null);
      } else {
        row.add(ele.val);
        if (ele.left != null)
          queue.offer(ele.left);
        if (ele.right != null)
          queue.offer(ele.right);
      }
    }
    result.add(row);
    Collections.reverse(result);
    return result;
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null)
      return true;
    return compare(root.left, root.right);
  }

  public boolean compare(TreeNode left, TreeNode right) {
    if (!(left == null ^ right != null)) {
      return false;
    }

    if (left == null && right == null) {
      return true;
    }

    return (left.val == right.val) && compare(left.left, right.right)
        && compare(left.right, right.left);
  }

  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;
    if (sum == root.val && root.left == null && root.right == null)
      return true;
    return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum
        - root.val));
  }

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> l = new ArrayList<Integer>();
    preorderTraverse(root, l);
    return l;
  }

  public void preorderTraverse(TreeNode root, List<Integer> l) {
    if (root == null) {
      return;
    }

    l.add(root.val);
    if (root.left != null) {
      preorderTraverse(root.left, l);
    }
    if (root.right != null) {
      preorderTraverse(root.right, l);
    }
  }

  public List<Integer> preorderTraversalNonRecursive(TreeNode root) {
    List<Integer> l = new ArrayList<Integer>();
    Stack<TreeNode> s = new Stack<TreeNode>();
    s.push(root);
    while (!s.empty()) {
      TreeNode n = s.pop();
      if (n != null) {
        l.add(n.val);
        s.push(n.right);
        s.push(n.left);
      }
    }
    return l;
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> l = new ArrayList<Integer>();
    inorderTraverse(root, l);
    return l;
  }

  public void inorderTraverse(TreeNode n, List<Integer> l) {
    if (n == null) {
      return;
    }

    if (n.left != null) {
      inorderTraverse(n.left, l);
    }

    l.add(n.val);

    if (n.right != null) {
      inorderTraverse(n.right, l);
    }
  }

  public List<Integer> inorderTraversalNonRecursive(TreeNode root) {
    List<Integer> l = new ArrayList<Integer>();
    Stack<TreeNode> s = new Stack<TreeNode>();
    TreeNode n = root;
    while (n != null) {
      s.push(n);
      n = n.left;
    }

    while (!s.empty()) {
      n = s.pop();
      if (n == null) {
        continue;
      }

      l.add(n.val);
      if (n.right != null) {
        n = n.right;
        s.push(n);
        while (n != null) {
          s.push(n.left);
          n = n.left;
        }
      }
    }

    return l;
  }

  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<String>();
    process("", n, 0, 0, result);
    return result;
  }

  public void process(String s, int n, int l, int r, List<String> result) {
    if (l == n && r == n) {
      result.add(s);
      return;
    }

    if (l > r) {
      if (l < n) {
        process(s + "(", n, l + 1, r, result);
      }
      process(s + ")", n, l, r + 1, result);
    }

    if (l == r) {
      process(s + "(", n, l + 1, r, result);
    }
  }

  public int rob(TreeNode root) {
    int[] result = dfs(root);
    return Math.max(result[0], result[1]);
  }

  private int[] dfs(TreeNode root) {
    if (root == null) {
      return new int[2];
    }

    int[] left = dfs(root.left);
    int[] right = dfs(root.right);

    int[] val = new int[2];
    val[0] = root.val + left[1] + right[1];
    val[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    return val;
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.add(root);
    q.add(null);

    while (q.size() > 1) {
      TreeNode e = q.poll();
      if (q.peek() == null) {
        result.add(e.val);
      }

      if (e == null) {
        q.add(e);
      } else {
        if (e.left != null) {
          q.add(e.left);
        }
        if (e.right != null) {
          q.add(e.right);
        }
      }
    }

    return result;
  }

  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    }

    if (root.left == null) {
      return 1 + minDepth(root.right);
    }

    if (root.right == null) {
      return 1 + minDepth(root.left);
    }

    return Math.min(1 + minDepth(root.left), 1 + minDepth(root.right));
  }

  public int sumNumbers(TreeNode root) {
    if (root != null) {
      return sumNumbersDfs(0, 0, root);
    }
    return 0;
  }

  private int sumNumbersDfs(int total, int pathSum, TreeNode root) {
    int val = pathSum * 10 + root.val;
    if (root.left == null && root.right == null) {
      total += val;
      return total;
    }

    if (root.left != null) {
      total = sumNumbersDfs(total, val, root.left);
    }

    if (root.right != null) {
      total = sumNumbersDfs(total, val, root.right);
    }

    return total;
  }

  public static void main(String[] args) {
    String tree = "1 2 2 3 3 3 3 4 4 4 4 4 4 # # 5 5 # #";
    TreeNode root = TreeNode.createTree(tree);
    TreeHelper th = new TreeHelper();
    boolean result = th.isHeightBalanced(root);
    System.out.println(result);
  }
}
