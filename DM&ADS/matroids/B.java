package DiskrLab9;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class B {

    static class Edge implements Comparable {
        int v;
        int u;
        int num;
        long w;

        public Edge(int num, int v, int u, long w) {
            this.v = v;
            this.u = u;
            this.w = w;
            this.num = num;
        }


        @Override
        public int compareTo(Object o) {
            Edge edge = (Edge) o;
            return Long.compare(w, edge.w);
        }
    }

    ArrayList<Integer>[] graph;
    ArrayList<Edge> edges = new ArrayList<>();
    int n;
    int m;
    long s;
    DSU dsu;


    void input() throws IOException {
        FastScanner scanner = new FastScanner("destroy.in");
        n = scanner.nextInt();
        m = scanner.nextInt();
        s = Long.parseLong(scanner.next());
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int v = scanner.nextInt() - 1;
            int u = scanner.nextInt() - 1;
            long w = -Long.parseLong(scanner.next());
            Edge edge = new Edge(i, v, u, w);
            edges.add(edge);
        }
    }


    void solve(PrintWriter out) throws IOException {
        input();
        dsu = new DSU(n);
        Collections.sort(edges);
        ArrayList<Edge> newEdges = new ArrayList<>();
        for (Edge edge : edges) {

            if (dsu.get(edge.u) != dsu.get(edge.v)) {
                dsu.union(dsu.get(edge.u), dsu.get(edge.v));
            } else {
                newEdges.add(edge);
            }
        }
        newEdges.forEach(edge -> edge.w *= -1);
        Collections.sort(newEdges);
        long sum = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (Edge edge : newEdges) {

            if (sum <= s - edge.w) {
                sum += edge.w;
                ans.add(edge.num);
            } else {
                break;
            }
        }
        out.println(ans.size());
        ans.stream().sorted(Integer::compare).forEach(val -> out.print(val + 1 + " "));

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("destroy.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new B().run();

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