package wk.datastructure.tree;

/**
 * 节点抽象
 * 
 * @author wk
 */
class Node<T extends Node<T>> {
    Integer element;
    T left;
    T right;

    public Node() {}

    public Node(int element) {
        this.element = element;
    }

    public Node(int element, T left, T right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public int element() {
        return element;
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }
}
