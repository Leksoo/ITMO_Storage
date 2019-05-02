package DiskrLab3;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task15 {

    static BigInteger fact(int n) {
        BigInteger a = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            a = a.multiply(new BigInteger(Integer.toString(i)));
        }
        return a;
    }

    static BigInteger comb(int n, int k) {
        return fact(n).divide(fact(k)).divide(fact(n - k));
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("num2choose.in");
        int n = in.nextInt();
        int k = in.nextInt();
        int number = 1;
        BigInteger m = new BigInteger(in.next());
        ArrayList<Integer> comb = new ArrayList<>();
        while (k > 0) {
            if (m.compareTo(comb(n - 1, k - 1)) < 0) {
                comb.add(number);
                k -= 1;
            } else {
                m = m.subtract(comb(n - 1, k - 1));
            }
            number++;
            n -= 1;
        }
        for (int x : comb
                ) {
            out.print(x + " ");

        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("num2choose.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task15().run();

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
