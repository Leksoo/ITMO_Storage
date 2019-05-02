import java.io.*;
import java.util.*;

public class garland {
    public static boolean isNotOnGround(double a,double mid,int n){
        double[] mas=new double[n];
        mas[0]=a;
        mas[1]=mid;
        for (int i = 2; i <n ; i++) {
            mas[i]=2*mas[i-1]-mas[i-2]+2;
            if (mas[i]<=0){
                return false;
            }
        }
        return true;


    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("garland.in");
        int n=in.nextInt();
        double a=in.nextDouble();
        double gr=0;
        double above=a;

        while(true){
            double mid=(gr+above)/2;
            if(mid==above || mid==gr ){
                break;
            }
            if (isNotOnGround(a,mid,n)){
                above=mid;
            }
            else {
                gr=mid;
            }
        }
        double[] mas=new double[n];
        mas[0]=a;
        mas[1]=above;
        for (int i = 2; i <n ; i++) {
            mas[i]=2*mas[i-1]-mas[i-2]+2;
        }
        out.print( Math.rint(100.0 * mas[n-1]) / 100.0);

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("garland.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new garland().run();

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


