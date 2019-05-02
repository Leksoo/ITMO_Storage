package DiskrLab9;


import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"WeakerAccess", "ConstantConditions"})

public class C {

    final static String fileName = "matching";
    PrintWriter out;
    //================================================================================





    int N;

    List<Pair<Integer,Integer>> weights;
    List<List<Integer>> edges;
    List<Integer> matching;
    List<Boolean> used;


    boolean findMatching(int v) {
        used.set(v,true);
        for (int i = 0; i < edges.get(v).size(); ++i) {
            int u = edges.get(v).get(i);
            if (matching.get(u) == -1 || (!used.get(matching.get(u)) && findMatching(matching.get(u)))) {
                matching.set(u, v);
                return true;
            }
        }
        return false;
    }

    void input() throws IOException {
        FastScanner in = new FastScanner();
        N = in.nextInt();
        weights = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int w = in.nextInt();
            weights.add(new Pair<>(w,i));
        }
        edges = Stream.generate(() -> new ArrayList<Integer>()).limit(N).collect(Collectors.toList());
        for (int i = 0; i < N ; i++) {
            int num = in.nextInt();
            for (int j = 0; j <num ; j++) {
                int v = in.nextInt()-1;
                edges.get(i).add(v);
            }
        }
        weights.sort((Pair<Integer,Integer> a , Pair<Integer,Integer> b) -> b.getKey().compareTo(a.getKey()));
        matching = Stream.generate(() -> -1).limit(N).collect(Collectors.toList());
        used = Stream.generate(() -> false).limit(N).collect(Collectors.toList());
        for (int i = 0; i < N ; i++) {
            Collections.fill(used,false);
            findMatching(weights.get(i).getValue());

        }
        List<Integer> res = Stream.generate(() -> -1).limit(N).collect(Collectors.toList());


        for (int j = 0; j < N; ++j) {
            if(matching.get(j)!= -1) {
                res.set(matching.get(j), j);
            }
        }

        for (int el:res) {
            print(el+1+" ");
        }

    }


    void solve() throws IOException {
        input();



    }

    //==================================================================================


    void run() throws IOException {
        out = new PrintWriter(fileName + ".out");
        solve();
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new C().run();

    }

    <T> void print(T obj) {
        if (fileName.equals("")) {
            System.out.print(obj);
        } else {
            out.print(obj);
        }
    }

    <T> void println(T obj) {
        if (fileName.equals("")) {
            System.out.println(obj);
        } else {
            out.println(obj);
        }
    }

    static class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner() throws IOException {
            if (fileName.equals("")) {
                reader = new BufferedReader(new InputStreamReader(System.in));
            } else {
                reader = new BufferedReader(new FileReader(fileName + ".in"));
            }
        }


        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                if (line.equals("")) {
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

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

}