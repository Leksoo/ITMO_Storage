package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class I {
    static int n;
    static int m;
    static int[][] graph;
    static int[] used;
    static int[] ans;

    static int dfs(Integer i) {
        if (used[i] == 1) {
            return 0;
        }
        used[i] = 1;
        for (int j = 0; j < n; j++) {
            if (graph[j][i] != -1) {
                if (ans[j] == -1 || dfs(ans[j]) == 1) {
                    ans[j] = i;
                    return 1;
                }
            }
        }
        return 0;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();
        m = in.nextInt();
        graph = new int[n][];
        for (int i = 0; i < n; i++) {
            int[] a = new int[m];
            Arrays.fill(a, -1);
            int b = in.nextInt() - 1;
            while (b != -1) {
                a[b] = 1;
                b = in.nextInt() - 1;
            }
            graph[i] = a;
        }
        int count = 0;
        used = new int[Math.max(n, m)];
        ans = new int[Math.max(n, m)];
        Arrays.fill(ans, -1);
        for (int i = 0; i < m; i++) {
            dfs(i);
            used = new int[Math.max(n, m)];

        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] != -1) {
                count++;
            }
        }
        System.out.println(count);
        for (int i = 0; i < n; i++) {
            if (ans[i] != -1) {
                System.out.println(i + 1 + " " + (ans[i] + 1));
            }
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        //out = new PrintWriter("allvectors.out");
        solve(out);
        //out.flush();
    }

    public static void main(String[] args) throws IOException {
        new I().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null || line.equals("")) {
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