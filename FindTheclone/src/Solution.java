import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    // Complete the findShortest function below.

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        Node graph = new Node(graphNodes, ids);
        int min = Integer.MAX_VALUE;
        for (int x = 0; x < graphFrom.length; x++) {
            graph.add_edge(graphFrom[x], graphTo[x]);
        }
        Stack<Integer> stack = graph.find_nodes(val);
        if (stack.size() < 2) {
            return -1;
        }
        while (!stack.isEmpty()) {
            min = Integer.min(min, graph.bfs(stack.pop()));
            if (min == 1) {
                break;
            }
        }
        return min;
        //run bfs on adj matrix -- always returns shortest path from A to b
    }

    //method to find shortest path

    public static void main(String[] args) {
        int vertex = 5;
        int[] edge_to = {2, 3, 4, 5};
        int[] edge_from = {1, 2, 2, 3};
        long[] id = {1, 2, 3, 1, 3};
        int col = 5;
        System.out.println(findShortest(vertex, edge_from, edge_to, id, col));
    }

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    //class node
    static class Node {
        int V;
        int[][] adjMatrix;
        long[] color;

        Node(int Vertex, long[] ids) {
            this.V = Vertex;
            this.adjMatrix = new int[Vertex][Vertex];
            this.color = ids;
            for (int i = 0; i < Vertex; i++) {
                for (int j = 0; j < Vertex; j++) {
                    adjMatrix[i][j] = 0;
                }
            }
        }

        void add_edge(int source, int destination) {
            this.adjMatrix[source - 1][destination - 1] = 1;
            this.adjMatrix[destination - 1][source - 1] = 1;
        }

        Stack<Integer> find_nodes(int i) {
            Stack<Integer> nodes = new Stack<>();
            for (int x = 0; x < color.length; x++) {
                if (color[x] == i) {
                    nodes.push(x);
                }
            }
            return nodes;
        }

        //bfs on adjencency matrix
        int bfs(int start) {
            boolean[] visited = new boolean[this.V];
            Queue<Integer> q = new LinkedList<>();
            int distance = 0;
            q.add(start);
            visited[start] = true;
            while (q.size() != 0) {
                int element = q.remove();
                int i;
                distance++;
                for (i = 0; i < adjMatrix.length; i++) {
                    if (adjMatrix[element][i] == 1 && visited[i] == false) {
                        q.add(i);
                        visited[i] = true;
                        if (color[start] == color[i]) {
                            return distance;
                        }
                    }
                }
            }
            return -1;

        }

    }
    //for more questions just reach out to me!
}


