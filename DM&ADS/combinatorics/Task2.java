package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task2 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("gray.in");
        int n=in.nextInt();
        String[] arr=new String[(int)Math.pow(2,n)];
        arr[0]="0";
        arr[1]="1";
        if (n==1){
            out.println(0);
            out.println(1);
            return;
        }
        for (int i = 1; i <n ; i++) {
            for (int j = 0; j <(int)Math.pow(2,i) ; j++) {
                String a1="0"+arr[j];
                String a2="1"+arr[j];
                arr[j]=a1;
                arr[-1-j+2*(int)Math.pow(2,i)]=a2;
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
        out = new PrintWriter("gray.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();

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
