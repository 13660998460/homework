package wk.algorithm.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wk.datastructure.graph.MatrixGraph;
import wk.datastructure.graph.MatrixGraph.GraphTraversal;

public class GraphTest {

    private static final char[] VERTEX_NAME = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    private static MatrixGraph graph;

    /**         
     *      A   5   B
     *   7    2   3   9
     * C        G        D
     *   8    4   6   4 
     *      E   5   F
     */

    @BeforeAll
    public static void initGraph() {
        graph = new MatrixGraph(VERTEX_NAME.length, VERTEX_NAME);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 3, 9);
        graph.addEdge(3, 5, 4);
        graph.addEdge(5, 4, 5);
        graph.addEdge(4, 2, 8);
        graph.addEdge(2, 0, 7);
        graph.addEdge(6, 0, 2);
        graph.addEdge(6, 1, 3);
        graph.addEdge(6, 4, 4);
        graph.addEdge(6, 5, 6);
    }

    @Test
    @DisplayName("图遍历")
    public void graphTraversal() {
        GraphTraversal traversal = new GraphTraversal(graph);
        traversal.dfs();
        traversal.bfs();
    }

    @Test()
    @DisplayName("最小生成树")
    public void minimumSpanningTree() {
        assertEquals(MST.prim(graph), MST.kruskal(graph));
    }

    @Test
    @DisplayName("最短路径")
    public void shortestPath() {
        int[][] floyd = Floyd.floyd(graph);
        Dijkstra dijkstra = new Dijkstra(graph);
        for (int i = 0; i < graph.getVertexNum(); i++) {
            int[] dis = dijkstra.dijkstra(i);
            for (int j = 0; j < graph.getVertexNum(); j++) {
                assertEquals(floyd[i][j], dis[j]);
            }
        }
    }

    @Test
    @DisplayName("马踏棋盘")
    public void horseChessboard() {
        HorseChessboard horseChessboard = new HorseChessboard(8, 8);
        horseChessboard.traversal(4, 4);
    }
}
