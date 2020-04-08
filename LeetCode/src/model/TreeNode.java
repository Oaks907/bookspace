package model;


/**
 * Create by haifei on 28/11/2018 9:14 AM.
 */
public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int x) {
    val = x;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }
}
