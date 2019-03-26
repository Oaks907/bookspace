package S601_700;

import com.sun.tools.corba.se.idl.StringGen;
import model.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Statement;
import java.util.Stack;

/**
 * Create by haifei on 23/3/2019 7:57 PM.
 * <p>
 * https://leetcode.com/problems/construct-string-from-binary-tree/
 */
public class S606_ConstructStringfromBinaryTree {

  public String tree2str(TreeNode t) {

    StringBuilder builder = new StringBuilder();

    if (t == null) {
      return builder.toString();
    }

    helper(t, builder);

    return builder.toString();
  }

  public void helper(TreeNode root, StringBuilder sb) {

    sb.append(root.val);

    if (root.left != null) {
      sb.append("(");
      helper(root.left, sb);
      sb.append(")");
    }

    if (root.right != null) {
      if (root.left == null) {
        sb.append("()");
      }
      sb.append("(");
      helper(root.right, sb);
      sb.append(")");
    }
  }

  @Test
  public void test1() {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;

    final S606_ConstructStringfromBinaryTree stringfromBinaryTree =
      new S606_ConstructStringfromBinaryTree();

    System.out.println(stringfromBinaryTree.tree2str(node1));

    Assert.assertEquals("1(2(4))(3)", stringfromBinaryTree.tree2str(node1));
  }
}
