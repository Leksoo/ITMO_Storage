package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task11 {

    int[] nextComb(int[] arr, int n, int k) {
        int[] arrCopy = Arrays.copyOf(arr, arr.length + 1);
        int i = k - 1;
        arrCopy[k] = n + 1;
        while ((i >= 0) && (arrCopy[i + 1] - arrCopy[i] < 2)) {
            i--;
        }
        if (i >= 0) {
            arrCopy[i]++;
            for (int j = i + 1; j < k; j++) {
                arrCopy[j] = arrCopy[j - 1] + 1;
            }
            int[] arrCopy2 = Arrays.copyOf(arrCopy, arrCopy.length-1);
            return arrCopy2;
        } else {
            return null;
        }
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("subsets.in");
        int n=in.nextInt();
        int[] a=new int[n];
        for (int i = 0; i <n ; i++) {
            a[i]=i+1;
        }
        out.println();
        ArrayList<String> ans=new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int[] b=new int[i];
            StringBuilder str=new StringBuilder();
            for (int j = 0; j <i ; j++) {
                b[j]=j+1;
                if (j+1==10) {
                    str.append("A");
                }
                else str.append(j+1);
            }
            ans.add(str.toString());
            while (true){
                b = nextComb(b, n, i);
                str=new StringBuilder();
                if (b == null) {
                    break;
                }
                for (int j = 0; j <i ; j++) {
                    if (b[j]==10) {
                        str.append("A");
                    }
                    else
                        str.append(b[j]);
                }
                ans.add(str.toString());

            }
        }
        Collections.sort(ans);
        for (String x:ans
             ) {
            for (int i = 0; i <x.length(); i++) {
                if (x.charAt(i)=='A'){
                    out.print(10);
                }
                else
                out.print(x.charAt(i)+" ");

            }
            out.println();

        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("subsets.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task11().run();

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
