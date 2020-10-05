package homework;

/**
 * @author Ilia Moskalenko
 */

/*
Условия задачи
Invert a binary tree.

        Example:

        Input:

        4
        /   \
        2     7
        / \   / \
        1   3 6   9

        Output:

        4
        /   \
        7     2
        / \   / \
        9   6 3   1
*/

public class Solution3 {
    public static void main(String[] args) {
        TreeNode treeNode2left1 = new TreeNode(1);
        TreeNode treeNode2right1 = new TreeNode(3);
        TreeNode treeNode2left2 = new TreeNode(6);
        TreeNode treeNode2right2 = new TreeNode(9);
        TreeNode treeNode1left = new TreeNode(2, treeNode2left1, treeNode2right1);
        TreeNode treeNode1right = new TreeNode(7, treeNode2left2, treeNode2right2);
        TreeNode treeNode = new TreeNode(4, treeNode1left, treeNode1right);

        Solution3 solution3 = new Solution3();
        solution3.invertTree(treeNode);
    }

    public TreeNode invertTree(TreeNode root){
        if (root == null) return null;

        if (root.left != null && root.right!= null){
            invert(root);
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    public TreeNode invert(TreeNode node){
        TreeNode buff = new TreeNode();
        buff.left = node.left;
        node.left = node.right;
        node.right = buff.left;
        return node;
    }

}





 /*class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}*/
