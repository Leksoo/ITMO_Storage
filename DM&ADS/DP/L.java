package AlgoLab3;

import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class L {
    static int n;
    static int w;

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("skyscraper.in");
        n=in.nextInt();
        w=in.nextInt();
        ArrayList<Integer> d=new ArrayList<>();
        ArrayList<Integer> c=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            c.add(in.nextInt());
            d.add(1+i);
        }
        Collections.reverse(c);
        Collections.reverse(d);
        System.out.print(Integer.MAX_VALUE);





    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("skyscraper.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new L().run();

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