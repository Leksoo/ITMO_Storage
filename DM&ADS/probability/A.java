package DiskrLab4;

import java.io.*;
import java.util.*;

public class A {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("exam.in");
        int k=in.nextInt();
        int n= in.nextInt();
        double res=0;
        for (int i = 0; i <k ; i++) {
            int p=in.nextInt();
            int m=in.nextInt();
            res+=1.0*p*m/100;
        }
        res/=n;
        out.printf(Locale.ENGLISH,"%.5f",res);




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("exam.out");
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