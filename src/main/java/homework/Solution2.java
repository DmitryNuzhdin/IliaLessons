package homework;

/**
 * @author Ilia Moskalenko
 */

   /*   Условия задачи

             3
           /  \
          9   20
             /  \
            15   7

  There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.*/


public class Solution2 {
    public static void main(String[] args) {
        /*TreeNode treeNode3left = new TreeNode(1);
        TreeNode treeNode3right = new TreeNode(5);*/
        TreeNode treeNode2right = new TreeNode(7);
        TreeNode treeNode2left = new TreeNode(15);
        TreeNode treeNode1right = new TreeNode(20, treeNode2left, treeNode2right);
        TreeNode treeNode1left = new TreeNode(9);
        TreeNode root = new TreeNode(3, treeNode1left, treeNode1right);
        Solution2 solution2 = new Solution2();

        System.out.println(solution2.sumOfLeftLeaves(root));

    }


    public int sumOfLeftLeaves(TreeNode root){
        int sum = 0;
        if (root == null){
            return sum;
        }
        return sumLeft(root.left) + sumRight(root.right);
    }

    private int sumRight(TreeNode node) {
        if (node == null) return 0;
        return sumLeft(node.left) + sumRight(node.right);
    }

    private int sumLeft(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return node.val;
        return sumLeft(node.left) + sumRight(node.right);
    }

}

class TreeNode{
    int val;
    TreeNode left, right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
