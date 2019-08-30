package wk.datastructure.tree;

import static org.junit.Assert.assertTrue;
import static wk.util.StaticImport.random;

import org.junit.jupiter.api.RepeatedTest;

/**
 * 平衡二叉树
 * 
 * 任意节点的两个子树的高度最大差别为1
 * 
 * @author wk
 */
public class AVLTree extends BinarySortTree {

    @Override
    public void add(int i) {
        Node node = new Node(i, null, null);
        if (root == null) {
            root = node;
        } else {
            add(root, node);
        }
    }

    private void add(Node parent, Node child) {
        if (child.element < parent.element) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                add(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                add(parent.right, child);
            }
        }

        adjust(parent);
    }

    private void adjust(Node parent) {
        int balance = height(parent.left) - height(parent.right);
        if (balance > 1) { // 需要右旋
            if (parent.left != null && height(parent.left.right) > height(parent.left.left)) {
                rotateLeft(parent.left);
                rotateRight(parent);
            } else {
                rotateRight(parent);
            }
            return;
        }
        if (balance < -1) { // 需要左旋
            if (parent.right != null && height(parent.right.left) > height(parent.right.right)) {
                rotateRight(parent.right);
                rotateLeft(parent);
            } else {
                rotateLeft(parent);
            }
        }
    }

    /**
     * 求节点的高度
     */
    private int height(Node node) {
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public void rotateLeft(Node parent) {
        Node temp = new Node(parent.element, parent.left, parent.right.left);
        parent.element = parent.right.element;
        parent.left = temp;
        parent.right = parent.right.right;
    }

    public void rotateRight(Node parent) {
        Node temp = new Node(parent.element, parent.left.right, parent.right);
        parent.element = parent.left.element;
        parent.right = temp;
        parent.left = parent.left.left;
    }

    @RepeatedTest(20)
    public void test() {
        for (int i = 0; i < 100; i++) {
            this.add(random().nextInt());
        }
        assertTrue(isBinarySortTree(this.root));
        assertTrue(isAVL(this.root));
    }

    private boolean isAVL(Node root) {
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        } else {
            if (root.left != null) {
                isAVL(root.left);
            }
            if (root.right != null) {
                isAVL(root.right);
            }
        }
        return true;
    }
}
