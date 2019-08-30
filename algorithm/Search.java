package wk.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wk.util.StaticImport.random;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * 查找算法
 * 
 * @author wk
 *
 */
public class Search {

    @Test
    public void search() {
        int[] source = new int[10];
        for (int i = 0; i < source.length; i++) {
            source[i] = random().nextInt(10000);
        }
        source = Arrays.stream(source).distinct().sorted().toArray();
        for (int i = 0; i < source.length; i++) {
            assertEquals(i, sequenceSearch(source, source[i]));
            assertEquals(i, binarySearch(source, source[i]));
            assertEquals(i, insertValSearch(source, source[i]));
            assertEquals(i, fibSearch(source, source[i]));
        }
    }

    /**
     * 顺序查找
     */
    public static int sequenceSearch(int[] source, int val) {
        for (int i = 0; i < source.length; i++) {
            if (val == source[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    public static int binarySearch(int[] source, int val) {
        return binarySearch(source, 0, source.length - 1, val);
    }

    private static int binarySearch(int[] source, int left, int right, int val) {
        if (left > right)
            return -1;
        int middle = (left + right) >> 1;
        if (val < source[middle]) {
            return binarySearch(source, left, middle - 1, val);
        } else if (val > source[middle]) {
            return binarySearch(source, middle + 1, right, val);
        } else {
            // 获取第一个
            while (--middle > 0 && val == source[middle]) {
            }
            return ++middle;
        }
    }

    /**
     * 插值查找
     */
    public static int insertValSearch(int[] source, int val) {
        return insertValSearch(source, 0, source.length - 1, val);
    }

    private static int insertValSearch(int[] source, int left, int right, int val) {
        if (left > right || val < source[left] || val > source[right])
            return -1;
        if (left == right) {
            if (source[left] == val)
                return left;
            return -1;
        }
        int index = left + (right - left) * (val - source[left]) / (source[right] - source[left]); // index有可能会超出int限制
        if (val < source[index]) {
            return insertValSearch(source, left, index - 1, val);
        } else if (val > source[index]) {
            return insertValSearch(source, index + 1, right, val);
        }
        return index;
    }

    /**
     * 斐波那契查找(黄金分割点)
     */

    private static final int MAX_SIZE = 100;

    private static final int[] fibArr = new int[MAX_SIZE];

    @BeforeAll
    private static void init() {
        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int i = 2; i < fibArr.length; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
    }

    public static int fibSearch(int[] source, int val) {
        int low = 0;
        int high = source.length - 1;
        int k = 0;
        int mid = 0;

        while (high > fibArr[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(source, fibArr[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = source[high];
        }

        while (low <= high) {
            mid = low + fibArr[k - 1] - 1;
            if (val < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (val > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
