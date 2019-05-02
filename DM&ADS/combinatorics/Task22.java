package DiskrLab3;

import java.io.*;
import java.util.*;

public class Task22 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("part2num.in");
        String p=in.next();
        ArrayList<Integer> arr=new ArrayList<>();
        StringBuilder a=new StringBuilder();
        for (int i = 0; i <p.length() ; i++) {
            if (i==p.length()-1){
                a.append(p.charAt(i));
                arr.add(Integer.parseInt(a.toString()));
            }
            if (p.charAt(i)=='+'){
                arr.add(Integer.parseInt(a.toString()));
                a=new StringBuilder();
            }else {
                a.append(p.charAt(i));
            }
        }
        int k=0;
        int n=0;
        for (int x:arr
             ) {
            n+=x;
        }

        int[][] splits = new int[102][102];
        for (int i = 0; i <= 101; i++) {
            for (int j = 101; j >= 0; j--) {
                if (i==0){
                    splits[i][j]=1;
                }
                else if (i==j){
                    splits[i][j]=1;
                }
                else if(i>=j){
                    splits[i][j] = splits[i - j][j] + splits[i][j + 1];
                }
            }
        }

//        for (int[] x : splits
//                ) {
//            for (int y : x
//                    ) {
//                out.print(y + " ");
//
//            }
//            out.println();
//        }
        int curr=1;
        int num =n;
        int i=0;
        while (true) {
            for (int j = curr; j <arr.get(i); j++) {

                    k+=splits[num-j][j];

            }
            num-=arr.get(i);
            if (num==0){
                break;
            }
            curr=arr.get(i);
            i++;
        }
        out.print(k);




    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("part2num.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task22().run();

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
