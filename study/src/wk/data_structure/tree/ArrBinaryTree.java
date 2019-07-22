package wk.data_structure.tree;

import static wk.util.StaticImport.sysout;

/**
 * 二叉树的顺序存储
 * 
 * 定义：将完全二叉树中节点逐层存入数组，在遍历该数组时依然可以按照二叉树的前序、中序、后序三种方式遍历。 规律：设二叉树任意父节点下标为x，左子节点下标为y，右子节点下标为z，则有y=2x+1,z=2x+2
 * 原因：将二叉树的任意连续两行从左往右数，上行每数一个节点，下行需要数两个节点
 * 
 * @author wk
 *
 */
public class ArrBinaryTree {
    Object[] arr;

    public ArrBinaryTree(Object[] arr) {
        this.arr = arr;
    }

    public void preTravel() {
        if (arr == null)
            return;
        StringBuilder sb = new StringBuilder();
        preTravel(0, sb);
        sb.setLength(sb.length() - 2);
        sysout(sb);
    }

    private void preTravel(int index, StringBuilder sb) {
        if (index >= arr.length) {
            return;
        }
        sb.append(arr[index] + ", ");
        preTravel(index * 2 + 1, sb);
        preTravel(index * 2 + 2, sb);
    }

    public static void main(String[] args) {
        ArrBinaryTree tree = new ArrBinaryTree(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        tree.preTravel();
    }
}
