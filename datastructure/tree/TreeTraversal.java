package wk.datastructure.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 树的遍历与打印
 * 
 * @author wk
 */
public class TreeTraversal {

    public static final String SEPARATOR = ", ";

    public enum TravelMode {
        Pre, Middle, Post, Level;
    }

    public static int[] travel(Node<?> root, TravelMode travelMode) {
        if (root == null)
            return null;
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
            case Level:
                levelTravel(root, sb);
                break;
        }
        sb.setLength(sb.length() - 2);
        return Arrays.stream(sb.toString().split(TreeTraversal.SEPARATOR)).mapToInt(Integer::parseInt).toArray();
    }

    private static void preTravel(Node<?> n, StringBuilder sb) {
        if (n == null)
            return;
        sb.append(n.element + SEPARATOR);
        preTravel(n.left, sb);
        preTravel(n.right, sb);
    }

    private static void middleTravel(Node<?> n, StringBuilder sb) {
        if (n == null)
            return;
        middleTravel(n.left, sb);
        sb.append(n.element + SEPARATOR);
        middleTravel(n.right, sb);
    }

    private static void postTravel(Node<?> n, StringBuilder sb) {
        if (n == null)
            return;
        postTravel(n.left, sb);
        postTravel(n.right, sb);
        sb.append(n.element + SEPARATOR);
    }

    private static void levelTravel(Node<?> n, StringBuilder sb) {
        // 当前数据结构不能满足需求时，考虑将该结构转换为另一种结构或者借助另一种结构
        LinkedList<Node<?>> queue = new LinkedList<>();
        queue.add(n);
        while (queue.size() > 0) {
            Node<?> head = queue.pop();
            sb.append(head.element + SEPARATOR);
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
        }
    }

}
