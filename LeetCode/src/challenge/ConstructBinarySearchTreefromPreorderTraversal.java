package challenge;

import model.TreeNode;

/**
 * Create by haifei on 21/4/2020 1:09 AM.
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    public TreeNode bstFromPreorder(int[] preorder) {

        if (null == preorder || preorder.length == 0) {
            return null;
        }

        TreeNode root = null;
        for (int i = 0; i < preorder.length; i++) {
            root = putVal(root, preorder[i]);
        }
        return root;
    }

    public TreeNode putVal(TreeNode node, int val) {

        if (node == null) {
            return new TreeNode(val);
        }

        if (node.val < val) {
            node.right = putVal(node.right, val);
        } else {
            node.left = putVal(node.left, val);
        }

        return node;
    }
}
