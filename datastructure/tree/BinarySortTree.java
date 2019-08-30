package wk.datastructure.tree;

import static org.junit.Assert.assertTrue;
import static wk.util.StaticImport.random;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import wk.datastructure.tree.TreeTraversal.TravelMode;

/**
 * 二叉排序树
 * 
 * 任意节点的左子树上所有节点的值均小于它的根节点的值 任意节点的右子树上所有节点的值均大于它的根节点的值； 没有键值相等的节点。
 * 
 * @author wk
 */
public class BinarySortTree {
    protected Node root;

    public static Node createBinarySortTree(int[] source) {
        LinkedList<Node> list = new LinkedList<>();
        for (int i = 0; i < source.length; i++) {
            list.add(new Node(source[i], null, null));
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node min = list.pollFirst();
            Node minddle = list.pollFirst();
            Node max = list.pollFirst();
            minddle.left = min;
            minddle.right = max;
            list.add(minddle);
        }
        return list.pollFirst();
    }

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
    }

    public Node delete(int i) {
        Node parent = root;
        Node target = null;
        while (parent != null) {
            if (parent.left != null) {
                if (parent.left.element == i) {
                    target = parent.left;
                    break;
                }
                if (i < parent.left.element) {
                    parent = parent.left;
                }
            }

            if (parent.right != null) {
                if (parent.right.element == i) {
                    target = parent.right;
                    break;
                }
                if (i > parent.right.element) {
                    parent = parent.right;
                }
            }

        }
        if (target == null) {
            return null;
        }
        delete(parent, target);
        return target;
    }

    public void delete(Node parent, Node target) {
        if (target.left == null && target.right == null) // 情况1：删除的节点是叶子节点
        {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.left == target) {
                parent.left = null;
            }
            if (parent.right == target) {
                parent.right = null;
            }
        } else if (target.left != null && target.right != null) // 情况2：删除的节点有两个子节点
        {
            Node rightMin = target.right;
            if (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            delete(rightMin.element);
            target.element = rightMin.element;
        } else {
            if (target.left != null) {
                if (parent != null) {
                    if (parent.left == target) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                } else {
                    root = target.left;
                }
            } else {
                if (parent != null) {
                    if (parent.left == target) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                } else {
                    root = target.right;
                }
            }
        }
    }

    public Node search(int i) {
        Node result = null;
        Node temp = root;
        while (temp != null) {
            if (temp.element == i) {
                result = temp;
                break;
            } else {
                if (i < temp.element) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
        }
        return result;
    }

    static class Node extends wk.datastructure.tree.Node<Node> implements Comparable<Node> {

        public Node(int element, Node left, Node right) {
            super(element, left, right);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.element, o.element);
        }

    }

    @Test
    public void test() {
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < 100; i++) {
            tree.add(random().nextInt());
        }
        assertTrue(isBinarySortTree(tree.root));
    }

    public static boolean isBinarySortTree(wk.datastructure.tree.Node<?> root) {
        if (root == null)
            return false;
        int[] elements = TreeTraversal.travel(root, TravelMode.Middle);
        for (int i = 0; i < elements.length - 2; i++) {
            if (elements[i] > elements[i + 1]) {
                return false;
            }
            i++;
        }
        return true;
    }
}
