package contest.contest_169;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 29/12/2019 11:05 AM.
 */
public class AllElementsinTwoBinarySearchTrees {


  public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> result = new ArrayList<>();

    recursion(root1, list1);
    recursion(root2, list2);

    while (!list1.isEmpty() && !list2.isEmpty()) {
      Integer val1 = list1.get(0);
      Integer val2 = list2.get(0);

      if (val1 > val2) {
        result.add(val2);
        list2.remove(0);
      } else if (val1 == val2) {
        result.add(val1);
        result.add(val2);
        list1.remove(0);
        list2.remove(0);
      } else {
        result.add(val1);
        list1.remove(0);
      }
    }

    if (!list1.isEmpty()) {
      result.addAll(list1);
    }
    if (!list2.isEmpty()) {
      result.addAll(list2);
    }

    return result;
  }

  public void recursion(TreeNode root, List<Integer> list) {
    if (null == root) {
      return;
    }
    recursion(root.left, list);
    list.add(root.val);
    recursion(root.right, list);
  }

  @Test
  public void test() {
    TreeNode node11 = new TreeNode(1);
    TreeNode node12 = new TreeNode(2);
    TreeNode node14 = new TreeNode(4);

    node12.left = node11;
    node12.right = node14;

    TreeNode node20 = new TreeNode(0);
    TreeNode node21 = new TreeNode(1);
    TreeNode node23 = new TreeNode(3);

    node21.left = node20;
    node21.right = node23;

    System.out.println(getAllElements(node12, node21));
  }
}
