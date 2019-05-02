package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class F {
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


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        String str = in.next();
        int n = str.length();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    continue;
                }
                int var1 = Integer.MAX_VALUE;
                if (isSuitable(str, i, j)) {
                    var1 = dp[i + 1][j - 1];
                }
                int var2 = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    if (dp[i][k] + dp[k + 1][j] < var2) var2 = dp[i][k] + dp[k + 1][j];
                    dp[i][j] = Math.min(var1, var2);
                }

            }
        }
//        System.out.println(n);
//        for (int[] x : dp
//                ) {
//            for (int y : x
//                    ) {
//                System.out.print(y + " ");
//
//            }
//            System.out.println();
//
//
//        }

        System.out.print(n - dp[0][n - 1]);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        //out = new PrintWriter("allvectors.out");
        solve(out);
        // out.flush();
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