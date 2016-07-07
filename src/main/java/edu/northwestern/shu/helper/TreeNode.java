package edu.northwestern.shu.helper;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right; 

  public TreeNode(int value) {
    this.val = value;
  }

  public static TreeNode createTree(String tree) {
    String[] nodes = tree.split(" ");
    TreeNode[] tnodes = new TreeNode[nodes.length];
    tnodes[0] = new TreeNode(Integer.parseInt(nodes[0]));
    for (int i = 1; i < nodes.length; i++) {
      if (!"#".equals(nodes[i])) {
        TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
        tnodes[i] = node;
        int parent = (i + 1) / 2 - 1;
        if ((i + 1) % 2 == 0) {
          tnodes[parent].left = node;
        } else {
          tnodes[parent].right = node;
        }
      }
    }
    return tnodes[0];
  }

  public static String toString(TreeNode root) {
    if (root == null) {
      return "";
    }
    
    StringBuilder sb = new StringBuilder();

    List<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    queue.add(null);

    while (queue.size() > 1) {
      TreeNode node = queue.remove(0);
      if (node == null) {
        sb.append("\n");
        queue.add(null);
        continue;
      }
 
      sb.append(Integer.toString(node.val) + " ");
      if (node.left != null) {
        queue.add(node.left);
      }

      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    String tree = "1 2 3 # 4 5 # # # # 6 7 8 # #";
    tree = "1 2 2 3 3 3 3 4 4 4 4 4 4 # # 5 5 # #";
    TreeNode root = TreeNode.createTree(tree);
    String result = TreeNode.toString(root);
    System.out.println(result);
  }
}
