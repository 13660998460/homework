package wk.datastructure.graph;

import static wk.util.StaticImport.sysout;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 邻接矩阵无向图
 * @author wk
 */
public class MatrixGraph {

    /** 顶点数量 */
    private final int vertexNum;
    /** 边的数量*/
    private int edgeNum;
    /** 邻接矩阵 */
    private final int[][] adjacencyMatrix;

    /** 顶点名*/
    private final char[] vertexName;

    /** 边不存在为0*/
    public static final int EDGE_NONE = 0;

    public MatrixGraph(int vertexNum, char[] vertexName) {
        this.vertexNum = vertexNum;
        adjacencyMatrix = new int[vertexNum][vertexNum];
        this.vertexName = Arrays.copyOf(vertexName, vertexNum);
    }

    public void addEdge(int from, int to) {
        this.addEdge(from, to, 1);
    }

    public void addEdge(int from, int to, int weight) {
        if (isEdgeExist(from, to))
            return;
        if (from > vertexNum || to > vertexNum)
            return;
        adjacencyMatrix[from][to] = weight;
        adjacencyMatrix[to][from] = weight;
        edgeNum++;
    }

    public void removeEdge(int from, int to) {
        if (!isEdgeExist(from, to))
            return;
        if (from > vertexNum || to > vertexNum)
            return;
        adjacencyMatrix[from][to] = EDGE_NONE;
        adjacencyMatrix[to][from] = EDGE_NONE;
        edgeNum--;
    }

    private boolean isEdgeExist(int from, int to) {
        return adjacencyMatrix[from][to] > EDGE_NONE;
    }

    public int[][] getMatrix() {
        int[][] copy = new int[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            copy[i] = adjacencyMatrix[i].clone();
        }
        return copy;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    /**
     * 遍历
     */
    public static class GraphTraversal {

        private MatrixGraph graph;
        private boolean[] visited;

        public GraphTraversal(MatrixGraph graph) {
            this.graph = graph;
            visited = new boolean[graph.vertexNum];
        }

        /**
         * deep first search 深度优先遍历
         */
        public void dfs() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < graph.vertexNum; i++) {
                if (!visited[i]) {
                    dfs(i, sb);
                }
            }
            sb.setLength(sb.length() - 1);
            sysout(sb);
            visited = new boolean[graph.vertexNum];
        }

        private void dfs(int i, StringBuilder sb) {
            sb.append(graph.vertexName[i]).append(",");
            visited[i] = true;
            int[] neighbor = graph.adjacencyMatrix[i];
            for (int j = 0; j < graph.vertexNum; j++) {
                if (!visited[j] && neighbor[j] > EDGE_NONE) {
                    dfs(j, sb);
                }
            }
        }

        /**
         * breadth first search 广度优先遍历
         */
        public void bfs() {
            StringBuilder sb = new StringBuilder();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < graph.vertexNum; i++) {
                if (!visited[i]) {
                    list.add(i);
                    visited[i] = true;
                    bfs(list, sb);
                }
            }
            sb.setLength(sb.length() - 1);
            sysout(sb.toString());
            visited = new boolean[graph.vertexNum];
        }

        private void bfs(LinkedList<Integer> list, StringBuilder sb) {
            int i = list.pop();
            sb.append(graph.vertexName[i]).append(",");
            int[] neighbor = graph.adjacencyMatrix[i];
            for (int j = 0; j < neighbor.length; j++) {
                if (!visited[j] && neighbor[j] > EDGE_NONE) {
                    list.add(j);
                    visited[j] = true;
                }
            }
            if (!list.isEmpty()) {
                bfs(list, sb);
            }
        }

    }
}
