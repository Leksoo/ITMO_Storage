package AlgoLab7;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class D {

    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] revGraph;
    int n;
    int m;
    boolean[] visited;
    int[] colors;
    Map<Integer,ArrayList<Integer>> map = new HashMap();
    ArrayList<Integer> sorted = new ArrayList<>();
    ArrayList<Integer> bridges = new ArrayList<>();
    int[] up;
    int[] tin;
    int[] tout;
    boolean hasCycle = false;
    int time = 0;
    int color = 0;

    void bridgeDfs(int v, int p) {
        time++;
        visited[v] = true;
        tin[v] = time;
        up[v] = time;
        for (int u : graph[v]) {
            if (u == p) {
                continue;
            }
            if (visited[u]) {
                up[v] = Math.min(up[v], tin[u]);
            } else {
                bridgeDfs(u, v);
                up[v] = Math.min(up[v], up[u]);
                if (up[u] > tin[v]) {
                    for (int a : graph[v]) {
                        if(a!=u) continue;
                        map.get(v).add(u);
                        map.get(u).add(v);
                    }
                }
            }
        }
    }

    void componentDfs(int v, int cur) {
        colors[v] = cur;
        for (int u : graph[v]) {
            if (colors[u] == -1) {
                int count = 0;
                for (int a:map.get(v)){
                    if(a==u){
                        count++;
                    }
                }
                if (count==1) {
                    // it is a bridge
                    color++;
                    componentDfs(u,color);
                } else {
                    componentDfs(u,cur);
                }
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
            map.put(i,new ArrayList<>());
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
        colors = new int[n];
        Arrays.fill(colors, -1);
        tin = new int[n];
        tout = new int[n];
        up = new int[n];
        Arrays.fill(up, Integer.MAX_VALUE);
    }


    void solve(PrintWriter out) throws IOException {
        input();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bridgeDfs(i, -1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                componentDfs(i, color);
                color++;
            }
        }
        System.out.println(color);
        for (int i:colors) {
            System.out.print(i+1+" ");
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
        new D().run();

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