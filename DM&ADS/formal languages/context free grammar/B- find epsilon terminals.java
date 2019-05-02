package DiskrLab6;

import java.io.*;
import java.util.*;

public class B {


    void solve(PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("epsilon.in"));
        String[] a = in.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        char start = a[1].charAt(0);
        TreeSet<Character> ans = new TreeSet<>();
        HashSet<String> rules = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String input = in.readLine();
            String[] b = input.split(" ");
            if (b.length == 2) {
                ans.add(b[0].charAt(0));
            } else {
                rules.add(input);
            }
        }
        while (true) {
            boolean needNext = false;
            for (String rule : rules) {
                String[] c = rule.split(" ");
                boolean eps = true;
                for (int i = 0; i < c[2].length(); i++) {
                    if (!ans.contains(c[2].charAt(i))) {
                        eps = false;
                        break;
                    }
                }
                if (eps) {
                    if (!ans.contains(c[0].charAt(0))) {
                        ans.add(c[0].charAt(0));
                        needNext = true;
                        break;
                    }
                }
            }
            if (!needNext) {
                break;
            }
        }
        for (char ch : ans) {
            out.print(ch + " ");
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("epsilon.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new B().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                if (line.equals("")) {
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