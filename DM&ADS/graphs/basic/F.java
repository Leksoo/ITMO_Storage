package AlgoLab7;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class F {

    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] revGraph;
    int n;
    int m;
    boolean[] visited;
    int[] colors;
    ArrayList<Integer> sorted = new ArrayList<>();

    boolean hasCycle = false;
    int num = 0;

    void sortDfs(int v) {
        visited[v] = true;
        for (int u : graph[v]) {
            if (!visited[u]) {
                sortDfs(u);
            }
        }
        sorted.add(v);
    }

    void resDfs(int v) {
        colors[v] = num;
        for (int u : revGraph[v]) {
            if (colors[u] == -1) {
                resDfs(u);
            }
        }
    }

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
            graph[v].add(u);
            revGraph[u].add(v);

        }
        visited = new boolean[n];
        colors = new int[n];
        Arrays.fill(colors, -1);
    }


    void solve(PrintWriter out) throws IOException {
        input();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                sortDfs(i);
            }
        }
        for (int i = 1; i < n; i++) {
            int v = sorted.get(n-i);
            if (colors[v] == -1) {
                resDfs(v);
                num++;
            }
        }
        int res = 0;
        Set<Pair<Integer, Integer>> edges = new HashSet<>();
        for (int v = 0; v < n;
             v++) {
            for (int u : graph[v]) {
                if (colors[v] != colors[u]) {
                    Pair<Integer, Integer> p = new Pair<>(colors[v], colors[u]);
                    edges.add(p);
                }
            }
        }
        System.out.println(edges.size());

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("topsort.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new F().run();

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
}