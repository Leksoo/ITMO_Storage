package AlgoLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class B {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        int n=in.nextInt();
        int[] mas=new int[n];
        int[] dp=new int[n];
        int[] ans=new int[n];
        Arrays.fill(ans,-100);
        dp[0]=1;
        for (int i = 0; i <n ; i++) {
            mas[i]=in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int max=0;
            for (int j = 0; j <i ; j++) {
                if (dp[j]>max && mas[j]<=mas[i]){
                    ans[i]=j;
                    max=dp[j];
                }
            }
            dp[i]=max+1;
        }
        int max=0;
        int ind=0;
        for (int i = 0; i <n ; i++) {
            if (dp[i]>max){
                max=dp[i];
                ind=i;
            }
        }
        System.out.println(max);
        ArrayList<Integer> a=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            if (ind==-100){
                break;
            }
            else {
                a.add(0,mas[ind]);
                ind=ans[ind];
            }
        }
        for (int x:a
             ) {
            System.out.print(x+" ");
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
       // out = new PrintWriter("out");
        solve(out);
       // out.flush();
    }

    public static void main(String[] args) throws IOException {
        new B().run();

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