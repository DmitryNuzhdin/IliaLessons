package lesson3;

import java.util.LinkedList;

public class RemoveRecursion {
    public static void main(String[] args) {
        TreeNode example = new TreeNode(new TreeNode(new TreeNode(3), new TreeNode(4), 1), new TreeNode(2), 0);
        invertBfs(example);
        System.out.println(example);
    }

    static void invertDfs(TreeNode root) {
        LinkedList<TreeNode> tasks = new LinkedList<>();
        tasks.addLast(root);
        while (!tasks.isEmpty()) {
            TreeNode currentTask = tasks.removeLast();
            System.out.println("Working on node" + currentTask.value);
            if (currentTask.left != null) tasks.addLast(currentTask.left);
            if (currentTask.right != null)tasks.addLast(currentTask.right);

            TreeNode tmp = currentTask.left;
            currentTask.left = currentTask.right;
            currentTask.right = tmp;
        }
    }

    static void invertBfs(TreeNode root) {
        LinkedList<TreeNode> tasks = new LinkedList<>();
        tasks.addLast(root);
        while (!tasks.isEmpty()) {
            TreeNode currentTask = tasks.removeLast();
            System.out.println("Working on node" + currentTask.value);
            if (currentTask.left != null) tasks.addFirst(currentTask.left);
            if (currentTask.right != null)tasks.addFirst(currentTask.right);

            TreeNode tmp = currentTask.left;
            currentTask.left = currentTask.right;
            currentTask.right = tmp;
        }
    }
}

class TreeNode{
    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode(TreeNode left, TreeNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode(int value) {
        this.value = value;
    }
}
