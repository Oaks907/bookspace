package S1301_S1400;

import model.TreeNode;

/**
 * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 * Create by haifei on 14/3/2020 2:09 PM.
 */
public class S1379_FindaCorrespondingNodeofaBinaryTreeinaCloneofThatTree {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return getTargetCopyMethod(original, cloned, target);
    }

    public TreeNode getTargetCopyMethod(TreeNode origin, TreeNode cloned, TreeNode target) {
        if (origin == null || cloned == null) {
            return null;
        }
        if (origin == target) {
            return cloned;
        }
        TreeNode left = getTargetCopyMethod(origin.left, cloned.left, target);
        TreeNode right = getTargetCopyMethod(origin.right, cloned.right, target);

        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }

        return null;
    }
}
