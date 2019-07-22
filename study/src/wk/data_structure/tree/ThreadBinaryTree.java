package wk.data_structure.tree;

/**
 * 线索二叉树
 * 
 * 定义：将二叉树的空白子节点的指针利用起来指向其特定遍历方式的前驱和后驱。 规律：二叉树中null指针的数量为二叉树节点的数量+1，即叶子节点的数量最多为二叉树节点数量/2+1
 * 
 * @author wk
 * 
 */
public class ThreadBinaryTree<T> {
    private Node<T> root;

    private Node<T> pre; // 前驱

    public static <T> ThreadBinaryTree<T> newInstance(BinaryTree<T> binaryTree) {
        ThreadBinaryTree<T> tree = new ThreadBinaryTree<>();
        tree.root = new Node<>(binaryTree.root);
        tree.threadNode();
        return tree;
    }

    public void threadNode() {
        threadNode(root);
    }

    private void threadNode(Node<T> node) {
        if (node == null) {
            return;
        }
        threadNode(node.left);
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        threadNode(node.right);
    }

    public String travel() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = root;
        while (temp != null) {
            while (temp.leftType == 0) {
                temp = temp.left;
            }
            sb.append(temp + ", ");
            while (temp.rightType == 1) {
                temp = temp.right;
                sb.append(temp + ", ");
            }
            temp = temp.right;
        }
        return sb.toString();
    }

    private static class Node<T> {

        public Node(BinaryTree.Node<T> node) {
            this.element = node.element;
            if (node.left != null) {
                this.left = new Node<T>(node.left);
            }
            if (node.right != null) {
                this.right = new Node<T>(node.right);
            }
        }

        T element;
        Node<T> left;
        Node<T> right;
        int leftType;
        int rightType;

        @Override
        public String toString() {
            return element.toString();
        }
    }

}
