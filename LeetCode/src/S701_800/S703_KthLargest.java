package S701_800;

/**
 * Create by haifei on 13/1/2019 12:33 AM.
 */
public class S703_KthLargest {

  class TreeNode {
    int val;
    int rightSubSum;
    TreeNode left;
    TreeNode right;

    TreeNode(int val, int rightSubSum) {
      this.val = val;
      this.rightSubSum = rightSubSum;
    }
  }

  private TreeNode root;
  private int k;

  public S703_KthLargest(int k, int[] nums) {
    this.k = k - 1;
    for (int i : nums) {
      root = insert(root, i);
    }
  }

  public int add(int val) {
    root = insert(root, val);
    return getKthLargest(root, k);
  }


  private TreeNode insert(TreeNode root, int key) {
    if (root == null) {
      return new TreeNode(key, 0);
    }

    if (root.val > key) {
      root.left = insert(root.left, key);
    } else {
      root.rightSubSum++;
      root.right = insert(root.right, key);
    }
    return root;
  }

  private int getKthLargest(TreeNode root, int k) {
    if (root == null) {
      return -1;
    }
    if (root.rightSubSum == k) {
      return root.val;
    }

    if (root.rightSubSum > k) {
      return getKthLargest(root.right, k);
    } else {
      return getKthLargest(root.left, k - root.rightSubSum - 1);
    }
  }

  public static void main(String[] args) {

    int k = 3;
    int[] arr = {4, 5, 8, 2};
    S703_KthLargest kthLargest = new S703_KthLargest(3, arr);
    System.out.println(kthLargest.add(3));
    System.out.println(kthLargest.add(5));
    System.out.println(kthLargest.add(10));
    System.out.println(kthLargest.add(9));
    System.out.println(kthLargest.add(4));
  }
}
