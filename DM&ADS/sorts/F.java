import java.io.*;
import java.util.*;

public class Style {


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("style.in");
        int n = in.nextInt();
        int[] nAr = new int[n];
        for (int i = 0; i < n; i++) {
            nAr[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] mAr = new int[m];
        for (int i = 0; i < m; i++) {
            mAr[i] = in.nextInt();
        }
        int first=0;
        int second=0;
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        while (i < n-1 || j < m-1){
            if (Math.abs(nAr[i]-mAr[j])<min){
                min=Math.abs(nAr[i]-mAr[j]);
                first=nAr[i];
                second=mAr[j];
            }
            if(i==n-1){
                j++;
                continue;
            }
            else if(j==m-1){
                i++;
                continue;
            }
            if (nAr[i]<=mAr[j]){
                i++;
            }
            else if (nAr[i]>mAr[j]) {
                j++;
            }
        }
        if (Math.abs(nAr[n-1]-mAr[m-1])<min){
            first=nAr[i];
            second=mAr[j];
        }
        out.print(first+" "+second);
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("style.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Style().run();

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

