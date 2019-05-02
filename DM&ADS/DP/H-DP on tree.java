package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class H {
    static int[] tree;
    static int n;
    static int[] res;

    static int ans(int i) {
        if (res[i] != -1) {
            return res[i];
        }
        int kinder = 0;
        int oma = 0;
        for (int j = 1; j <= n; j++) {
            if (tree[j] == i) {
                kinder += ans(j);
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= n; k++) {

                if (tree[j] == i && j == tree[k]) {
                    oma += ans(k);
                }
            }
        }
        res[i] = Math.max(1 + oma, kinder);
        return res[i];

    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();
        tree = new int[n + 1];
        res = new int[n + 1];
        Arrays.fill(res, -1);
        int root = 0;
        for (int i = 1; i <= n; i++) {
            int a = in.nextInt();
            tree[i] = a;
            if (a == 0) {
                root = i;
            }
        }
        System.out.print(ans(root));


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        //out = new PrintWriter("allvectors.out");
        solve(out);
        //out.flush();
    }

    public static void main(String[] args) throws IOException {
        new H().run();

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