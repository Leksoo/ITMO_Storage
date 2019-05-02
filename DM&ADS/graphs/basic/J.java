package AlgoLab7;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class J {

    static class Edge implements Comparable{
        int v;
        int u;
        int w;

        public Edge(int v, int u, int w) {
            this.v = v;
            this.u = u;
            this.w = w;
        }


        @Override
        public int compareTo(Object o) {
            Edge edge = (Edge)o;
            return Integer.compare(w, edge.w);
        }
    }

    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] revGraph;
    ArrayList<Edge> edges = new ArrayList<>();
    int n;
    int m;
    boolean[] visited;
    int[] colors;
    ArrayList<Integer> sorted = new ArrayList<>();
    DSU dsu;

    boolean hasCycle = false;

    void input() throws IOException {
        FastScanner scanner = new FastScanner();
        n = scanner.nextInt();
        m = scanner.nextInt();
        graph = new ArrayList[n];
        revGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int v = scanner.nextInt() - 1;
            int u = scanner.nextInt() - 1;
            int w = scanner.nextInt();
            Edge edge = new Edge(v,u,w);
            edges.add(edge);
        }
        visited = new boolean[n];
        colors = new int[n];
    }


    void solve(PrintWriter out) throws IOException {
        input();
        dsu = new DSU(n);
        Collections.sort(edges);
        long res = 0;
        for (Edge edge:edges){
            if(dsu.get(edge.u)!=dsu.get(edge.v)){
                res+=edge.w;
                dsu.union(dsu.get(edge.u),dsu.get(edge.v));
            }
        }
        System.out.print(res);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("topsort.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new J().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                if (line.equals("")) {
                    return "";
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }

    public static class DSU {
        int[] counts;
        int[] mins;
        int[] maxs;
        int[] p;
        int[] rank;
        int n;

        DSU(int n) {
            this.n = n;
            p = new int[n + 2];
            rank = new int[n + 2];
            counts = new int[n + 1];
            mins = new int[n + 1];
            maxs = new int[n + 1];
            for (int i = 0; i < n; i++) {
                init(i);
                counts[i] = 1;
                mins[i] = i;
                maxs[i] = i;
            }
        }

        void init(int el) {
            p[el] = el;
            rank[el] = 0;
        }

        int get(int el) {
            if (p[el] == el) {
                return el;
            }
            return p[el] = get(p[el]);
        }

        void union(int x, int y) {
            x = get(x);
            y = get(y);

            if (x != y) {
                counts[x] = counts[x] + counts[y];
                counts[y] = counts[x];
                if (mins[x] < mins[y]) {
                    mins[y] = mins[x];
                } else {
                    mins[x] = mins[y];
                }
                if (maxs[x] > maxs[y]) {
                    maxs[y] = maxs[x];
                } else {
                    maxs[x] = maxs[y];
                }


                if (rank[x] < rank[y]) {
                    int t = x;
                    x = y;
                    y = t;
                }

                p[y] = x;
                rank[x] = Math.max(rank[x], rank[y] + 1);
            }
        }


    }
}