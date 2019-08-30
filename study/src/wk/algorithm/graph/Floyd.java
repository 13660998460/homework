package wk.algorithm.graph;

import wk.datastructure.graph.MatrixGraph;

/**
 * 弗洛伊德算法
 * 计算有权图中每个顶点到其余各顶点的最短路径
 * 
 * 算法思路：遍历图中所有顶点作为中间点，若图中任意两点到该点的距离和小于当前已记录的该两点间距离，就更新该距离
 * 
 * @author wk
 */
public class Floyd {

    public static int[][] floyd(MatrixGraph graph) {
        int[][] matrix = graph.getMatrix();
        for (int i = 0; i < graph.getVertexNum(); i++) {
            // 当前以i为中间点，遍历矩阵，j为行，k为列
            for (int j = 0; j < graph.getVertexNum(); j++) {
                for (int k = 0; k < graph.getVertexNum(); k++) {
                    if (j == k || matrix[i][j] == MatrixGraph.EDGE_NONE || matrix[i][k] == MatrixGraph.EDGE_NONE) {
                        continue;
                    }
                    // 计算顶点i到任意两点的长度，如果小于两点间长度，则为两点间长度赋值
                    int len = matrix[i][j] + matrix[i][k];
                    if (matrix[j][k] == MatrixGraph.EDGE_NONE || matrix[j][k] > len) {
                        matrix[j][k] = len;
                    }
                }
            }
        }
        return matrix;
    }

}
