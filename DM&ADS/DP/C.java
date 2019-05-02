package AlgoLab3;

import java.io.*;
import java.util.*;

public class C {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        int n=in.nextInt();
        long[][] dp=new long[n+1][10];
        for (int i = 0; i <=9 ; i++) {
            dp[1][i]=1;
        }
        dp[1][8]=0;
        dp[1][0]=0;
        int a=(int)(Math.pow(10,9));
        for (int i =2; i <=n ; i++) {
            dp[i][0]=(dp[i-1][4]+dp[i-1][6])%a;
            dp[i][1]=(dp[i-1][6]+dp[i-1][8])%a;
            dp[i][2]=(dp[i-1][7]+dp[i-1][9])%a;
            dp[i][3]=(dp[i-1][4]+dp[i-1][8])%a;
            dp[i][4]=(dp[i-1][0]+dp[i-1][9]+dp[i-1][3])%a;
            dp[i][5]=0;
            dp[i][6]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][7])%a;
            dp[i][7]=(dp[i-1][2]+dp[i-1][6])%a;
            dp[i][8]=(dp[i-1][3]+dp[i-1][1])%a;
            dp[i][9]=(dp[i-1][4]+dp[i-1][2])%a;

        }
        long sum=0;
        for (int i = 0; i <10 ; i++) {
            sum+=dp[n][i]%a;
        }
        System.out.print(sum%a);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
       // out = new PrintWriter("allvectors.out");
        solve(out);
        //out.flush();
    }

    public static void main(String[] args) throws IOException {
        new C().run();

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