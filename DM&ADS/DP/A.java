package AlgoLab3;

import java.io.*;
import java.util.*;

public class A {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("input.txt");
        int n=in.nextInt();
        int k=in.nextInt();
        int[] mas=new int[n];
        int[] dp=new int[n];
        dp[0]=1;
        dp[n-1]=1;
        for (int i = 1; i <n-1 ; i++) {
            mas[i]=in.nextInt();
        }
        int i=0;
        int sum=0;
        int count=0;
        while (i<n-1) {

            int max = Integer.MIN_VALUE;
            int ind=0;
            for (int j = i+1; j < i+k && j<n; j++) {
                if (mas[j]>=0){
                    ind=j;
                    break;
                }
                if (mas[j] > max) {
                    max = mas[j];
                    ind=j;
                }
            }
            dp[ind]=1;
            sum+=mas[ind];
            i=ind;
            count++;
        }
        out.println(sum);
        out.println(count);
        for (int j = 0; j <dp.length ; j++) {
            if (dp[j]==1){
                out.print(j+1+" ");
            }
        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("output.txt");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new A().run();

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