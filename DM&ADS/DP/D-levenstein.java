package AlgoLab3;

import java.io.*;
import java.util.*;

public class D {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("input.txt");
        String a = in.next();
        String b = in.next();
        int len1 = a.length();
        int len2 = b.length();
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i <=len1; i++) {
            for (int j = 0; j <=len2; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = j;
                    }
                } else if (j == 0) {
                    if (i == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = i;
                    }
                } else if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int f = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                    dp[i][j] =f+1;
                }
            }
        }
        out.print(dp[len1][len2]);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("output.txt");
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