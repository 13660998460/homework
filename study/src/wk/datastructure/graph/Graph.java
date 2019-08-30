package wk.datastructure.graph;

import static wk.util.StaticImport.sysout;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接表有向图
 * @author wk
 */
public class Graph {

    private List<Vertex> vertexes = new ArrayList<>();

    /**
     * 顶点
     */
    static class Vertex {
        int data;
        /** 相邻顶点*/
        List<Vertex> linkedVertexes = new ArrayList<>();

        public Vertex(int data) {
            this.data = data;
        }

        public void linkVertex(Vertex to) {
            if (!linkedVertexes.contains(to)) {
                linkedVertexes.add(to);
            }
        }

        public void unlinkVertex(Vertex to) {
            if (linkedVertexes.contains(to)) {
                linkedVertexes.remove(to);
            }
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + data;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex)obj;
            if (data != other.data)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Vertex [data=" + data + "]";
        }
    }

    public void addEdge(int from, int to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : vertexes) {
            if (from == v.data) {
                fromV = v;
            } else if (to == v.data) {
                toV = v;
            }
            if (fromV != null && toV != null)
                break;
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            vertexes.add(fromV);
        }
        if (toV == null) {
            toV = new Vertex(to);
            vertexes.add(toV);
        }
        fromV.linkVertex(toV);
    }

    public void removeEdge(int from, int to) {
        Vertex fromV = vertexes.get(from);
        Vertex toV = vertexes.get(to);
        if (fromV == null || toV == null)
            return;
        fromV.unlinkVertex(toV);
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        vertexes.forEach(v -> {
            sb.append(v.data).append("-");
            v.linkedVertexes.forEach(x -> {
                sb.append(x.data).append("-");
            });
            sb.setLength(sb.length() - 1);
            sb.append("\r\n");
        });
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(5, 4);
        g.addEdge(3, 4);
        g.removeEdge(1, 2);
        sysout(g);
    }
}
