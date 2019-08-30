package wk.algorithm.graph;

import static wk.util.StaticImport.sysout;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 马踏棋盘
 * 国际象棋的棋盘为8*8的方格棋盘，将“马”放在任意指定的方格中，按照“马”走棋的规则将“马”进行移动。
 * 要求每个方格只能进入一次，最终使得“马”走遍棋盘64个方格。
 * 
 * 解决思路：使用图的深度优先遍历，当遍历无法继续时（不符合规则）步数仍未达到，则返回上一步使用其他可遍历的点继续。
 * 
 * @author wk
 */
public class HorseChessboard {
    /** 棋盘的列数*/
    private final int row;
    /** 棋盘的行数*/
    private final int column;
    /** 是否访问过*/
    private boolean[][] visited;
    /** 是否已完成*/
    private boolean finished;

    public HorseChessboard(int row, int column) {
        this.row = row;
        this.column = column;
        visited = new boolean[row][column];
    }

    /**
     * @param i,j 初始点
     */
    public void traversal(int i, int j) {
        int[][] chessboard = new int[row][column];
        int step = 1;
        traversal(chessboard, i, j, step);
        for (int k = 0; k < row; k++) {
            sysout(Arrays.toString(chessboard[k]));
        }
    }

    private void traversal(int[][] chessboard, int i, int j, int step) {
        visited[i][j] = true;
        chessboard[i][j] = step;
        LinkedList<Point> next = getNext(i, j);
        // 排序，优先选择下一步的下一步少的
        next.sort((a, b) -> Integer.compare(getNext(a.x, a.y).size(), getNext(b.x, b.y).size()));
        // 判断是否可以走下一步
        while (!next.isEmpty()) {
            Point p = next.pop();
            if (!finished && !visited[p.x][p.y]) {
                traversal(chessboard, p.x, p.y, step + 1);
            }
        }

        // 下一步已不可走，根据步数判断是否完成，如果已完成则设置完成标记防止外层方法修改数据,未完成则恢复本次循环所做更改
        if (step < 8 * 8 && !finished) {
            chessboard[i][j] = 0;
            visited[i][j] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 获取下一步可以走的点 
     */
    private LinkedList<Point> getNext(int i, int j) {
        LinkedList<Point> list = new LinkedList<Point>();
        Point p = new Point();
        if ((p.x = i - 2) >= 0 && (p.y = j - 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = i - 1) >= 0 && (p.y = j - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = i + 1) < row && (p.y = j - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = i + 2) < row && (p.y = j - 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = i + 2) < row && (p.y = j + 1) < column) {
            list.add(new Point(p));
        }
        if ((p.x = i + 1) < row && (p.y = j + 2) < column) {
            list.add(new Point(p));
        }
        if ((p.x = i - 1) >= 0 && (p.y = j + 2) < column) {
            list.add(new Point(p));
        }
        if ((p.x = i - 2) >= 0 && (p.y = j + 1) < column) {
            list.add(new Point(p));
        }
        return list;

    }
}
