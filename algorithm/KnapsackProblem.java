package wk.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 动态规划思想    0-1背包问题
 * 假设山洞里共有a,b,c,d,e五件宝物，它们的重量分别是2,2,6,5,4，它们的价值分别是6,3,5,4,6，
 * 现有承重为10的背包, 怎么装背包，才可以能带走最多的财富。
 * 
 * @author wk
 */
public class KnapsackProblem {

    @Test
    public void test() {
        int bagCapacity = 10;
        Item[] items = new Item[5];
        items[0] = new Item(2, 6);
        items[1] = new Item(2, 3);
        items[2] = new Item(6, 5);
        items[3] = new Item(5, 4);
        items[4] = new Item(4, 6);
        assertEquals(15, dynamic(bagCapacity, items));
    }

    public static int dynamic(int bagCapacity, Item[] items) {
        // 动态规划表
        int[][] dynamicTable = new int[items.length][bagCapacity];

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            for (int j = 0; j < bagCapacity; j++) {
                // 如果重量大于容量，则取上一行的值
                if (item.weight > j) {
                    if (i > 0) {
                        dynamicTable[i][j] = dynamicTable[i - 1][j];
                    } else {
                        dynamicTable[i][j] = 0;
                    }
                } else {
                    if (i > 0) {
                        // 当前物品价值+前面物品在背包容量为 当前背包容量-物品重量 时的最大装载价值
                        int v = item.value + dynamicTable[i - 1][j - item.weight];
                        dynamicTable[i][j] = Math.max(dynamicTable[i - 1][j], v);
                    } else {
                        dynamicTable[i][j] = item.value;
                    }
                }
            }
        }
        return dynamicTable[items.length - 1][bagCapacity - 1];
    }

    private static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
