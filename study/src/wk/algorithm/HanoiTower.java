package wk.algorithm;

import static wk.util.StaticImport.reverseOrder;
import static wk.util.StaticImport.sysout;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 分治算法         汉诺塔游戏 
 * 有三根相邻的柱子，标号为A,B,C，A柱子上从下到上按金字塔状叠放着n个不同大小的圆盘，把所有盘子
 * 移动到柱子B上，每次只能移动一个盘子并且不能出现大盘子在小盘子上方的情况。
 * 
 * @author wk
 */
public class HanoiTower {

    private final int plateCount;

    private Column a;
    private Column b;
    private Column c;

    private long moveCount;

    public HanoiTower(int count) {
        this.plateCount = count;
        int[] a = IntStream.rangeClosed(1, count).boxed().sorted(reverseOrder()).mapToInt(Integer::intValue).toArray();
        this.a = new Column('A', a, a.length);
        this.b = new Column('B', new int[count], 0);
        this.c = new Column('C', new int[count], 0);
    }

    public void hanoiTower() {
        sysout(a.toString() + b.toString() + c.toString());

        int num = this.plateCount;
        hanoiTower(num, a, b, c);

        sysout(a.toString() + b.toString() + c.toString());
        sysout("一共移动" + moveCount + "次");
    }

    private void hanoiTower(int num, Column a, Column b, Column c) {
        if (num == 1) {
            // 只有一个盘时，将a移动到b即可,此时移动的是前两个参数
            move(a, b);
        } else {
            // 多个盘时，采用分治思想，将多个盘看成两个盘：最下面一个和上面所有。首先将上面所有盘从a移动到c
            hanoiTower(num - 1, a, c, b);
            // 然后将最后一个盘从a移动到b
            move(a, b);
            // 最后将上面所有盘从c移动到b
            hanoiTower(num - 1, c, b, a);
        }
    }

    private void move(Column from, Column to) {
        to.push(from.pop());
        moveCount++;
    }

    /**
     * 柱子   栈结构
     */
    private static class Column {
        char name;
        int[] column;
        int index;

        public Column(char name, int[] column, int index) {
            this.name = name;
            this.column = column;
            this.index = index;
        }

        public void push(int plate) {
            if (index >= 1 && plate > column[index - 1]) {
                throw new AssertionError("大盘不能在小盘上");
            }
            column[index++] = plate;
        }

        public int pop() {
            int plate = column[--index];
            column[index] = 0;
            return plate;
        }

        @Override
        public String toString() {
            return name + ":" + Arrays.toString(column);
        }
    }

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower(32);
        hanoiTower.hanoiTower();
    }
}
