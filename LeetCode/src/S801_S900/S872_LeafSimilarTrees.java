package S801_S900;

import model.TreeNode;
import sun.nio.cs.FastCharsetProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 5/12/2018 8:56 AM.
 */
public class S872_LeafSimilarTrees {

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    list1 = getLeafNode(root1, list1);
    list2 = getLeafNode(root2, list2);

    int index = 0;
    while (list1.size() == list2.size() && index < list1.size()) {
      if (list1.get(index) != list2.get(index)) {
        return false;
      }
      index++;
    }

    return list1.size() == list2.size();
  }

  private List<Integer> getLeafNode(TreeNode root, List<Integer> list) {
    if (null == root) {
      return list;
    }
    if (null == root.left && null == root.right) {
      list.add(root.val);
    }
    getLeafNode(root.left, list);
    getLeafNode(root.right, list);

    return list;
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;

    final S872_LeafSimilarTrees similarTrees = new S872_LeafSimilarTrees();

    System.out.println(similarTrees.leafSimilar(node1, node1));
  }
}
