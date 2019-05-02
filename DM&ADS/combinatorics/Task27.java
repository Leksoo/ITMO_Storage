package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task27 {

    String nextPSP(String a) {
        int closed = 0;
        int opened = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '(') {
                opened++;
                if (closed > opened) {
                    break;
                }
            } else {
                closed++;
            }
        }
        a = a.substring(0, a.length() - opened - closed);
        if (a.equals("")) {
            return null;
        }
        StringBuilder b=new StringBuilder(a);
        b.append(")");
        for (int i = 0; i < opened; i++) {
            b.append("(");
        }
        for (int i = 0; i < closed - 1; i++) {
            b.append(")");
        }
        return b.toString();

    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("nextbrackets.in");
        String psp=in.next();
        psp=nextPSP(psp);
        if (psp==null){
            out.print("-");
        }
        else out.print(psp);




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nextbrackets.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task27().run();

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
