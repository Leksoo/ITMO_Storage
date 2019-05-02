package DiskrLab4;

import java.io.*;
import java.util.*;

public class C {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("lottery.in");
        int n=in.nextInt();
        int m= in.nextInt();
        double res=0;
        double last=1;
        int[] a=new int[m+2];
        int[] b=new int[m+2];
        for (int i = 1; i <=m ; i++) {
            a[i]=in.nextInt();
            b[i]=in.nextInt();
        }
        for (int i = 1; i <=m ; i++) {
            double tmp;
            if(m==i) {
                 tmp = (1.0 / a[i]) * last * b[i];
            }
            else{
                tmp = (1.0 / a[i]) * last * (1.0 - 1.0 / a[i + 1]) * b[i];
            }
            last*=(1.0/a[i]);
            res+=tmp;
        }
        out.printf(Locale.ENGLISH,"%.10f",n-res);




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("lottery.out");
        solve(out);
        out.flush();
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