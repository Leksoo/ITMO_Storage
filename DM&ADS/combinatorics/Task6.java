package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task6 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("vectors.in");
        int n=in.nextInt();
        ArrayList<String> mas=new ArrayList<>();
        for (int i = 0; i <(int)Math.pow(2,n) ; i++) {
            StringBuilder a=new StringBuilder(Integer.toBinaryString(i));
            boolean marker=true;
            for (int j = 1; j <a.length() ; j++) {
                if (a.charAt(j)=='1' && a.charAt(j-1)=='1'){
                    marker=false;
                    break;
                }
            }
            if (!marker){
                continue;
            }
            while (a.length()<n){
                a.insert(0,0);
            }
            mas.add(a.toString());

        }
        out.println(mas.size());
        for (String x:mas
                ) {
            out.println(x);

        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("vectors.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task6().run();

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
