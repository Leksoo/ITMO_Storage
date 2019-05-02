package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task23 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("nextvector.in");
        char[] vector = in.next().toCharArray();
        int size = vector.length;
        char[] next = Arrays.copyOf(vector, vector.length);
        char[] prev = Arrays.copyOf(vector, vector.length);

        //prev
        int n = size;
        while (n > 0 && prev[n - 1] != '1') {
            prev[n - 1] = '1';
            n--;
        }
        if (n == 0) {
            prev = null;
        }
        else {
            prev[n - 1] = '0';
        }

        if (prev != null) {
            out.println(prev);
        } else out.println('-');

        //next
        n = size;
        while (n > 0 && next[n - 1] != '0') {
            next[n - 1] = '0';
            n--;
        }
        if (n == 0) {
            next = null;
        }
        else {
            next[n - 1] = '1';
        }
        if (next != null) {
            out.println(next);
        } else out.println('-');


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nextvector.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task23().run();

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
