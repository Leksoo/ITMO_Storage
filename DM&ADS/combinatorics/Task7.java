package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task7 {
    static int[] nextPerm(int[] arr, int n) {
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                int min = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if ((arr[j] < arr[min]) && (arr[j] > arr[i])) {
                        min = j;
                    }
                }
                int t = arr[i];
                arr[i] = arr[min];
                arr[min] = t;
                int[] b = new int[n];
                int f = 1;
                for (int j = i + 1; j < n; j++) {
                    b[j] = arr[n - f];
                    f++;
                }
                for (int j = i + 1; j < n; j++) {
                    arr[j] = b[j];
                }
                return arr;
            }

        }
        return null;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("permutations.in");
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        while (true) {
            for (int i = 0; i < n; i++) {
                out.print(arr[i] + " ");

            }
            out.println();
            arr = nextPerm(arr, n);
            if (arr == null) {
                return;
            }

        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("permutations.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task7().run();

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
