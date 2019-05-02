package DiskrLab5;

import java.io.*;
import java.util.*;

public class F {
    boolean result = true;
    boolean[] visited;
    HashSet<Integer> terminals1 = new HashSet<>();
    HashMap<Integer, HashMap<Character, Integer>> map = new HashMap<>();
    HashSet<Integer> terminals2 = new HashSet<>();
    HashMap<Integer, HashMap<Character, Integer>> map2 = new HashMap<>();


    void dfs(int v1, int v2) {
        visited[v1] = true;
        if (terminals1.contains(v1) != terminals2.contains(v2)) {
            result = false;
            return;
        }

        HashMap<Character, Integer> curMap = map.get(v1);
        if (curMap == null && map2.get(v2) != null) {
            result = false;
            return;
        }
        if (curMap != null) {
            for (Character ch : curMap.keySet()) {
                int u1 = map.get(v1).get(ch);
                if (!map2.get(v2).containsKey(ch)) {
                    result = false;
                    return;
                }
                int u2 = map2.get(v2).get(ch);
                if((u1==v1 && u2!=v2)||(v1!=u1 && u2==v2)){
                    result=false;
                    return;
                }
                if (!visited[u1]) {
                    dfs(u1, u2);
                }
            }
        }
        return;
    }

    void solve(PrintWriter out) throws IOException {
        FastScanner in = new FastScanner("isomorphism.in");
        int n1 = in.nextInt();
        int m1 = in.nextInt();
        int k1 = in.nextInt();

        for (int i = 0; i < k1; i++) {
            terminals1.add(in.nextInt());
        }

        for (int i = 0; i < m1; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            String a = in.next();
            if (!map.containsKey(from)) {
                map.put(from, new HashMap<>());
                map.get(from).put(a.charAt(0), to);

            } else {
                map.get(from).put(a.charAt(0), to);
            }
        }

        int n2 = in.nextInt();
        int m2 = in.nextInt();
        int k2 = in.nextInt();

        for (int i = 0; i < k2; i++) {
            terminals2.add(in.nextInt());
        }

        for (int i = 0; i < m2; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            String a = in.next();
            if (!map2.containsKey(from)) {
                map2.put(from, new HashMap<>());
                map2.get(from).put(a.charAt(0), to);

            } else {
                map2.get(from).put(a.charAt(0), to);
            }
        }

        if (n1 != n2) {
            result = false;
        }
        if (m1 != m2)
            result = false;
        if (k1 != k2)
            result = false;
        if(map.size()!=map2.size()){
            result=false;
        }
        if (!result) {
            out.println("NO");
            return;
        }
        visited = new boolean[n1 + 1];
        dfs(1, 1);
        if (result) out.println("YES");
        else out.println("NO");


    }


    StreamTokenizer in;
    PrintWriter out;

    void run() throws IOException {
        out = new PrintWriter("isomorphism.out");
        solve(out);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        new F().run();

    }

    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public FastScanner() throws IOException {
            reader = new BufferedReader(new InputStreamReader(System.in));
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
    }

}