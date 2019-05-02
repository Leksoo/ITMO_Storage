package DiskrLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Task8 {

    int[] nextComb(int[] arr, int n, int k) {
        int[] arrCopy = Arrays.copyOf(arr, arr.length + 1);
        int i = k - 1;
        arrCopy[k] = n + 1;
        while ((i >= 0) && (arrCopy[i + 1] - arrCopy[i] < 2)) {
            i--;
        }
        if (i >= 0) {
            arrCopy[i]++;
            for (int j = i + 1; j < k; j++) {
                arrCopy[j] = arrCopy[j - 1] + 1;
            }
            int[] arrCopy2 = Arrays.copyOf(arrCopy, arrCopy.length-1);
            return arrCopy2;
        } else {
            return null;
        }
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("choose.in");
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[k];
        for (int i = 1; i <= k; i++) {
            arr[i - 1] = i;
        }
        while (true) {
            for (int i = 0; i < k; i++) {
                out.print(arr[i] + " ");

            }
            out.println();
            arr = nextComb(arr, n, k);
            if (arr == null) {
                return;
            }

        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("choose.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task8().run();

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
