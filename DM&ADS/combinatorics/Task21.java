package DiskrLab3;


import java.io.*;
import java.util.*;

public class Task21 {

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("part2num.out");
        int n = in.nextInt();
        int k = in.nextInt();
        k++;
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
        ArrayList<Integer> ans = new ArrayList<>();
        int num = n;
        ans.add(1);
        while (true) {
            for (int j = ans.get(ans.size()-1); j <= num; j++) {
                if (splits[num-j][j]>=k){
                    num-=j;
                    break;
                }
                else {
                    k-=splits[num-j][j];
                    ans.set(ans.size()-1,ans.get(ans.size()-1)+1);
                }
            }
            if (num==0){
                break;
            }
            ans.add(ans.get(ans.size()-1));
        }
        Collections.sort(ans);
        for (int l = 0; l < ans.size() - 1; l++) {
            out.print(ans.get(l) + "+");
        }
        out.print(ans.get(ans.size() - 1));

    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("part2num.in");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new Task21().run();

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
