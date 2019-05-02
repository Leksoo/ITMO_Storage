package AlgoLab2;

import java.io.*;
import java.util.*;

public class D {
    static ArrayList<Integer> stack;
    static ArrayList<Integer> stackMin;

    static boolean empty() {
        return stack.size() == 0;
    }

    static void push(Integer element) {
        stack.add(element);
    }

    Integer pop() {
        return stack.remove(stack.size() - 1);
    }

    static boolean emptyMin() {
        return stackMin.size() == 0;
    }


    static void pushMin(Integer element) {
        stackMin.add(element);
    }

    Integer popMin() {
        return stackMin.remove(stackMin.size() - 1);
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("stack-min.in");
        BufferedReader r=new BufferedReader(new InputStreamReader(new FileInputStream("stack-min.in")));
        stack = new ArrayList<>();
        stackMin=new ArrayList<>();
        int n=in.nextInt();
        for (int i = 0; i <n ; i++) {
            int command=in.nextInt();
            if (command==1){
                int el=in.nextInt();
                push(el);
                if (emptyMin()){
                    pushMin(el);
                }
                else {
                    int b=popMin();
                    if (el<=b){
                        pushMin(b);
                        pushMin(el);
                    }
                    else {
                        pushMin(b);
                        pushMin(b);
                    }
                }
            }
            else if (command==2){
                pop();
                popMin();
            }
            else if (command==3){
                out.println(stackMin.get(stackMin.size()-1));
            }
        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("stack-min.out");
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
