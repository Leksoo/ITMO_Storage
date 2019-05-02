package DiskrLab4;

import java.io.*;
import java.util.*;

public class B {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("shooter.in");
        int n=in.nextInt();
        int m=in.nextInt();
        int k= in.nextInt();
        double b=1.0/n;

        double res=0;
        double important=0;
        for (int i = 0; i <n ; i++) {
            double p=in.nextDouble();
            double p1=1.0-p;
            if(k-1==i){
                important=p1;
            }
            res+=Math.pow(p1,m)*b;
        }
        res=Math.pow(important,m)*b/res;
        out.printf(Locale.ENGLISH,"%.13f",res);




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("shooter.out");
        solve(out);
        out.flush();
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
