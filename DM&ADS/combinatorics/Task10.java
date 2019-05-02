package DiskrLab3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Task10 {

    ArrayList<Integer> nextPart(ArrayList<Integer> arr) {
        arr.set(arr.size() - 1, arr.get(arr.size() - 1) - 1);
        arr.set(arr.size() - 2, arr.get(arr.size() - 2) + 1);
        if (arr.get(arr.size() - 2) > arr.get(arr.size() - 1)) {
            arr.set(arr.size() - 2, arr.get(arr.size() - 2) + arr.get(arr.size() - 1));
            arr.remove(arr.size() - 1);
            return arr;
        }
        while (arr.get(arr.size() - 2) * 2 <= arr.get(arr.size() - 1)) {
            arr.add(arr.get(arr.size() - 1) - arr.get(arr.size() - 2));
            arr.set(arr.size() - 2, arr.get(arr.size() - 3));
        }
        return arr;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("partition.in");
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(1);

        }
        while (true) {
            for (int i = 0; i < a.size() - 1; i++) {
                out.print(a.get(i) + "+");
            }
            out.print(a.get(a.size() - 1));
            out.println();
            a = nextPart(a);
            if (a.size() == 1) {
                out.print(a.get(0));
                return;
            }

        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("partition.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task10().run();

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
