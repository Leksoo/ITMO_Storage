package AlgoLab7;

import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class B {

    ArrayList<Pair<Integer,Integer>>[] graph;
    ArrayList<Integer>[] revGraph;
    int n;
    int m;
    boolean[] visited;
    int[] colors;
    ArrayList<Integer> bridges = new ArrayList<>();
    int[] up;
    int[] tin;
    int[] tout;
    boolean hasCycle = false;
    int time = 0;


    void bridgeDfs(int v,int p) {
        time++;
        visited[v] = true;
        tin[v] = time;
        up[v] = time;
        for (Pair<Integer,Integer> pair : graph[v]) {
            int u = pair.getKey();
            if(u == p){
                continue;
            }
            if (visited[u]) {
                up[v] = Math.min(up[v], tin[u]);
            } else {
                bridgeDfs(u, v);
                up[v] = Math.min(up[v], up[u]);
                if (up[u] > tin[v]) {
                    bridges.add(pair.getValue());
                }
            }

        }
        colors[v] = 2;
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
            graph[v].add(new Pair<>(u,i));
            graph[u].add(new Pair<>(v,i));


        }
        visited = new boolean[n];
        colors = new int[n];
        tin = new int[n];
        tout = new int[n];
        up = new int[n];
        Arrays.fill(up,Integer.MAX_VALUE);
    }


    void solve(PrintWriter out) throws IOException {
        input();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bridgeDfs(i, -1);
            }
        }
        System.out.println(bridges.size());
        Collections.sort(bridges);
        for (int v: bridges) {
            System.out.print(v+1+" ");
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
}