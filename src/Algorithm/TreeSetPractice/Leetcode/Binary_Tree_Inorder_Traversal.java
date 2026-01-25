package Algorithm.TreeSetPractice.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * InOrder：Left -> Root -> Right
 */
public class Binary_Tree_Inorder_Traversal {
    public static void main(String[] args) {

        // Expected : 90 -> 100 -> 80
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(90);
        root.right = new TreeNode(80);

        System.out.println(inorderTraversal(root));
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private static void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        // Left
        inorder(node.left, result);

        // Root
        result.add(node.val);

        // Right
        inorder(node.right, result);
    }
}