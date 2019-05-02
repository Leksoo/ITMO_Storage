package AlgoLab2;

import java.io.*;
import java.util.*;

public class C {
    static ArrayList<String> stack;

    static boolean empty() {
        return stack.size() == 0;
    }


    static void push(String element) {
        stack.add(element);
    }

    String pop() {
        return stack.remove(stack.size() - 1);
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("postfix.in");
        BufferedReader r=new BufferedReader(new InputStreamReader(new FileInputStream("postfix.in")));
        stack = new ArrayList<>();
        String[] line =r.readLine().split(" ");
        for (int i = 0; i < line.length; i++) {
            String next=line[i];
            if (next.equals("+")){
                String a=pop();
                String b=pop();
                int c=Integer.parseInt(a)+Integer.parseInt(b);
                push(Integer.toString(c));
            }
            else if (next.equals("-")){
                String a=pop();
                String b=pop();
                int c=Integer.parseInt(b)-Integer.parseInt(a);
                push(Integer.toString(c));

            }
            else if (next.equals("*")) {
                String a=pop();
                String b=pop();
                int c=Integer.parseInt(a)*Integer.parseInt(b);
                push(Integer.toString(c));
            }
            else {
                push(next);
            }

        }
        out.print(pop());


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("postfix.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new C().run();

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


