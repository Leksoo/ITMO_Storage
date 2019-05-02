import java.io.*;
import java.util.*;

public class RadixSort {


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("radixsort.in");
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.next();
        }


        int R = 100000 ;
        String[] b;

        for (int i = m-1; i >= m-k; i--) {
            b=new String[n];
            int[] count = new int[R];
            for (int j = 0; j < n; j++) {
                count[arr[j].charAt(i)]++;
            }
            int c=0;
            for (int j = 0; j < R; j++) {
                int tmp = count[j];
                count[j] = c;
                c += tmp;
            }

            for (int j = 0; j < n; j++) {
                int d = arr[j].charAt(i);
                b[count[d]] = arr[j];
                count[d]++;
            }
            for (int j = 0; j < n; j++) {
                arr[j] = b[j];
            }
        }


        for (String x : arr
                ) {
            out.println(x);
        }
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("radixsort.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new RadixSort().run();

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