package AlgoLab2;

import java.io.*;
import java.util.*;

public class B {
    static ArrayList<Character> stack;

    static boolean empty() {
        return stack.size() == 0;
    }


    static void push(char element) {
        stack.add(element);
    }

    char pop() {
        return stack.remove(stack.size() - 1);
    }


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("brackets.in");
        ArrayList<Character> a = new ArrayList<>();
        String k = in.next();
        stack = new ArrayList<>();
        for (int i = 0; i < k.length(); i++) {
            char next = k.charAt(i);

            if (next == '(' || next == '{' || next == '[') {
                push(next);
            } else {
                if (empty()) {
                    out.print("NO");
                    return;
                }
                char last = pop();
                if (last == '(' && next != ')') {
                    out.print("NO");
                    return;
                }
                if (last == '[' && next != ']') {
                    out.print("NO");
                    return;
                }
                if (last == '{' && next != '}') {
                    out.print("NO");
                    return;
                }
            }
        }
        if (empty()) {
            out.print("YES");
        } else out.print("NO");


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("brackets.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new B().run();

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

