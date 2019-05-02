package AlgoLab7;

import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class C {

    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] revGraph;
    int n;
    int m;
    boolean[] visited;
    ArrayList<Integer> cutPoints = new ArrayList<>();
    int[] up;
    int[] tin;
    int time = 0;


    void cutPointsDfs(int v, int p) {
        visited[v] = true;
        tin[v] = time;
        up[v] = time;
        time++;
        int count = 0;
        for (int u : graph[v]) {
            if (u == p) {
                continue;
            }
            if (visited[u]) {
                up[v] = Math.min(up[v], tin[u]);
            } else if(!visited[u]){
                count++;
                cutPointsDfs(u, v);
                up[v] = Math.min(up[v], up[u]);
                if (up[u] >= tin[v] && p != -1 ) {
                    if(!cutPoints.contains(v))
                        cutPoints.add(v);
                }

            }

        }
        if (p == -1 && count > 1) {
            if(!cutPoints.contains(v))
                cutPoints.add(v);
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
            graph[u].add(v);


        }
        visited = new boolean[n];
        tin = new int[n];
        up = new int[n];
    }


    void solve(PrintWriter out) throws IOException {
        input();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cutPointsDfs(i, -1);
            }
        }
        System.out.println(cutPoints.size());
        Collections.sort(cutPoints);
        for (int v : cutPoints) {
            System.out.print(v + 1+" ");
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("topsort.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new C().run();

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