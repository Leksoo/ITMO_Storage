package DiskrLab6;

import java.io.*;
import java.util.*;

public class D {

    public static final int A = 1000000007;

    void solve(PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("nfc.in"));
        String[] a = in.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        char start = a[1].charAt(0);

        HashSet<String> nonTrules = new HashSet<>();
        HashSet<String> Trules = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String input = in.readLine();
            String[] c = input.split(" ");
            if (c[2].length() == 1) {
                Trules.add(c[0] + " " + c[2]);
            } else {
                nonTrules.add(c[0] + " " + c[2]);
            }

        }
        String inp = in.readLine();
        int inpL = inp.length();
        long[][][] dp = new long[26][inpL][inpL];
        for (int i = 0; i < inpL; i++) {
            for (String rule : Trules) {
                int nonT = rule.charAt(0) - 'A';
                int T = rule.charAt(2);
                if (T == inp.charAt(i)){
                    dp[nonT][i][i]=1;
                }
            }
        }

        for (int i = inpL-2; i >=0 ; i--) {
            for (int j = i; j <inpL ; j++) {
                    for (String rule : nonTrules) {
                        int nonT = rule.charAt(0) - 'A';
                        int non1 = rule.charAt(2) - 'A';
                        int non2 = rule.charAt(3) - 'A';
                        for (int k = i; k <j ; k++) {
                            dp[nonT][i][j] += dp[non1][i][k] * dp[non2][k + 1][j];
                            dp[nonT][i][j] %= A;
                        }
                }
            }
        }
        out.print(dp[start-'A'][0][inpL-1]);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nfc.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new D().run();

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