package wk.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wk.util.StaticImport.random;

import org.junit.jupiter.api.RepeatedTest;

/**
 * KMP算法
 * 
 * @author wk
 */
public class KMP {

    @RepeatedTest(1000)
    public void test() {
        String source = random().nextLong(Long.MAX_VALUE) + "";
        String dest = random().nextInt(100) + "";
        assertEquals(source.indexOf(dest), kmpSearch(source, dest));
    }

    public static int kmpSearch(String source, String dest) {
        int[] pmt = partMatchTable(dest);
        for (int i = 0, j = 0; i < source.length(); i++) {
            while (j > 0 && source.charAt(i) != dest.charAt(j)) {
                j = pmt[j - 1];
            }
            if (source.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] partMatchTable(String dest) {
        int[] pmt = new int[dest.length()];
        pmt[0] = 0; // 第一个字符的匹配值为0
        // j:前缀指针，也代表相同的字符数    i:后缀指针，也代表当前循环的字符序列长度
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当前一个子串发现前后缀有相同时(j>0),而后续字符不同
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                /*
                 * 获取前一个子串(即 dest.subString(0,i))的匹配值，此时j指向前缀相同个数的
                 * 后一个字符，即跳过了j-1个字符的对比过程
                 */
                j = pmt[j - 1];
            }
            // 后缀字符与前缀字符相同，前缀指针+1，后缀指针在下次循环时+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            pmt[i] = j;
        }
        return pmt;
    }
}
