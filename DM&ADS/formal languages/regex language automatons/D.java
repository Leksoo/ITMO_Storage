package DiskrLab5;

import java.io.*;
import java.util.*;

public class D {


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("problem4.in");
        int n=in.nextInt();
        int m =in.nextInt();
        int k=in.nextInt();
        int l=in.nextInt();
        HashSet<Integer> terminals=new HashSet<>();
        for (int i = 0; i <k ; i++) {
            terminals.add(in.nextInt());
        }
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for (int i = 0; i <m ; i++) {
            int from=in.nextInt();
            int to=in.nextInt();
            String a=in.next();
            if (!map.containsKey(from)){
                map.put(from,new ArrayList<>());
                map.get(from).add(to);

            }
            else {
                map.get(from).add(to);
            }
        }
        long[] dp=new long[n+5];
        for (int i = 1; i <=n ; i++) {
            if(terminals.contains(i)){
                dp[i]=1;
            }
        }
        for (int i = 0; i <l ; i++) {
            long[] dpTmp=new long[n+5];
            for (Integer key : map.keySet()) {
                for (Integer x: map.get(key) ) {
                    dpTmp[key]+=dp[x];
                    dpTmp[key]%=1000000007;
                }

            }
            dp=dpTmp;
        }
        out.print(dp[1]);




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("problem4.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new D().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                if (line.equals("")) {
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