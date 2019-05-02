package AlgoLab2;

import java.io.*;
import java.util.*;

public class J {

    void solve(PrintWriter out) throws IOException {
        FastScanner scanner=new FastScanner("bureaucracy.in");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Deque<Integer> queue=new ArrayDeque<>();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=(scanner.nextInt());
        }
        int m0=m/arr.length;
        int m1=m%arr.length;
        for (int i = 0; i <n ; i++) {
            arr[i]=arr[i]-m0;
            if (arr[i]<0){
                m1-=arr[i];
                arr[i]=0;
            }
        }
        for (int i = 0; i <n ; i++) {
            if (arr[i]>0) {
                queue.addLast(arr[i]);
            }

        }
        if (queue.size()==0){
            out.print(-1);
            return;
        }
        for (int i = 0; i <m1 ; i++) {
            int a=queue.pollFirst()-1;
            if (a>0){
                queue.addLast(a);
            }
            if (queue.size()==0){
                out.print(-1);
                return;
            }
        }
        out.println(queue.size());
        for (int x:queue
             ) {
            out.print(x+" ");

        }


    }
    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("bureaucracy.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new J().run();

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

