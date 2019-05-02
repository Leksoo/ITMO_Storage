package AlgoLab2;

import java.io.*;
import java.util.*;

public class E {
    static int[] counts;
    static int[] mins;
    static int[] maxs;
   static int[] p;
    static int[] rank;
    static void init (int el) {
        p[el]=el;
        rank[el] = 0;
    }

    static int get (int el) {
        if(p[el]==el){
            return el;
        }
        return p[el]=get(p[el]);
    }

    static void union(int x, int y) {
        x = get(x);
        y = get(y);

        if (x != y) {
            counts[x]=counts[x]+counts[y];
            counts[y]=counts[x];
            if (mins[x]<mins[y]) {
                mins[y] = mins[x];
            }
            else {
                mins[x]=mins[y];
            }
            if (maxs[x]>maxs[y]){
                maxs[y]=maxs[x];
            }
            else {
                maxs[x]=maxs[y];
            }


            if (rank[x] < rank[y]) {
                int t = x;
                x=y;
                y=t;
            }

            p[y] = x;
            rank[x]=Math.max(rank[x],rank[y]+1);
        }
    }



    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("dsu.in");
        BufferedReader r=new BufferedReader(new InputStreamReader(new FileInputStream("dsu.in")));
        int n=Integer.parseInt(r.readLine());
        p=new int[n+2];
        rank=new int[n+2];
        counts=new int[n+1];
        mins=new int[n+1];
        maxs=new int[n+1];
        for (int i = 1; i <=n; i++) {
            init(i);
            counts[i]=1;
            mins[i]=i;
            maxs[i]=i;
        }
        String line="1";
        while (line!=null){
            line=r.readLine();
            if (line==null){
                break;
            }
            String[] a=line.split(" ");
            if (a[0].equals("union")){
                union(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
            }
            if (a[0].equals("get")){
                out.println(mins[get(Integer.parseInt(a[1]))]+" "+maxs[get(Integer.parseInt(a[1]))]+" "+counts[get(Integer.parseInt(a[1]))]);
            }
        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("dsu.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new E().run();

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

