package DiskrLab3;


import java.io.*;
import java.util.*;

public class Task19 {
    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("num2brackets2.in");
        int n = in.nextInt();
        long k = Long.parseLong(in.next());
        k = k + 1;
        int d = 0;
        StringBuilder res = new StringBuilder();
        long[][] mas = new long[2 * n + 1][n + 1];
        for (int i = 0; i < 2 * n + 1; i++) {
            Arrays.fill(mas[i], 0);
        }
        mas[0][0] = 1;
        for (int i = 1; i < 2 * n + 1; i++) {
            for (int j = 0; j <= n; j++) {
                if (j > 0 && j < n) {
                    mas[i][j] = mas[i - 1][j - 1] + (mas[i - 1][j + 1]);
                } else if (j < n + 1 && !(j > 0)) {
                    mas[i][j] = mas[i - 1][j + 1];
                } else mas[i][j] = mas[i - 1][j - 1];
            }
        }
        ArrayList<String> matches = new ArrayList();
        long num;
        for (int i = 2 * n - 1; i >= 0; i--) {
            if (d < n ) {
                num = mas[i][d + 1];
                num = num * ((long) Math.pow(2, (i - 1 - d) / 2));
                if (k <= num) {
                    matches.add("(");
                    res.append("(");
                    d++;
                    continue;
                }
            } else num = 0;
            k -= num;


            if (d >0 && matches.size() > 0 && matches.get(matches.size() - 1).equals("(")) {
                num = mas[i][d - 1];
                num = num * ((long) Math.pow(2, (i + 1 - d) / 2));
                if (k <= num) {
                    matches.remove(matches.size() - 1);
                    res.append(")");
                    d--;
                    continue;
                }
            } else num = 0;
            k -= num;


//////////////////////////////////////
            if (d < n ) {
                num = mas[i][d + 1];
                num = num * ((long) Math.pow(2, (i - 1 - d) / 2));
                if (k <= num) {
                    matches.add("[");
                    res.append("[");
                    d++;
                    continue;
                }
            } else num = 0;
            k -= num;


            if (d >0 && matches.size() > 0 && matches.get(matches.size() - 1).equals("[")) {
                num = mas[i][d - 1];
                num = num * ((long) Math.pow(2, (i + 1 - d) / 2));
                if (k <= num) {
                    matches.remove(matches.size() - 1);
                    res.append("]");
                    d--;
                    continue;
                }
            } else num = 0;
            k -= num;


        }
        out.print(res);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("num2brackets2.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task19().run();

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
