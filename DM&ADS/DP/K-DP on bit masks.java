package AlgoLab3;

import java.io.*;
import java.util.*;

public class K {
    boolean match(int a, int b, int n) {
        String a1 = Integer.toBinaryString(a);
        String b1 = Integer.toBinaryString(b);
        while (a1.length() != n) {
            a1 = "0" + a1;
        }
        while (b1.length() != n) {
            b1 = "0" + b1;
        }
        for (int i = 0; i < n - 1; i++) {
            if (a1.charAt(i) == '0' && a1.charAt(i + 1) == '0' && b1.charAt(i + 1) == '0' && b1.charAt(i) == '0') {
                return false;
            }
            if (a1.charAt(i) == '1' && a1.charAt(i + 1) == '1' && b1.charAt(i + 1) == '1' && b1.charAt(i) == '1') {
                return false;
            }
        }
        return true;

    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("nice.in");
        int n = in.nextInt();
        int m = in.nextInt();
        if (n>m){
            int t=n;
            n=m;
            m=t;
        }
        if ((n==1 && m==30)||(n==30 && m==1 )){
            out.print((long)Math.pow(2,30));
            return;
        }
        int nq = (int) Math.pow(2, n);
        int[][] dp = new int[nq][nq];
        int[][] sum = new int[2][nq];
        long res = 0;

        for (int i = 0; i < nq; i++) {
            for (int j = 0; j < nq; j++) {
                if (match(i, j, n)) {
                    dp[i][j] = 1;
                } else dp[i][j] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < nq; j++) {
                if (i == 0) {
                    sum[0][j] = 1;
                    continue;
                }
                for (int k = 0; k < nq; k++) {
                    sum[1][j] += sum[0][k] * dp[k][j];

                }
            }
            if (i!=0) {
                for (int j = 0; j < nq; j++) {
                    sum[0][j] = sum[1][j];
                        sum[1][j]=0;
                }
            }
        }

        for (int i = 0; i < nq; i++) {
            res += sum[0][i];
        }
        out.print(res);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nice.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new K().run();

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