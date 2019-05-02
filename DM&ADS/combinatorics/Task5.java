package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task5 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("telemetry.in");
        int n=in.nextInt();
        int k=in.nextInt();
        String[] arr=new String[(int)Math.pow(k,n)];
        for (int i = 0; i <k ; i++) {
            arr[i]=Integer.toString(i);
        }
        if (n==1){
            for (int i = 0; i <k ; i++) {
                out.println(i);
            }
            return;
        }
        for (int i = 1; i <n ; i++) {
            for (int j = 0; j <(int)Math.pow(k,i) ; j++) {
                String examp=arr[j];
                String examp2=arr[(int)Math.pow(k, i)-j-1];
                if (examp.length()>i){
                    examp=examp.substring(1);
                }
                if (examp2.length()>i){
                    examp2=examp2.substring(1);
                }
                for (int l = 0; l <k ; l++) {
                    if (l%2==0) {
                        arr[j+l*(int)Math.pow(k, i)]=""+l+examp;
                    }
                    else {
                        arr[j+l*(int)Math.pow(k, i)]=""+l+examp2;
                    }
                }
            }
        }
        for (String x:arr
                ) {
            out.println(x);

        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("telemetry.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task5().run();

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
