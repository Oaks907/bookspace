package S901_S1000;

import model.TreeNode;

/**
 * Create by haifei on 22/9/2020 8:29 AM.
 */
public class S968_BinaryTreeCameras {

    public int minCameraCover(TreeNode root) {
        int[] dfs = dfs(root);
        return dfs[1];
    }

    private int[] dfs(TreeNode node) {
        if (null == node) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int[] array = new int[3];
        array[0] = left[2] + right[2] + 1;
        array[1] = Math.min(array[0], Math.min(left[0] + right[1], right[0] + left[1]));
        array[2] = Math.min(array[0], left[1] + right[1]);
        return array;
    }
}
