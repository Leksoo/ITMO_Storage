import java.io.*;
import java.util.*;

public class antiqs {


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("antiqs.in");
        int n = in.nextInt();
        int[] a=new int[n];
            for (int i =0; i <n; i++) {
                a[i] = i+1;
            }
        for (int i = 0; i <n; i++) {
            int t=a[i];
            a[i]=a[i/2];
            a[i/2]=t;
        }

        for (int x:a
             ) {
            out.print(x+" ");

        }
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("antiqs.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new antiqs().run();

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

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

}
