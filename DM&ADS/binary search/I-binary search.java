import java.io.*;
import java.util.*;

public class BinSearch {

    private static int binarySearchLeft(int[] a, int key) {
        int l =0;
        int r = a.length-1;

        while (l<r) {
            int mid =l+ (r-l)/2;
            int midEl = a[mid];

            if (midEl >= key)
                r=mid;
            else
                l = mid+1;

        }
        if(a[l]==key){
            return l+1;
        }
        else {
            return -1;
        }
    }
    private static int binarySearchRight(int[] a, int key) {
        int l =0;
        int r = a.length-1;

        while (l<r) {
            int mid =r - (r-l)/2;
            int midEl = a[mid];

            if (midEl <= key)
                l =mid;
            else
                r = mid-1;

        }
        if(a[l]==key){
            return l+1;
        }
        else {
            return -1;
        }
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in= new FastScanner("binsearch.in");
        int n=in.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i] = in.nextInt();

        }
        int m=in.nextInt();
        for (int i = 0; i <m ; i++) {
            int k=in.nextInt();
            out.println(binarySearchLeft(arr,k)+" "+binarySearchRight(arr,k));
        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("binsearch.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new BinSearch().run();

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









