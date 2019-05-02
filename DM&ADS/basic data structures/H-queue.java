package AlgoLab2;

import java.io.*;
import java.util.*;

public class H {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("saloon.in");

        int n=in.nextInt();
        int[] times=new int[n];
        int[] pat=new int[n];
        int[] res = new int[n];
        for (int i = 0; i <n ; i++) {
            int t1=in.nextInt();
            int t2=in.nextInt();
            times[i]=t1*60+t2;
            pat[i]=in.nextInt();
        }
        for (int i = 0; i <n ; i++) {
            int count=0;
            int j0=0;
            for (int j = i-1; j >=0 ; j--) {
                if (res[j]>times[i]){
                    count++;
                    j0=j;
                }
            }
            if (pat[i]<count){
                res[i]=times[i];
                continue;
            }
            res[i]=times[i]+20;
            for (int j =i-1; j >=j0 ; j--) {
                if (res[j]>times[i]){
                    res[i]=res[j]+20;
                    break;
                }
                
            }
        }
        for (int i = 0; i <n ; i++) {
            out.println(res[i]/60+" "+ res[i]%60);
        }


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("saloon.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new H().run();

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