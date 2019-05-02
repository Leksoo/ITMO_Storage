package DiskrLab3;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task14 {

    BigInteger fact(int n) {
        BigInteger a = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            a = a.multiply(new BigInteger(Integer.toString(i)));
        }
        return a;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("perm2num.in");
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        BigInteger s = new BigInteger("0");
        int[] used = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= a[i] - 1; j++) {
                if (used[j] == 0)
                    s = s.add(fact(n - i));
            }
            used[a[i]] = 1;

        }
        out.print(s);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("perm2num.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task14().run();

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
