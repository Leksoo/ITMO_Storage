package DiskrLab4;

import java.io.*;

import java.math.BigDecimal;
import java.util.*;

public class D {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("markchain.in");
        int n=in.nextInt();
       double chain[][]=new double[n][n];
        double res=0;
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                chain[i][j]=in.nextDouble();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double temp = chain[i][j];
                chain[i][j] = chain[j][i];
                chain[j][i] = temp;
            }
        }

        double[][] gauss=new double[n][n+1];
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j <n ; j++) {
                gauss[i][j]=chain[i][j];
            }
        }
        for (int i = 0; i <=n ; i++) {
            gauss[n-1][i]=1.0;
        }
        for (int i = 0; i <n-1 ; i++) {
            gauss[i][i]-=1.0;
        }

        for (int m = 1; m <=n-1 ; m++) {

            for (int i = m; i < n; i++) {
                double coeff = gauss[i][m-1] / gauss[m - 1][m-1];
                for (int j = m - 1; j <= n; j++) {
                    gauss[i][j] -= gauss[m-1][j] * coeff;
                }
            }
        }

        for (int m = n-2; m >=0 ; m--) {

            for (int i = m; i >=0; i--) {
                double coeff = gauss[i][m+1] / gauss[m+1][m+1];
                for (int j =n; j >=0; j--) {
                    gauss[i][j] -= gauss[m+1][j] * coeff;
                }
            }
        }






//        for (int s = 1; s <30; s++) {
//
//            double tmp[][]=new double[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    for (int k = 0; k < n; k++) {
//                        tmp[i][j] += chain[i][k] * chain[k][j];
//                    }
//                }
//            }
//            chain=Arrays.copyOf(tmp,n);
//        }
        //System.out.println(Arrays.deepToString(chain));
        for (int i = 0; i <n ; i++) {


            out.printf(Locale.ENGLISH,"%.4f",gauss[i][n]/gauss[i][i]);
            out.println();
        }




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("markchain.out");
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
