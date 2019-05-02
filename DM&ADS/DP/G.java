package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class G {
    static StringBuilder ans;
    static String str;
    static int[][] dp;
    static int[][] ind;

    static boolean isSuitable(String str, int i, int j) {
        if (str.charAt(i) == '(' && str.charAt(j) == ')') {
            return true;
        }
        if (str.charAt(i) == '{' && str.charAt(j) == '}') {
            return true;
        }
        if (str.charAt(i) == '[' && str.charAt(j) == ']') {
            return true;
        }
        return false;
    }

    static void answer(int l, int r) {
        int len = r - l + 1;
        if (dp[l][r] == 0) {
            ans.append(str.substring(l, r + 1));
            return;
        }
        if (dp[l][r] == len) {
            return;
        }

        if (ind[l][r] == Integer.MIN_VALUE) {
            ans.append(str.charAt(l));
            answer(l + 1, r - 1);
            ans.append(str.charAt(r));
            return;
        }
        answer(l, ind[l][r]);
        answer(ind[l][r] + 1, r);

    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        str = in.next();
        int n = str.length();
        dp = new int[n][n];
        ind = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    continue;
                }
                int var = Integer.MAX_VALUE;
                if (isSuitable(str, i, j)) {
                    var = dp[i + 1][j - 1];

                }
                int f = Integer.MIN_VALUE;
                for (int k = i; k < j; k++) {
                    if (dp[i][k] + dp[k + 1][j] < var) {
                        var = dp[i][k] + dp[k + 1][j];
                        f = k;
                    }

                }
                dp[i][j] = var;
                ind[i][j] = f;

            }
        }
        ans = new StringBuilder();
        answer(0, n - 1);
        System.out.print(ans);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        //out = new PrintWriter("allvectors.out");
        solve(out);
        // out.flush();
    }

    public static void main(String[] args) throws IOException {
        new G().run();

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