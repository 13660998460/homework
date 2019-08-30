package wk.algorithm.graph;

import static wk.util.StaticImport.random;
import static wk.util.StaticImport.sysout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import wk.datastructure.graph.MatrixGraph;

/**
 * Minimum Spanning Tree   最小生成树算法
 *          
 * @author wk
 */
public class MST {

    /**
     *  Prim算法   
     */
    public static int prim(MatrixGraph graph) {
        int weight = 0;
        int[][] matrix = graph.getMatrix();
        int vertexNum = graph.getVertexNum();
        boolean[] visited = new boolean[vertexNum];
        // 第一个访问的节点
        visited[random().nextInt(vertexNum)] = true;
        // 每循环一次生成一条边
        for (int n = 1; n < vertexNum; n++) {
            int x = 0, y = 0, min = Integer.MAX_VALUE;
            // 遍历所有节点，选取已经访问过的节点
            for (int i = 0; i < vertexNum; i++) {
                if (visited[i]) {
                    // 遍历该节点的相邻节点，获取最小权重
                    for (int j = 0; j < vertexNum; j++) {
                        if (!visited[j] && matrix[i][j] > 0 && matrix[i][j] < min) {
                            min = matrix[i][j];
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            sysout(x + "->" + y + ",weight:" + min);
            weight += min;
            visited[y] = true;
        }
        return weight;
    }

    /**
     *  kruskal算法   
     */
    public static int kruskal(MatrixGraph graph) {
        int[][] matrix = graph.getMatrix();
        int vertexNum = graph.getVertexNum();
        // 获取所有边
        Edge[] edges = new Edge[graph.getEdgeNum()];
        int index = 0;
        for (int i = 0; i < vertexNum; i++) {
            for (int j = i + 1; j < vertexNum; j++) {
                if (matrix[i][j] != MatrixGraph.EDGE_NONE) {
                    edges[index++] = new Edge(i, j, matrix[i][j]);
                }
            }
        }
        Arrays.sort(edges);

        List<List<Edge>> list = new ArrayList<List<Edge>>();

        for (Edge edge : edges) {
            if (isCircle(list, edge)) {
                continue;
            } else {
                put(list, edge);
            }
        }
        List<Edge> collect = list.stream().flatMap(List::stream).collect(Collectors.toList());
        sysout(collect);
        return collect.stream().mapToInt(e -> e.weight).sum();
    }

    /**
     * 判断加入某条边后是否构成回路
     * @param list 当前生成的多个子树
     * @param edge 要加入的边
     */
    private static boolean isCircle(List<List<Edge>> list, Edge edge) {
        for (List<Edge> edges : list) {
            Set<Integer> vertexes = new HashSet<Integer>();
            edges.forEach(e -> {
                vertexes.add(e.from);
                vertexes.add(e.to);
            });
            if (vertexes.contains(edge.from) && vertexes.contains(edge.to)) {
                return true;
            }
        }
        return false;
    }

    private static void put(List<List<Edge>> list, Edge edge) {
        List<Edge> l1 = null;
        List<Edge> l2 = null;
        for (List<Edge> edges : list) {
            Set<Integer> vertexes = new HashSet<Integer>();
            edges.forEach(e -> {
                vertexes.add(e.from);
                vertexes.add(e.to);
            });
            if (vertexes.contains(edge.from) || vertexes.contains(edge.to)) {
                if (l1 == null) {
                    l1 = edges;
                } else {
                    l2 = edges;
                }
            }
        }
        if (l1 == null && l2 == null) {
            List<Edge> l = new ArrayList<MST.Edge>();
            l.add(edge);
            list.add(l);
        } else if (l1 != null && l2 == null) {
            l1.add(edge);
        } else if (l1 != null && l2 != null) {
            list.remove(l2);
            l1.addAll(l2);
            l1.add(edge);
        }
    }

    /**
     * 边
     */
    public static class Edge implements Comparable<Edge> {
        public Edge() {}

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        int from;
        int to;
        int weight;

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return from + "->" + to + ",weight:" + weight;
        }
    }
}
