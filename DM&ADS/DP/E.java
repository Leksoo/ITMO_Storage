package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class E {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        if (n==0){
            System.out.println(0);
            System.out.println("0 0");
            return;
        }
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = in.nextInt();
        }
        int cons = (int) Math.pow(10, 9);
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], cons);
        }
        int[][] days = new int[n + 1][n + 1];
        dp[0][0] = 0;
        int count;
        int ans = Integer.MAX_VALUE;
        int k1 = 0;
        int k2 = 0;
        ArrayList<Integer> daysAns = new ArrayList<>();
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] != cons) {
                    if (j > 0 && dp[i + 1][j - 1] > dp[i][j]) {
                        days[i + 1][j - 1] = j;
                        dp[i + 1][j - 1] = dp[i][j];
                    }

                    if (cost[i + 1] > 100) {
                        count = 1;
                    } else count = 0;
                    int j0 = j + count;
                    if ((dp[i + 1][j0] > dp[i][j] + cost[i + 1])) {
                        days[i + 1][j0] = j;
                        dp[i + 1][j0] = dp[i][j] + cost[i + 1];
                    }

                }
            }
        }
//        for (int[] x:dp
//             ) {
//            for (int y:x
//                 ) {
//                System.out.print(y+" ");
//            }
//            System.out.println();
//
//        }
        for (int i = 0; i <= n; i++) {
            if (ans >= dp[n][i]) {
                ans = dp[n][i];
                k1 = i;
            }
        }
        System.out.println(ans);
        System.out.print(k1 + " ");
        for (int i = n; i > 0; i--) {
            if (days[i][k1] == k1 + 1) {
                daysAns.add(i);
                k2++;

            }
            k1 = days[i][k1];
        }
        System.out.print(k2);
        System.out.println();
        Collections.sort(daysAns);
        for (int x : daysAns
                ) {
            System.out.println(x);

        }
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        //out = new PrintWriter("allvectors.out");
        solve(out);
        // out.flush();
    }

    public static void main(String[] args) throws IOException {
        new E().run();

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