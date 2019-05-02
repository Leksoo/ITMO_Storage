package AlgoLab2;

import java.io.*;
import java.util.*;

public class K {


    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("kenobi.in");
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("kenobi.in")));
        int n = Integer.parseInt(r.readLine());
        Deque<Integer> deck1 = new ArrayDeque<>();
        Deque<Integer> deck2 = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String[] comm = r.readLine().split(" ");
            if (comm[0].equals("add")) {
                deck1.addLast(Integer.parseInt(comm[1]));
                if (deck1.size() != deck2.size() && (deck1.size() + deck2.size()) % 2 == 0) {
                    deck2.addLast(deck1.pollFirst());
                }
            } else if (comm[0].equals("take")) {
                if ((deck1.size() + deck2.size()) != 0) {
                    deck1.pollLast();
                    if ((deck1.size() + deck2.size()) % 2 != 0) {
                        deck1.addFirst(deck2.pollLast());
                    }
                }
            } else if (comm[0].equals("mum!")) {
                if (deck1.size()+deck2.size()<2){
                    continue;
                }
                Deque<Integer> t=new ArrayDeque<>();
                t=deck1;
                deck1=deck2;
                deck2=t;
                if (deck2.size() > deck1.size()) {
                    deck1.addFirst(deck2.pollLast());
                }
            }
        }
        out.println(deck1.size() + deck2.size());
        for (int x:deck2
             ) {
            out.print(x + " ");

        }
        for (int x:deck1
                ) {
            out.print(x + " ");

        }



    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("kenobi.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new K().run();

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

