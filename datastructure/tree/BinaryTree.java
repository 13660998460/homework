package wk.datastructure.tree;

import static org.junit.Assert.assertArrayEquals;
import static wk.util.StaticImport.random;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import wk.datastructure.tree.TreeTraversal.TravelMode;

/**
 * 二叉树
 * 
 * @author wk
 *
 */
@SuppressWarnings("all")
public class BinaryTree {

    Node root;

    /**
     * 平行添加
     */
    public void add(int i) {
        Node node = new Node<>(i);
        if (root == null) {
            root = node;
        } else {
            Node lastParent = findLastParent();
            if (lastParent.left == null) {
                lastParent.left = node;
            } else if (lastParent.right == null) {
                lastParent.right = node;
            }
        }
    }

    public Node findLastParent() {
        Node target;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            target = queue.pop();
            if (target.left == null || target.right == null)
                return target;
            if (target.left != null) {
                queue.add(target.left);
            }
            if (target.right != null) {
                queue.add(target.right);
            }
        }
        return null;
    }

    public void remove(int i) // 因节点无规律，直接删除整棵子树
    {
        if (i == root.element)
            root = null;
        remove(root, i);
    }

    private void remove(Node node, int i) {
        if (node == null)
            return;
        if (node.left != null && i == node.left.element) {
            node.left = null;
            return;
        }
        if (node.right != null && i == node.right.element) {
            node.right = null;
            return;
        }
        remove(node.left, i);
        remove(node.right, i);
    }

    /**
     * 测试add方法
     */
    @Test
    public void test() {
        final int count = 1000;
        int[] record = new int[count];
        for (int i = 0; i < count; i++) {
            int element = random().nextInt(count);
            this.add(element);
            record[i] = element;
        }
        assertArrayEquals(record, TreeTraversal.travel(root, TravelMode.Level));
    }
}
