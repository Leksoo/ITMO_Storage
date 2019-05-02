import java.io.*;
import java.util.*;

public class Sort {

    public static void sort(int[] arr, int l, int r){
     if(l<r) {
         int m = split(arr, l, r);
         sort(arr, l, m);
         sort(arr, m+1, r);
     }
     else {
         return;
     }


    }
    public static int split(int[] arr, int l,int r){
        Random rnd = new Random();
        int x=arr[l + rnd.nextInt(r - l+1)];
        int i=l-1;
        int j=r+1;
        while (i<j) {
            i++;
            while (arr[i] < x) {
                i++;
            }
            j--;
            while (arr[j] >x) {
               j--;
            }
            if (i<j){
                int t=arr[i];
                arr[i]=arr[j];
                arr[j]=t;
            }

        }
        return j;
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in= new FastScanner("sort.in");
        int n=in.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i] = in.nextInt();

        }
        sort(arr,0,arr.length-1);
        for (int x:arr
             ) {
            out.print(x+" ");

        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("sort.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Sort().run();

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








