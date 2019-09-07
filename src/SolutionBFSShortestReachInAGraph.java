import java.util.*;

public class SolutionBFSShortestReachInAGraph {

    class Node {
        int id;
        boolean visited;
        int distance;
        ArrayList<Node> children = new ArrayList<>();
    }

    class Graph {
        Node startingNode;
        Node[] nodes;
        int[] distances;

        Node getNodeWithId(int id) {
            return nodes[id-1];
        }
    }

    private Graph graph;

    private void doBFS(Node root) {
        LinkedList<Node> nodeList = new LinkedList<>();

        root.visited = true;

        nodeList.addLast(root);

        while(!nodeList.isEmpty()) {
            Node r = nodeList.removeFirst();

            for(Node n : r.children) {
                if(!n.visited) {
                    n.visited = true;
                    n.distance = r.distance + 6;
                    graph.distances[n.id-1] = r.distance + 6;

                    nodeList.addLast(n);
                } else {
                    if(n.distance > (r.distance + 6)) {
                        graph.distances[n.id-1] = r.distance + 6;
                        n.distance = r.distance + 6;
                    }
                }
            }
        }
    }

    private void solve(Scanner in) {
        int q = in.nextInt();

        for (int i = 0; i < q; i++) {

            graph = new Graph();

            int n = in.nextInt();
            int m = in.nextInt();

            graph.nodes = new Node[n];
            graph.distances = new int[n];

            for (int k = 1; k <= n; k++) {
                Node node = new Node();
                node.id = k;

                graph.nodes[k-1] = node;
                graph.distances[k-1] = -1;
            }

            for(int j = 0; j < m; j++) {
                int u = in.nextInt();
                int v = in.nextInt();

                Node nodeU = graph.getNodeWithId(u);
                Node nodeV = graph.getNodeWithId(v);

                nodeU.children.add(nodeV);
                nodeV.children.add(nodeU);
            }

            graph.startingNode = graph.getNodeWithId(in.nextInt());

            doBFS(graph.startingNode);

            for(int k = 0; k < graph.nodes.length; k++) {

                if(k != graph.startingNode.id-1) {
                    System.out.print("" + graph.distances[k]+ " ");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        SolutionBFSShortestReachInAGraph solution = new SolutionBFSShortestReachInAGraph();
        solution.solve(in);

        in.close();
    }
}