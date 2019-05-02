package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task28 {

    int[] nextMPerm (int[] arr,int n){
        int k=n-2;
        for (int i = n-2; i >=0 ; i--) {
            if(arr[i]>=arr[i+1]){
                k--;
            }
            else {
                break;
            }
        }
        if (k >= 0) {
            int f=k+1;
            for (int i = f; i <n-1 ; i++) {
                if (arr[i+1]>arr[k]){
                    f++;
                }
                else {
                    break;
                }
            }
            int t = arr[f];
            arr[f] = arr[k];
            arr[k] = t;
            int[] b = new int[n];
            int f2 = 1;
            for (int j = k + 1; j < n; j++) {
                b[j] = arr[n - f2];
                f2++;
            }
            for (int j = k + 1; j < n; j++) {
                arr[j] = b[j];
            }
            return arr;
        }
        else {
            return null;
        }
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("nextmultiperm.in");
        int n=in.nextInt();
        int[] next=new int[n];
        for (int i = 0; i <n ; i++) {
            int a=in.nextInt();
            next[i]=a;
        }
        next=nextMPerm(next,n);
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
        out = new PrintWriter("nextmultiperm.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task28().run();

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
