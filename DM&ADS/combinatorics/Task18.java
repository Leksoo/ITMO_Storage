package DiskrLab3;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task18 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner();
        String str=in.next();
        int n=str.length()/2;

        BigInteger k = new BigInteger("0");
        int d = 1;
        BigInteger[][] mas = new BigInteger[2 * n + 1][n + 1];
        for (int i = 0; i < 2 * n + 1; i++) {
            Arrays.fill(mas[i], new BigInteger("0"));
        }
        mas[0][0] = new BigInteger("1");
        for (int i = 1; i < 2 * n + 1; i++) {
            for (int j = 0; j <= n; j++) {
                if (j > 0 && j < n) {
                    mas[i][j] = mas[i - 1][j - 1].add(mas[i - 1][j + 1]);
                } else if (j < n + 1 && !(j > 0)) {
                    mas[i][j] = mas[i - 1][j + 1];
                } else mas[i][j] = mas[i - 1][j - 1];
            }
        }
        for (BigInteger[] x:mas
             ) {
            for (BigInteger y:x
                 ) {
                System.out.print(y+" ");

            }
            System.out.println();

        }
        for (int i = 2*n-1; i >=0; i--) {
            if(str.charAt(2*n-1-i)=='('){
                d++;

            }
            else {
                    if (d<=n) {
                    k = k.add(mas[i][d]);
                }
                d--;

            }
        }
        out.print(k);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("brackets2num.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task18().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
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
