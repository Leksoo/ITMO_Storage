package DiskrLab3;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task16 {

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
        FastScanner in = new FastScanner("choose2num.in");
        int n = in.nextInt();
        int k = in.nextInt();
        BigInteger number = new BigInteger("0");
        ArrayList<Integer> curr = new ArrayList<>();
        curr.add(0);
        for (int i = 0; i < k; i++) {
            curr.add(in.nextInt());
        }
        for (int i = 1; i < curr.size(); i++) {
            int start = 1 + curr.get(i - 1);
            int end = curr.get(i) - 1;
            for (int j = start; j <= end; j++) {
                number=number.add(comb(n - j, k - i));
            }
        }
        out.print(number);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("choose2num.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task16().run();

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
