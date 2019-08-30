package wk.datastructure.tree;

import static org.junit.Assert.assertArrayEquals;
import static wk.util.StaticImport.random;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import wk.datastructure.tree.TreeTraversal.TravelMode;

/**
 * 二叉树的顺序存储
 * 
 * 定义：将完全二叉树中节点逐层存入数组，在遍历该数组时依然可以按照二叉树的前序、中序、后序三种方式遍历。
 * 规律：设二叉树任意父节点下标为x，左子节点下标为y，右子节点下标为z，则有y=2x+1,z=2x+2
 * 原因：将二叉树的任意连续两行从左往右数，上行每数一个节点，下行需要数两个节点
 * 
 * @author wk
 *
 */
public class ArrBinaryTree {

    public static int[] preTravel(int[] source) {
        if (source == null)
            return null;
        StringBuilder sb = new StringBuilder();
        preTravel(source, 0, sb);
        sb.setLength(sb.length() - 2);
        return Arrays.stream(sb.toString().split(TreeTraversal.SEPARATOR)).mapToInt(Integer::parseInt).toArray();
    }

    private static void preTravel(int[] source, int index, StringBuilder sb) {
        if (index >= source.length) {
            return;
        }
        sb.append(source[index] + ", ");
        preTravel(source, index * 2 + 1, sb);
        preTravel(source, index * 2 + 2, sb);
    }

    /**
     * 测试数组的前序遍历和树的前序遍历结果是否一致
     */
    @Test
    public void test() {
        final int count = 1000;
        int[] arr = new int[count];
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < count; i++) {
            int element = random().nextInt(count);
            binaryTree.add(element);
            arr[i] = element;
        }
        int[] treeTravel = TreeTraversal.travel(binaryTree.root, TravelMode.Pre);
        int[] arrTravel = preTravel(arr);
        assertArrayEquals(treeTravel, arrTravel);
    }
}
