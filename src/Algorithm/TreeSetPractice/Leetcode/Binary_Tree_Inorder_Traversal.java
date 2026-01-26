package Algorithm.TreeSetPractice.Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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


    private static List<Integer> inorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // 1. 一直往左走，把所有左節點都壓入 Stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 2. 左邊都走完了，彈出 Stack 頂的節點
            current = stack.pop();

            // 3. 處理當前節點（中序遍歷在這裡輸出）
            result.add(current.val);

            // 4. 轉向右子樹
            current = current.right;
        }
        return result;
    }
}