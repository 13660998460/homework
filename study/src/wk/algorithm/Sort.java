package wk.algorithm;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

/**
 * 排序算法
 * 
 * @author wk
 */
public class Sort {
    static final int SORT_SIZE = 100000;

    static int[] source = new int[SORT_SIZE];

    @BeforeEach
    private void initSource() {
        Random random = new Random();
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(SORT_SIZE);
        }
    }

    @AfterEach
    private void checkSort() {
        for (int i = 0; i < source.length - 2; i++) {
            int j = i + 1;
            assertTrue(source[i] <= source[j]);
        }
    }

    @RepeatedTest(5)
    public void javaSort() {
        Arrays.sort(source);
    }

    @RepeatedTest(5)
    public void javaParallelSort() {
        Arrays.parallelSort(source);
    }

    @RepeatedTest(5)
    public void selectSortTest() {
        selectSort(source);
    }

    @RepeatedTest(5)
    public void bubbleSortTest() {
        bubbleSort(source);
    }

    @RepeatedTest(5)
    public void insertSortTest() {
        insertSort(source);
    }

    @RepeatedTest(5)
    public void shellSortTest() {
        shellSort(source);
    }

    @RepeatedTest(5)
    public void quickSortTest() {
        quickSort(source);
    }

    @RepeatedTest(5)
    public void mergeSortTest() {
        mergeSort(source);
    }

    @RepeatedTest(5)
    public void radixSortTest() {
        radixSort(source);
    }

    @RepeatedTest(5)
    public void heapSortTest() {
        heapSort(source);
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] source) {
        for (int i = 0; i < source.length - 1; i++) {
            int min = source[i];
            int minIndex = i;
            for (int j = i + 1; j < source.length; j++) {
                if (min > source[j]) {
                    min = source[j];
                    minIndex = j;
                }
            }
            exchange(source, i, minIndex);
        }
    }

    /**
     * 堆排序：属于选择排序
     * 
     * 因叶子节点的数量最多为二叉树节点数量/2+1，则最后一个非叶子节点的下标为 arr.length/2-1 
     * 设完全二叉树任意父节点下标为x，左子节点下标为y，右子节点下标为z，则有y=2x+1,z=2x+2
     */
    public static void heapSort(int[] source) {
        // 将所有非叶子节点都调整为一个大顶堆
        for (int i = source.length / 2 - 1; i >= 0; i--) {
            adjust(source, i, source.length);
        }
        // 每次选择最大值放入数组末尾，并继续调整
        for (int j = source.length - 1; j > 0; j--) {
            exchange(source, 0, j);
            adjust(source, 0, j);
        }
    }

    /**
     * 将一个顺序存储二叉树的某个节点在特定长度内调整为一个大顶堆
     * 
     * @param source
     *            源数组
     * @param parent
     *            要调整的节点
     * @param length
     *            作用长度
     */
    private static void adjust(int[] source, int parent, int length) {
        int child = (parent << 1) + 1; // 左子节点下标
        if (child + 1 < length && source[child] < source[child + 1]) {
            child++; // 如果右节点大于左节点，则将指针指向右节点
        }
        if (child < length && source[child] > source[parent]) {
            exchange(source, parent, child);
            adjust(source, child, length);
        }
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] source) {
        for (int i = 1; i < source.length; i++) {
            int insertVal = source[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < source[insertIndex]) {
                source[insertIndex + 1] = source[insertIndex];
                insertIndex--;
            }
            source[insertIndex + 1] = insertVal;
        }
    }

    /**
     * 希尔排序：基于插入排序
     */
    public static void shellSort(int[] source) {
        for (int i = source.length / 2; i > 0; i /= 2) // i:步长，以i步长分组
        {
            for (int j = i; j < source.length; j++) // j:每组第一个元素之外的所有元素
            {
                int insertVal = source[j];
                int insertIndex = j - i; // j-i:j元素所属组中的前一个元素
                while (insertIndex >= 0 && insertVal < source[insertIndex]) {
                    source[insertIndex + i] = source[insertIndex];
                    insertIndex -= i;
                }
                source[insertIndex += i] = insertVal;
            }
        }
    }

    /**
     * 冒泡排序：属于交换排序
     */
    public static void bubbleSort(int[] source) {
        for (int i = source.length - 1; i > 0; i--) {
            boolean flag = false; // 适用于原数组基本排序好的情况
            for (int j = 0; j < i; j++) {
                if (source[j] > source[j + 1]) {
                    exchange(source, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 快速排序：属于交换排序
     */
    public static void quickSort(int[] source) {
        quickSort(source, 0, source.length - 1);
    }

    private static void quickSort(int[] source, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int middle = source[(left + right) / 2];
        while (l < r)
        // 以middle值为基准，将数组调整为前l个数均小于middle，后r个数均大于middle
        {
            // 在左边找一个比middle大的数，直至到middle为止
            while (source[l] < middle) {
                l++;
            }
            // 在右边找一个比middle小的数，直至到middle为止
            while (source[r] > middle) {
                r--;
            }
            // l以左均小于middle，r以右均大于middle
            if (l == r) {
                break;
            }
            exchange(source, l, r);

            // 判断参与交换的两个数中其中一个是middle的情况，即其中一边均大于或小于middle，而一边还不是
            if (source[l] == middle) // middle被交换到左边，此时r指针位于中间
            {
                r--; // 右边需要多一个位置
            }
            if (source[r] == middle) // middle被交换到右边，此时l指针位于中间
            {
                l++; // 左边需要多一个位置
            }
        }
        quickSort(source, left, l - 1);
        quickSort(source, r + 1, right);
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] source) {
        int[] temp = new int[source.length];
        mergeSort(source, 0, source.length - 1, temp);
        System.arraycopy(temp, 0, source, 0, source.length);
    }

    private static void mergeSort(int[] source, int left, int right, int[] temp) {
        if (left < right) // 分解到left==right
        {
            int middle = (left + right) / 2;
            mergeSort(source, left, middle, temp); // 向左分解
            mergeSort(source, middle + 1, right, temp); // 向右分解
            merge(source, left, middle, right, temp); // 合并
        }
    }

    private static void merge(int[] source, int left, int middle, int right, int[] temp) {
        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (source[i] < source[j]) {
                temp[k++] = source[i++];
            } else {
                temp[k++] = source[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = source[i++];
        }
        while (j <= right) {
            temp[k++] = source[j++];
        }

        System.arraycopy(temp, left, source, left, right - left + 1);
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] source) {
        int max = source[0];
        for (int i = 1; i < source.length; i++) {
            if (max < source[i]) {
                max = source[i];
            }
        }
        int maxLength = String.valueOf(max).length();
        int[][] buckets = new int[10][source.length]; // 10倍空间换1倍性能
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < source.length; j++) {
                int bucketId = source[j] / (int)Math.pow(10, i) % 10;
                int[] bucket = buckets[bucketId];
                bucket[++bucket[0]] = source[j]; // 桶第一位存储长度
            }

            int index = 0;
            for (int j = 0; j < 10; j++) {
                System.arraycopy(buckets[j], 1, source, index, buckets[j][0]);
                index += buckets[j][0];
                buckets[j][0] = 0;
            }
        }
    }

    private static void exchange(int[] source, int i, int j) {
        int tmp = source[i];
        source[i] = source[j];
        source[j] = tmp;
    }

}
