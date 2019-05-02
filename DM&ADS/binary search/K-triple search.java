import java.io.*;
import java.util.*;

public class Trains {
    int[] t=new int[1002];
    int[] times2 =new int[1002];
    int[] v=new int[1002];
    int[] s =new int[1002];
    int n;
    int l;
    double res;

    double var1(int x){
        if (x<0){
            return 0;
        }
        if (x> s[n]){
            return times2[n];
        }
        int i=0;
        while (s[i]<x){
            i++;
        }
        return times2[i]*1.0-(s[i]*1.0-x)/v[i];
    }
    double var2(int x){
        if (x<0){
            return 0;
        }
        if (x> s[n]){
            return times2[n];
        }
        int i=0;
        while (s[i]<=x){
            i++;
        }
        return times2[i]*1.0-(s[i]*1.0-x)/v[i];
    }

    void res(double x){
        if (x> res) res =x;
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("trains.in");
        t[0]=0;
        v[0]=1;
        l=in.nextInt();
        n=in.nextInt();
        for (int i = 1; i <=n ; i++) {
            t[i]=in.nextInt();
            v[i]=in.nextInt();
        }

        s[0]=0;
        for (int i = 1; i <=n; i++) {
            s[i]= s[i-1]+v[i]*t[i];
        }
        times2[0]=0;
        for (int i = 1; i <=n ; i++) {
            times2[i]= times2[i-1]+t[i];
        }
        s[n+1]= s[n]+1;
        v[n+1]=1;
        times2[n+1]= times2[n]+1;


        for (int i = 0; i <=n; i++) {
            res(var1(s[i]+l)- times2[i]);
            res(times2[i]-var1(s[i]-l));
            res(var2(s[i]+l)- times2[i]);
            res(times2[i]-var2(s[i]-l));

        }

        out.format(Locale.ENGLISH,"%.6f%n", res);


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("trains.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Trains().run();

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

