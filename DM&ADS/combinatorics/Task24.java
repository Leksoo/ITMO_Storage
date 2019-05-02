package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task24 {

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

    static int[] prevPerm(int[] arr, int n) {
        for (int i = n - 2; i >= 0; i--)     {
            if (arr[i] > arr[i + 1]) {
                int m = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if ((arr[j] > arr[m]) && (arr[j] < arr[i])) {
                        m = j;
                    }
                }
                int t = arr[i];
                arr[i] = arr[m];
                arr[m] = t;
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
        FastScanner in = new FastScanner("nextperm.in");
        int n=in.nextInt();
        int[] next=new int[n];
        int[] prev=new int[n];
        for (int i = 0; i <n ; i++) {
            int a=in.nextInt();
            next[i]=a;
            prev[i]=a;
        }
        prev=prevPerm(prev,n);
        if (prev==null){
            for (int i = 0; i <n ; i++) {
                out.print("0 ");
            }
        }
        else{
            for (int x:prev
                 ) {
                out.print(x+" ");

            }
        }
        out.println();
        next=nextPerm(next,n);
        if (next==null){
            for (int i = 0; i <n ; i++) {
                out.print("0 ");
            }
        }
        else{
            for (int x:next
                    ) {
                out.print(x+" ");

            }
        }
        out.println();



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("nextperm.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task24().run();

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
