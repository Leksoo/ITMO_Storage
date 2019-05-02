import java.io.*;
import java.util.*;

public class Style2 {
    static int min(int a1,int a2,int a3,int a4){
        return Math.min(Math.min(a1,a2),Math.min(a3,a4));
    }
    static int max(int a1,int a2,int a3,int a4){
        return Math.max(Math.max(a1,a2),Math.max(a3,a4));
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("style.in");
        int n1 = in.nextInt();
        int[] ar1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            ar1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] ar2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            ar2[i] = in.nextInt();
        }
        int n3 = in.nextInt();
        int[] ar3 = new int[n3];
        for (int i = 0; i < n3; i++) {
            ar3[i] = in.nextInt();
        }
        int n4 = in.nextInt();
        int[] ar4 = new int[n4];
        for (int i = 0; i < n4; i++) {
            ar4[i] = in.nextInt();
        }
        Arrays.sort(ar1);
        Arrays.sort(ar2);
        Arrays.sort(ar3);
        Arrays.sort(ar4);
        int i1=0;
        int i2=0;
        int i3=0;
        int i4=0;
        int res1=0,res2=0,res3=0,res4=0;
        int minRes=Integer.MAX_VALUE;
        int abs=max(ar1[i1],ar2[i2],ar3[i3],ar4[i4])-min(ar1[i1],ar2[i2],ar3[i3],ar4[i4]);
        if (abs<minRes){
            minRes=abs;
            res1=ar1[i1];
            res2=ar2[i2];
            res3=ar3[i3];
            res4=ar4[i4];
        }
        while (true){
            int min=min(ar1[i1],ar2[i2],ar3[i3],ar4[i4]);
            if ( ar1[i1]==min) i1++;
            else if (ar2[i2]==min) i2++;
            else if (ar3[i3]==min) i3++;
            else if (ar4[i4]==min) i4++;
            if (!(i1<n1 && i2<n2 && i3<n3 && i4<n4) ){
                break;
            }
            abs=max(ar1[i1],ar2[i2],ar3[i3],ar4[i4])-min(ar1[i1],ar2[i2],ar3[i3],ar4[i4]);
            if (abs<minRes){
                minRes=abs;
                res1=ar1[i1];
                res2=ar2[i2];
                res3=ar3[i3];
                res4=ar4[i4];
            }
        }
        out.print(res1+" "+res2+" "+res3+" "+res4);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("style.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Style2().run();

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


