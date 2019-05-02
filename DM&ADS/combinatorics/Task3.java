package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task3 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("antigray.in");
        int n=in.nextInt();
        for (int i = 0; i <(int)Math.pow(3,n-1) ; i++) {
            StringBuilder digit=new StringBuilder();
            int m=i;
            while (m>0){
                digit.insert(0,m%3);
                m/=3;

            }
            while (digit.length()<n){
                digit.insert(0,0);
            }
            for (int j = 0; j <3 ; j++) {
                out.println(digit);
                for (int k = 0; k <digit.length() ; k++) {
                    digit.setCharAt(k,(char)(+digit.charAt(k)+1));
                    if (digit.charAt(k)=='3'){
                        digit.setCharAt(k,'0');
                    }
                }
            }
        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("antigray.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();

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
