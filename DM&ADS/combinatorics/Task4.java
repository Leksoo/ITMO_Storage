package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task4 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("chaincode.in");
        int n=in.nextInt();
        Set<String> codes=new HashSet<>();
        StringBuilder a=new StringBuilder();
        while (a.length()<n){
            a.insert(0,0);
        }
        codes.add(a.toString());
        out.println(a);
        while (true){
            StringBuilder b=new StringBuilder(a);
            StringBuilder c=new StringBuilder(a);
            if (!codes.contains(b.append(1).deleteCharAt(0).toString())){
                codes.add(b.toString());
                out.println(b);
                a=new StringBuilder(b);

            }
            else if (!codes.contains(c.append(0).deleteCharAt(0).toString())){
                codes.add(c.toString());
                out.println(c);
                a=new StringBuilder(c);
            }
            else {
                return;
            }

        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("chaincode.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task4().run();

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
