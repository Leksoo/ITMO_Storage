package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task1 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("allvectors.in");
        int n=in.nextInt();
        for (int i = 0; i <(int)Math.pow(2,n) ; i++) {
            StringBuilder a=new StringBuilder(Integer.toBinaryString(i));
            while (a.length()<n){
                a.insert(0,0);
            }
            out.println(a);
        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("allvectors.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();

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