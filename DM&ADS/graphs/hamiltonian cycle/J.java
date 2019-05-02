package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class J {
    static int cons = (int) Math.pow(10, 8);
    static int n;
    static int[][] dp;
    static int[][] city;
    ArrayList<Integer> p;


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt() ;
        city = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                city[i][j] = in.nextInt();
            }
        }


        p = new ArrayList<>();

        int nq=1<<n;
        dp = new int[nq][n];
        for (int i = 0; i < nq; i++) {
            Arrays.fill(dp[i], cons);
        }
        for (int i = 0; i <n ; i++) {
            dp[1<<i][i]=0;
        }

        for (int mask = 1; mask <nq ; mask++) {
            for (int v = 0; v <n ; v++) {
                if (((1<<v) & mask)>0) {
                    for (int u = 0; u < n; u++) {
                        if (((1 << u) & mask) == 0 && u!=v) {
                            dp[mask | (1 << u)][u] = Math.min(dp[mask | (1 << u)][u], dp[mask][v] + city[v][u]);
                        }
                    }
                }
            }
        }
        int res=cons;
        int ind=0;
        ArrayList<Integer> path=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            if (dp[(1<<n)-1][i]<res){
                res=dp[(1<<n)-1][i];
                ind=i;
            }
        }

//        for (int[] x:dp
//                ) {
//            for (int y:x
//                    ) {
//                System.out.print(y+" ");
//
//            }
//            System.out.println();
//
//        }
        System.out.println(res);
        int mask = nq - 1;
        path.add(ind);
        while (path.size()!=n) {
            for (int j = 0;j<n;j++) {
                if (j!=ind && dp[mask][ind] ==dp[mask - (1<<ind)][j] + city[ind][j]){
                    path.add(j);
                    mask = mask - (1<<ind);
                    ind = j;

                    break;
                }
            }
        }
        for (int x:path
             ) {
            System.out.print(x+1+" ");

        }
        System.out.println();







    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        //  out = new PrintWriter("allvectors.out");
        solve(out);
        //out.flush();
    }

    public static void main(String[] args) throws IOException {
        new J().run();

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