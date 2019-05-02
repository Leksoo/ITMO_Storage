package DiskrLab3;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task13 {

    BigInteger fact(int n) {
        BigInteger a = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            a = a.multiply(new BigInteger(Integer.toString(i)));
        }
        return a;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("num2perm.in");
        int n = in.nextInt();
        String k1 = in.next();
        BigInteger k = new BigInteger(k1);
        int[] res = new int[n + 1];
        int[] used = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            BigInteger old = k.divide(fact(n - i));
            k = k.remainder(fact(n - i));
            int s = 0;
            for (int j = 1; j <= n; j++) {
                if (used[j] == 0) {
                    s++;
                    if (s == old.intValue() + 1) {
                        res[i] = j;
                        used[j] = 1;
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            out.print(res[i] + " ");
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("num2perm.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task13().run();

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
