package S901_S1000;

import model.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 * Create by haifei on 16/9/2019 8:05 AM.
 */
public class S988_SmallestStringStartingFromLeaf {

  public static String ans = null;

  public String smallestFromLeaf(TreeNode root) {

    ans = "zzzzzzzzzz";

    smallest(root, "");
    return ans;
  }

  private void smallest(TreeNode node, String str) {
    if (null == node) {
      return;
    }

    str = (char) ('a' + node.val) + str;

    if (node.left == null && node.right == null) {
      if (str.compareTo(ans) < 0) {
        ans = str;
      }
      return;
    }

    smallest(node.left, str);
    smallest(node.right, str);
  }

  @Test
  public void test() {

    TreeNode node0 = new TreeNode(0);
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);

    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node3_1 = new TreeNode(3);
    TreeNode node4_1 = new TreeNode(4);

    node0.left = node1;
    node0.right = node2;

    node1.left = node3;
    node1.right = node4;
    node2.left = node3_1;
    node2.right = node4_1;

    String result = smallestFromLeaf(node0);

    Assert.assertEquals("dba", result);
  }

  @Test
  public void test1() {
    TreeNode node0 = new TreeNode(0);
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);

    TreeNode node0_1 = new TreeNode(0);
    TreeNode node1_1 = new TreeNode(1);
    TreeNode node2_1 = new TreeNode(2);

    node2.left = node2_1;
    node2.right = node1;

    node2_1.right = node1_1;

    node1_1.left = node0;

    node1.left = node0_1;

    String result = smallestFromLeaf(node2);

    Assert.assertEquals("abc", result);
  }
}
