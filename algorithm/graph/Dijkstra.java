package wk.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

import wk.datastructure.graph.MatrixGraph;

/**
 * 迪杰斯特拉算法 
 * 计算有权图中从某一个顶点到其余各顶点的最短路径
 * 
 * 算法思路：利用图的广度优先遍历，每次遍历计算遍历点的相邻点到原点的距离并保存，如果该距离小于已保存的则更新。
 * 
 * @author wk
 */
public class Dijkstra {

    private final MatrixGraph graph;

    private int[] dis;
    private List<Integer> preVisit;

    public Dijkstra(MatrixGraph graph) {
        this.graph = graph;
        reset();
    }

    private void reset() {
        int vertexNum = graph.getVertexNum();
        this.preVisit = new ArrayList<>(vertexNum);
        this.dis = new int[vertexNum];
    }

    /**
     * @param from 原点
     * @return  原点到各个点的距离，下标为点，值为距离
     */
    public int[] dijkstra(int from) {
        preVisit.add(from);
        for (int i = 0; i < graph.getVertexNum(); i++) {
            int curVisit = preVisit.get(i);
            update(curVisit);
        }
        int[] result = dis;
        reset();
        return result;
    }

    /**
     * @param vertex 更新点
     */
    public void update(int vertex) {
        int i = vertex;
        int[][] matrix = graph.getMatrix();
        int[] neighbors = matrix[i];
        for (int j = 0; j < neighbors.length; j++) {
            // 遍历更新点的邻居节点（除去原点）
            if (neighbors[j] > 0 && j != preVisit.get(0)) {
                // 更新点到原点的距离+更新点到相邻点的距离=相邻点到原点的距离
                int len = dis[i] + neighbors[j];
                // 如果距离小于当前已经记录的距离或当前还未记录，则更新距离
                if (len < dis[j] || dis[j] == 0) {
                    dis[j] = len;
                    // 将j加入准备更新集合
                    if (!preVisit.contains(j)) {
                        preVisit.add(j);
                    }
                }
            }
        }
    }
}
