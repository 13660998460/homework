package wk.algorithm;

import static org.junit.Assert.assertTrue;
import static wk.util.StaticImport.random;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 稀疏数组
 * 
 * @author wk
 *
 */
public class SparseArray {

    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int COUNT = 10;

    @Test
    public void test() {
        int[][] source = new int[ROWS][COLUMNS];
        for (int i = 0; i < COUNT; i++) {
            source[random().nextInt(ROWS)][random().nextInt(COLUMNS)] = random().nextInt(ROWS * COLUMNS);
        }
        int[][] sparseArray = encode(source);
        int[][] origin = decode(sparseArray);
        assertTrue(Arrays.deepEquals(source, origin));
    }

    public static int[][] encode(int[][] source) {
        int nonNull = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                if (source[i][j] != 0) {
                    nonNull++;
                }
            }
        }

        if (nonNull * 3 > source.length * source[0].length) {
            System.err.println("WARNING : This array is not suitable for encoding as a sparse array");
        }

        int[][] sparseArray = new int[nonNull + 1][3];
        sparseArray[0][0] = source.length;
        sparseArray[0][1] = source[0].length;
        sparseArray[0][2] = nonNull;

        int count = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                if (source[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = source[i][j];
                }
            }
        }
        return sparseArray;
    }

    public static int[][] decode(int[][] sparseArray) {
        int[][] origin = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i <= sparseArray[0][2]; i++) {
            origin[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return origin;
    }
}
