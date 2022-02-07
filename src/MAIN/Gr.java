package MAIN;


import java.util.*;

public class Gr {

    static class Pair implements Comparable<Pair> {
        int vertex;
        String path;
        int cost;

        public Pair(int vertex ,String path, int cost) {
            this.vertex = vertex;
            this.path = path;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }


    static class Edge {
        int src;
        int destination;
        int cost;
        boolean isVisited;
        public Edge(int src, int vertex, int cost) {
            this.src = src;
            this.destination = vertex;
            this.cost = cost;
            isVisited = false;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", destination=" + destination +
                    ", cost=" + cost +
                    ", isVisited=" + isVisited +
                    '}';
        }
    }


    public static ArrayList<Edge>[] buildGraph() {
        int edgeCnt = 5;
        ArrayList<Edge> graphs[] = new ArrayList[edgeCnt];

        for (int i = 0; i< edgeCnt; i++) {
            graphs[i] = new ArrayList<>();
        }

        graphs[0].add(new Edge(0, 1, 10));
        graphs[0].add(new Edge(0, 3, 40));

        graphs[1].add(new Edge(1, 2, 10));
        graphs[1].add(new Edge(1, 0, 10));

        graphs[2].add(new Edge(2, 1, 10));
        graphs[2].add(new Edge(2, 3, 10));
        graphs[2].add(new Edge(2, 4, 10));

        graphs[3].add(new Edge(3, 0, 40));
        graphs[3].add(new Edge(3, 2, 2));
        graphs[3].add(new Edge(3, 4, 10));

        graphs[4].add(new Edge(4, 3, 3));
        graphs[4].add(new Edge(4, 2, 8));

        return graphs;
    }

    // bfs algorithm from src to the destination.


    // find the shortest path from the src vertex to the every other vertex
    // with the priority queue simple and easy to follow.
    // remove mark work add.
    public static void dijkstrasAlgorithm(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        queue.add(new Pair(src, src + "", 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (!visited[p.vertex]) {
                visited[p.vertex] = true;
                System.out.println("from src 0 -> " + p.vertex +
                        " with the cost of " + p.cost + " via path " + p.path);
                for (Edge e: graph[p.vertex]) {
                    if (!visited[e.destination]) {
                        queue.add(new Pair(e.destination, p.path + e.destination,
                                p.cost + e.cost));

                    }
                }
            }
        }
    }


    public static boolean isAllNodesVisited(ArrayList<Edge>[] graph, boolean[] visited) {
        int cnt = graph.length;
        for (int i = 0; i< cnt; i++) {
            if (!visited[i]) { return false; }
        }
        return true;
    }


    public static void allPaths(ArrayList<Edge>[] graph, int src,
                                boolean[] visited, String psf) {


        if (src == 4) {
            System.out.println(psf);
            return;
        }

        visited[src] = true;

        for (Edge e: graph[src]) {
            if (!visited[e.destination]) {
                allPaths(graph, e.destination, visited, psf + e.destination);
            }
        }
        visited[src] = false;
    }


    public static void bfsAlgorithm(ArrayList<Edge>[] graph,  int src,
                                    boolean[] visited, String psf) {
        // Queue is the interface and not the actual class in the java,
        // so we use the array deque class

        Queue<Pair> queue = new ArrayDeque<>();
        // add the src to the queue.
        if (src == 4) {
            System.out.println(psf);
            return;
        }

        queue.add(new Pair(src, src + "", 0));

        while (!queue.isEmpty()) {
            // rmwa.
            Pair p = queue.remove();
            if (!visited[p.vertex]) {
                visited[p.vertex] = true;
                for (Edge e: graph[p.vertex]) {
                    if (!visited[e.destination]) {
                        queue.add(new Pair(e.destination, p.path + e.destination
                                + "", p.cost + e.cost));
                    }
                }
            }

        }
        System.out.println("No path exist");
    }



    public static void hamiltonionPath(ArrayList<Edge>[] graph, int src, HashSet<Integer> visited,
                                       String psf, ArrayList<String> cache) {
        if (visited.size() == graph.length - 1) {
            cache.add(psf);
            return;
        }
        visited.add(src);
        for (Edge e: graph[src]) {
            if (!visited.contains(e.destination)) {
                hamiltonionPath(graph, e.destination, visited,
                        psf + e.destination, cache);
            }
        }
        visited.remove(src);
    }


    public static void main(String[] args) {
        ArrayList<Edge>[] graph = buildGraph();
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<String> cache = new ArrayList<>();
        hamiltonionPath(graph, 0, visited, 0 + "", cache);
        StringBuilder builder = new StringBuilder();
        HashSet<String> directionsSet = new HashSet<>();
        for (String path: cache) {
            System.out.println(path);
            HashSet<Integer> set = new HashSet<>();

            for (int i = 0; i< path.length() - 1; i++) {
                int vq = Integer.parseInt(path.charAt(i) + "");
                int vs = Integer.parseInt(path.charAt(i + 1) + "");
                System.out.println(vs);
            }
            String result = builder.toString();
            directionsSet.add(result);
        }
        
        for (String s: directionsSet) {
            System.out.println(s);
        }
    }

}





