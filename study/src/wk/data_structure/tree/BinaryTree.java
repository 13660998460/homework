package wk.data_structure.tree;

import static wk.util.StaticImport.sysout;

/**
 * 二叉树
 * 
 * @author wk
 *
 */
public class BinaryTree<T> {

    protected Node<T> root;

    public enum TravelMode {
        Pre, Middle, Post;
    }

    public BinaryTree() {

    }

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 1; i <= 10; i++) {
            tree.add(i);
        }
        tree.travel(TravelMode.Pre);
        tree.remove(7);
        tree.travel(TravelMode.Pre);
    }

    /**
     * 平行添加
     */
    public void add(T t) {
        Node<T> n = new Node<>(t, null, null);
        if (root == null) {
            root = n;
            return;
        } else {
            Node<T> temp = root;
            Node<T> parent = temp;
            boolean isLeft = true;
            while (true) {
                if (temp.left == null) {
                    temp.left = n;
                    return;
                } else if (temp.right == null) {
                    temp.right = n;
                    return;
                } else {
                    if (isLeft) {
                        temp = parent.left;
                        isLeft = false;
                    } else {
                        temp = parent.right;
                        parent = parent.left;
                        isLeft = true;
                    }
                }
            }
        }
    }

    public void remove(T t) // 删除子树
    {
        if (t == null)
            return;
        if (t.equals(root.element))
            root = null;
        remove(root, t);
    }

    private void remove(Node<T> node, T t) {
        if (node == null)
            return;
        if (node.left != null && t.equals(node.left.element)) {
            node.left = null;
            return;
        }
        if (node.right != null && t.equals(node.right.element)) {
            node.right = null;
            return;
        }
        remove(node.left, t);
        remove(node.right, t);
    }

    public void travel(TravelMode travelMode) {
        StringBuilder sb = new StringBuilder();
        switch (travelMode) {
            case Pre:
                preTravel(root, sb);
                break;
            case Middle:
                middleTravel(root, sb);
                break;
            case Post:
                postTravel(root, sb);
                break;
        }
        sb.setLength(sb.length() - 2);
        sysout(sb.toString());
    }

    private void preTravel(Node<T> n, StringBuilder sb) {
        if (n == null)
            return;
        sb.append(n.element + ", ");
        preTravel(n.left, sb);
        preTravel(n.right, sb);
    }

    private void middleTravel(Node<T> n, StringBuilder sb) {
        if (n == null)
            return;
        middleTravel(n.left, sb);
        sb.append(n.element + ", ");
        middleTravel(n.right, sb);
    }

    private void postTravel(Node<T> n, StringBuilder sb) {
        if (n == null)
            return;
        postTravel(n.left, sb);
        postTravel(n.right, sb);
        sb.append(n.element + ", ");
    }

    static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
