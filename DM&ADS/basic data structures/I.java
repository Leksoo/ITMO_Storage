package AlgoLab2;

import java.io.*;
import java.util.*;

public class I {
    static ArrayList<Integer> stack;
    static ArrayList<Integer> sums;

    static boolean empty() {
        return stack.size() == 0;
    }


    static void push(int element) {
        stack.add(element);
    }

    int pop() {
        return stack.remove(stack.size() - 1);
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("hemoglobin.in");
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("hemoglobin.in")));
        Integer n = in.nextInt();
        stack = new ArrayList<>();
        sums = new ArrayList<>();
        int sum=0;
        for (int i = 0; i < n; i++) {
            String next = in.next();
            if (next.charAt(0)=='+'){
                int num=Integer.parseInt(next.substring(1));
                push(num);
                sum+=num;
                if (sums.size()==0) {
                    sums.add(num);
                }
                else {
                    sums.add(sums.get(sums.size()-1)+num);
                }
            }
            else if (next.charAt(0)=='-'){
                int num=pop();
                out.println(num);
                sum-=num;
                sums.remove(sums.size()-1);

            }
            else if (next.charAt(0)=='?'){
                int k=Integer.parseInt(next.substring(1));
                if (sums.size()-1-k<0){
                    out.println(sum);
                }
                else {
                    out.println(sum - sums.get(sums.size() - 1 - k));
                }


            }

        }
    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("hemoglobin.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new I().run();

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
