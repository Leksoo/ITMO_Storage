package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task20 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("brackets2num2.in");
        String input = in.next();
        int n = input.length() / 2;
        long k = 0;
        int d = 0;
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
            num=0;
            int d1 = 0;
            char c=input.charAt(2 * n - 1 - i);


            if ( input.charAt(2 * n - 1 - i) == '(' || input.charAt(2 * n - 1 - i) == ')' || input.charAt(2 * n - 1 - i) == '[' || input.charAt(2 * n - 1 - i) == ']') {
                d1 = 1;



            }


            if (input.charAt(2 * n - 1 - i) == ')' || input.charAt(2 * n - 1 - i) == '[' || input.charAt(2 * n - 1 - i) == ']') {
                int nd = d + 1;
                if (d <n ) {

                    num = mas[i][nd];
                    num = num * ((long) Math.pow(2, (i - nd) / 2));
                    k += num;
                }
                d1 = -1;
            }
            if (input.charAt(2 * n - 1 - i) == '[' || input.charAt(2 * n - 1 - i) == ']') {
                int nd = d - 1;
                if (d>0  ) {
                    if (matches.size()>0 && !matches.get(matches.size()-1).equals("(")){}
                    else {
                        num = mas[i][nd];
                        num = num * ((long) Math.pow(2, (i - nd) / 2));
                        k += num;
                    }
                }
                d1 = 1;

            }
            if (input.charAt(2 * n - 1 - i) == ']') {
                int nd = d + 1;

                if (d <n) {
                    num = mas[i][nd];
                    num = num * ((long) Math.pow(2, (i - nd) / 2));
                    k += num;
                }
                d1 = -1;

            }
            d += d1;
            if (c=='(' || c=='[') {
                matches.add(Character.toString(c));
            }
            else {
                matches.remove(matches.size()-1);
            }


        }
        out.print(k);
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("brackets2num2.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task20().run();

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
